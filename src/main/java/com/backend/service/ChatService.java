package com.backend.service;

import com.backend.document.chat.ChatMapperDocument;
import com.backend.document.chat.ChatMemberDocument;
import com.backend.document.chat.ChatMessageDocument;
import com.backend.document.chat.ChatRoomDocument;
import com.backend.dto.chat.ChatMessageDTO;
import com.backend.dto.chat.ChatRoomDTO;
import com.backend.dto.chat.NotificationResponse;
import com.backend.entity.user.User;
import com.backend.repository.chat.ChatMapperRepository;
import com.backend.repository.chat.ChatMemberRepository;
import com.backend.repository.chat.ChatMessageRepository;
import com.backend.repository.chat.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatMemberRepository chatMemberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMapperRepository chatMapperRepository;
    public static final int PAGE_SIZE = 20;

    public String createChatRoom(ChatRoomDTO chatRoomDTO) {

        ChatRoomDocument chatRoomDocument = chatRoomDTO.toDocument();
        log.info("chatRoomDocument: " + chatRoomDocument);
        ChatRoomDocument savedDocument = chatRoomRepository.save(chatRoomDocument);

        if (savedDocument.getMembers() != null && savedDocument.getLeader() != null) {
            ChatMapperDocument mapper = ChatMapperDocument.builder()
                    .userId(savedDocument.getLeader())
                    .chatRoomId(savedDocument.getId())
                    .joinedAt(LocalDateTime.now())
                    .build();
            chatMapperRepository.save(mapper);

            savedDocument.getMembers().forEach(uid -> {
                ChatMapperDocument mapperDocument = ChatMapperDocument.builder()
                        .userId(uid)
                        .chatRoomId(savedDocument.getId())
                        .joinedAt(LocalDateTime.now())
                        .build();
                chatMapperRepository.save(mapperDocument);
            });
        }

        // 채팅방 생성 시 채팅방 구성원 전체에게 시스템 메시지 전송
        // 시스템 알림 메시지 설정
        ChatMessageDocument message = ChatMessageDocument.builder()
                .roomId(savedDocument.getId())
                .sender("System")
                .content("채팅방이 생성되었습니다.")
                .type("CREATE")
                .build();

        // 메시지를 브로드캐스트
        messagingTemplate.convertAndSend("/topic/chat/" + savedDocument.getId(), message);

        return savedDocument.getId();
    }

    public ChatRoomDTO getChatRoomInfo(String chatRoomId) {
        if (chatRoomRepository.findById(chatRoomId).isPresent()) {
            ChatRoomDocument chatRoomDocument = chatRoomRepository.findById(chatRoomId).get();
            return chatRoomDocument.toDTO();
        }
        return null;
    }

    public List<ChatRoomDTO> getAllChatRoomsByUserId(String userId) {
        List<ChatRoomDocument> chatRoomList = chatRoomRepository.findAllByLeaderOrMembersAndStatusIsNot(userId, userId, 0);
        log.info("chatRoomList: " + chatRoomList);
        List<ChatRoomDTO> chatRoomDTOS = chatRoomList.stream().map(ChatRoomDocument::toDTO).toList();
        log.info("chatRoomDTOS: " + chatRoomDTOS);
        return chatRoomDTOS;
    }

    public void updateUnreadCountsAndLastMessage(String chatRoomId, String senderId) {
        // 사용자가 참여한 채팅방 가져오기
        List<ChatMapperDocument> userChatMappings = chatMapperRepository.findByChatRoomId(chatRoomId);
        log.info("userChatMappings: " + userChatMappings);
        for (ChatMapperDocument mapper : userChatMappings) {
            String userId = mapper.getUserId();
            log.info("userId: " + userId);
            if (!userId.equals(senderId)) {
                // 해당 사용자의 읽지 않은 메시지 수 업데이트
                long count = getUnreadMessageCount(senderId, chatRoomId).getCount();
                log.info("count: " + count);
                // 읽지 않은 메시지 수 알림 전송
                NotificationResponse notification = new NotificationResponse();
                notification.setType("unreadCount");
                notification.setChatRoomId(chatRoomId);
                notification.setUnreadCount((int) count);
                log.info("notification : ", notification);
                messagingTemplate.convertAndSend("/topic/notifications/" + userId, notification);
                // 마지막 메시지 알림 전송
                String lastMessage = chatMessageRepository.findFirstByRoomIdOrderByTimeStampDesc(chatRoomId).getContent();
                log.info("lastMessage: " + lastMessage);
                NotificationResponse lastMessageNotification = new NotificationResponse();
                lastMessageNotification.setType("lastMessage");
                lastMessageNotification.setChatRoomId(chatRoomId);
                lastMessageNotification.setLastMessage(lastMessage);
                log.info("lastMessageNotification : ", lastMessageNotification);
                messagingTemplate.convertAndSend("/topic/notifications/" + userId, lastMessageNotification);
            }
        }
    }


    public ChatMemberDocument saveChatMember(String chatRoomId, User user, String groupName) {
        ChatMemberDocument chatMemberDocument = new ChatMemberDocument();
        chatMemberDocument.setUid(user.getUid());
        chatMemberDocument.setEmail(user.getEmail());
        chatMemberDocument.setHp(user.getHp());
        chatMemberDocument.setLevel(user.getLevel());
        chatMemberDocument.setName(user.getName());
        chatMemberDocument.setGroup(groupName);
        chatMemberDocument.getRoomIds().add(chatRoomId);

        ChatMemberDocument savedDocument = chatMemberRepository.save(chatMemberDocument);
        return savedDocument;
    }

    public ChatRoomDocument updateChatRoomFavorite(ChatRoomDTO chatRoomDTO) {
        ChatRoomDocument chatRoomDocument = chatRoomRepository.findById(chatRoomDTO.getId()).orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다. ID: " + chatRoomDTO.getId()));
        if (chatRoomDTO.getChatRoomFavorite() == 0 || chatRoomDTO.getChatRoomFavorite() == 1) {
            chatRoomDocument.setChatRoomFavorite(chatRoomDTO.getChatRoomFavorite());
        }
        ChatRoomDocument savedDocument = chatRoomRepository.save(chatRoomDocument);
        return savedDocument;
    }

    public String updateChatMemberFavorite(String email, ChatMemberDocument frequentMember, String type) {
        ChatMemberDocument loginUser = chatMemberRepository.findByEmail(email);
        if (loginUser != null && Objects.equals(type, "insert")) {
            if (!loginUser.getFrequent_members().contains(frequentMember)) {
                loginUser.getFrequent_members().add(frequentMember);
            } else {
                return "duplicate";
            }
            chatMemberRepository.save(loginUser);
            return "success";
        } else if (loginUser != null && Objects.equals(type, "delete")) {
            loginUser.getFrequent_members().remove(frequentMember);
            chatMemberRepository.save(loginUser);
            return "success";
        }
        return "failure";
    }

    public ChatMemberDocument findChatMember(String uid) {
        if (chatMemberRepository.findByUid(uid) != null) {
            ChatMemberDocument document = chatMemberRepository.findByUid(uid);
            return document;
        }
        return null;
    }

    @Transactional
    public void deleteChatMember(String uid, String roomId) {
        Optional<ChatRoomDocument> optionalChatRoomDocument = chatRoomRepository.findById(roomId);
        if (optionalChatRoomDocument.isPresent()) {
            ChatRoomDocument chatRoomDocument = optionalChatRoomDocument.get();
            if (chatRoomDocument.getMembers().contains(uid)) {
                chatRoomDocument.getMembers().remove(uid);
            } else if (chatRoomDocument.getLeader().equals(uid)) {
                chatRoomDocument.setLeader(null);
            }
            chatRoomRepository.save(chatRoomDocument);
        }
        chatMapperRepository.deleteByUserIdAndChatRoomId(uid, roomId);
    }

    public ChatMessageDocument saveMessage(ChatMessageDTO chatMessageDTO) {
        ChatMessageDocument chatMessageDocument = chatMessageDTO.toDocument();
        if (chatMessageDocument != null) {
            ChatMessageDocument savedDocument = chatMessageRepository.save(chatMessageDocument);
            return savedDocument;
        }
        return null;
    }

    // 특정 채팅방의 최신 메시지 로드
    public List<ChatMessageDocument> getLatestMessages(String chatRoomId) {
        PageRequest pageRequest = PageRequest.of(0, PAGE_SIZE);
        return chatMessageRepository.findByRoomIdOrderByTimeStampDesc(chatRoomId, pageRequest);

    }

    // 특정 채팅방의 이전 메시지 로드
    public List<ChatMessageDocument> getOlderMessages(String chatRoomId, LocalDateTime beforeTimestamp) {
        PageRequest pageRequest = PageRequest.of(0, PAGE_SIZE);
        return chatMessageRepository.findByRoomIdAndTimeStampBeforeOrderByTimeStampDesc(chatRoomId, beforeTimestamp, pageRequest);
    }

    // 사용자가 채팅방을 읽었다고 표시
    public void markAsRead(String userId, String chatRoomId, String readTimestamp) {
        Optional<ChatMapperDocument> chatMapperOpt = chatMapperRepository.findByUserIdAndChatRoomId(userId, chatRoomId);
        ChatMapperDocument chatMapper;

        if (chatMapperOpt.isPresent()) {
            chatMapper = chatMapperOpt.get();
        } else {
            // 사용자와 채팅방이 존재하는지 확인
            if (!chatMemberRepository.existsById(userId)) {
                throw new IllegalArgumentException("Invalid user ID");
            }
            if (!chatRoomRepository.existsById(chatRoomId)) {
                throw new IllegalArgumentException("Invalid chat room ID");
            }
            // 처음 읽는 경우 생성
            chatMapper = ChatMapperDocument.builder()
                    .userId(userId)
                    .chatRoomId(chatRoomId)
                    .joinedAt(LocalDateTime.parse(readTimestamp)) // 채팅방에 참여한 시간 설정
                    .build();
        }
        chatMapper.setLastReadTimeStamp(LocalDateTime.parse(readTimestamp));
        chatMapperRepository.save(chatMapper);
    }

    // 사용자가 읽지 않은 메시지 수 가져오기
    public ChatMessageDTO getUnreadMessageCount(String userId, String chatRoomId) {

        Optional<ChatMapperDocument> userChatRoomOpt = chatMapperRepository.findByUserIdAndChatRoomId(userId, chatRoomId);
        if (userChatRoomOpt.isEmpty()) {
            long count = chatMessageRepository.countByRoomIdAndTimeStampAfter(chatRoomId, LocalDateTime.MIN);
            ChatMessageDocument chatMessageDocument = chatMessageRepository.findFirstByRoomIdOrderByTimeStampDesc(chatRoomId);
            ChatMessageDTO chatMessageDTO = chatMessageDocument.toDTO();
            chatMessageDTO.setCount(count);
            return chatMessageDTO;
        }

        ChatMapperDocument userChatRoom = userChatRoomOpt.get();
        LocalDateTime lastReadTimestamp = userChatRoom.getLastReadTimeStamp() != null
                ? userChatRoom.getLastReadTimeStamp()
                : userChatRoom.getJoinedAt(); // lastReadTimestamp가 없으면 joinedAt을 기준으로

        long count = chatMessageRepository.countByRoomIdAndTimeStampAfter(chatRoomId, lastReadTimestamp);
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setCount(count);
        return chatMessageDTO;
    }

}
