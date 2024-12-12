package com.backend.service.mongoDB;

import com.backend.document.page.Page;
import com.backend.dto.request.page.PageDto;
import com.backend.entity.folder.Permission;
import com.backend.repository.page.PageRepository;
import com.backend.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;
    private final PermissionService permissionService;

    public Page save(PageDto page) {

        Page pages  = page.ToEntity();
        Page savedpage =  pageRepository.save(pages);
        log.info(savedpage);
        return savedpage;
    }

    public PageDto findById(String id) {
        Optional<Page> opt = pageRepository.findById(id);
        if(opt.isPresent()) {
            Page page = opt.get();
            PageDto pageDto = PageDto.builder()
                    .id(page.getId())
                    .ownerUid(page.getOwnerUid())
                    .title(page.getTitle())
                    .content(page.getContent())
                    .createAt(page.getCreateAt())
                    .updateAt(page.getUpdateAt())
                    .build();
            return pageDto;
        }
        return null;
    }

    public List<Page> pageList(String uid){
        List<Page> list = pageRepository.findByOwnerUid(uid);
        return list;
    }

    public ResponseEntity<?> getPageContent(String pageId, Long userId) {
        Optional<Page> page = pageRepository.findById(pageId);
        if(page.isEmpty()){
            return ResponseEntity.badRequest().body("페이지가 없습니다...");
        }
        PageDto dto = PageDto.builder()
                .ownerUid(page.get().getOwnerUid())
                .title(page.get().getTitle())
                .content(page.get().getContent())
                .createAt(page.get().getCreateAt())
                .updateAt(page.get().getUpdateAt())
                .id(page.get().getId())
                .build();

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> putPageContent(PageDto dto, Long userId) {
        Optional<Page> page = pageRepository.findById(dto.getId());
        if(page.isEmpty()){
            return ResponseEntity.badRequest().body("페이지가 없습니다...");
        }
        page.get().putData(dto);
        pageRepository.save(page.get());
        return ResponseEntity.ok("수정 성공");
    }
}
