package com.backend.service;


import com.backend.dto.response.drive.NewNameResponseDto;
import com.jcraft.jsch.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class SftpService {

    @Value("${file-server.url}")
    private String SFTP_HOST;
    private static final int SFTP_PORT = 22;
    private static final String SFTP_USER = "sftpuser";
    private static final String SFTP_PASSWORD = "Lotte4!@12";

    private static final String BASE_SFTP_DIR = "uploads/";



    //user별 sftpUser 생성
    public boolean  createUserFolderOnSftp(String username) {
        String remoteDir = BASE_SFTP_DIR + username;

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);

            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // SFTP 서버에 폴더 생성
            channelSftp.mkdir(remoteDir);


            channelSftp.disconnect();
            session.disconnect();



            log.info("Folder created and permissions set successfully on SFTP: {}", remoteDir);
            return true;
        } catch (SftpException | JSchException e) {
            log.error("Failed to create folder on SFTP: {}", e.getMessage());
            return false;
        }
    }

    public boolean deleteUserFolderOnSftp(String username) {
        String remoteDir = BASE_SFTP_DIR + username;


        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);

            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // 폴더 삭제 시 폴더 안에 파일이나 하위 폴더가 없어야 삭제 가능
            channelSftp.rmdir(remoteDir);

            channelSftp.disconnect();
            session.disconnect();

            log.info("Folder deleted successfully on SFTP: {}", remoteDir);
            return true;
        } catch (SftpException | JSchException e) {
            log.error("Failed to delete folder on SFTP: {}", e.getMessage());
            return false;
        }
    }





    public NewNameResponseDto createFolder(String folderName,String username) {

        String folderUUID = UUID.randomUUID().toString();
        String remoteDir = BASE_SFTP_DIR + username + "/"+ folderUUID;

        log.info("remoteDIR!!! "+remoteDir);
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // SFTP 서버에 폴더 생성
            channelSftp.mkdir(remoteDir);
            log.info("Folder created successfully on SFTP: {}", remoteDir);

            channelSftp.disconnect();
            session.disconnect();

            log.info("Folder created and permissions set successfully on SFTP: {}", remoteDir);
            return NewNameResponseDto.builder()
                    .folderUUID(folderUUID)
                    .folderName(folderName)
                    .Path(remoteDir)
                    .build();
        } catch (Exception e) {
            log.error("Error while creating folder: {}", e.getMessage());
            return null;
        }
    }



  //사용자 생성시 root 폴더 생성 (폴더이름 -> username)
    public String createRootFolder(String folderName,String uid) {

        String remoteDir = BASE_SFTP_DIR + uid;
        log.info("remoteDIR!!! "+remoteDir);
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // SFTP 서버에 폴더 생성
            channelSftp.mkdir(remoteDir);
            log.info("Folder created successfully on SFTP: {}", remoteDir);

            channelSftp.disconnect();
            session.disconnect();

            log.info("Folder created and permissions set successfully on SFTP: {}", remoteDir);
            return remoteDir;
        } catch (Exception e) {
            log.error("Error while creating folder: {}", e.getMessage());
            return null;
        }
    }

    public NewNameResponseDto createNewFolder(String folderName, String parentPath) {

        String folderUUID = UUID.randomUUID().toString();
        String remoteDir = parentPath +"/"+ folderUUID;
        log.info("remoteDIR!!! "+remoteDir);
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // SFTP 서버에 폴더 생성
            channelSftp.mkdir(remoteDir);
            log.info("Folder created successfully on SFTP: {}", remoteDir);

            channelSftp.disconnect();
            session.disconnect();

            log.info("Folder created and permissions set successfully on SFTP: {}", remoteDir);
            return  NewNameResponseDto.builder()
                    .folderUUID(folderUUID)
                    .folderName(folderName)
                    .Path(remoteDir)
                    .build();
        } catch (Exception e) {
            log.error("Error while creating folder: {}", e.getMessage());
            return null;
        }

    }


    public String uploadFile(String localFilePath, String remoteDir, String remoteFileName) {
        try {
            // SFTP 연결 설정
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // 원격 디렉토리가 없는 경우 생성
            try {
                channelSftp.cd(remoteDir);
            } catch (SftpException e) {
                log.info("Remote directory does not exist. Creating directory: {}", remoteDir);
                channelSftp.cd(remoteDir);
            }

            // 파일 업로드
            channelSftp.put(localFilePath, remoteFileName);
            log.info("File uploaded successfully: {}/{}", remoteDir, remoteFileName);

            // 연결 종료
            channelSftp.disconnect();
            session.disconnect();
            return remoteDir+"/"+remoteFileName;
        } catch (JSchException | SftpException e) {
            log.error("File upload failed: {}", e.getMessage());
            return null;
        }
    }




    public boolean thumbnailFileUploads(String localFilePath,  String remoteFileName) {

       String remoteDir = BASE_SFTP_DIR+"thumbnails";

        try {
            // SFTP 연결 설정
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // 원격 디렉토리가 없는 경우 생성
            try {
                channelSftp.cd(remoteDir);
            } catch (SftpException e) {
                log.info("Remote directory does not exist. Creating directory: {}", remoteDir);
                channelSftp.cd(remoteDir);
            }

            // 파일 업로드
            channelSftp.put(localFilePath, remoteFileName);
            log.info("File uploaded successfully: {}/{}", remoteDir, remoteFileName);

            // 연결 종료
            channelSftp.disconnect();
            session.disconnect();
            return true;
        } catch (JSchException | SftpException e) {
            log.error("File upload failed: {}", e.getMessage());
            return false;
        }
    }

    public boolean downloadFile(String remoteDir, String remoteFileName, String localFilePath) {
        try {
            // SFTP 연결 설정
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // 파일 다운로드
            String remoteFilePath = remoteDir + "/" + remoteFileName;
            channelSftp.get(remoteDir, localFilePath);
            log.info("File downloaded successfully: {} -> {}", remoteFilePath, localFilePath);

            // 연결 종료
            channelSftp.disconnect();
            session.disconnect();
            return true;
        } catch (JSchException | SftpException e) {
            log.error("File download failed: {}", e.getMessage());
            return false;
        }
    }




    public boolean renameFolder(String currentPath, String newPath){
        try {
            // SFTP 연결 설정
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            channelSftp.rename(currentPath, newPath);
            System.out.println("폴더 이름이 성공적으로 변경되었습니다.");

            // 연결 종료
            channelSftp.disconnect();
            session.disconnect();
            return true;
        } catch (JSchException | SftpException e) {
            System.err.println("폴더 이름 변경 실패: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String path){
        try {
            // SFTP 연결 설정
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            String command = "rm -rf " + path;
            channelExec.setCommand(command);
            channelExec.connect();

            System.out.println("폴더와 하위 파일이 모두 삭제되었습니다.");

            channelExec.disconnect();
            session.disconnect();
            return true;
        } catch (JSchException e ) {
            System.err.println("폴더 삭제 실패: " + e.getMessage());
            return false;
        }

    }

    public  long  calculatedSize(String uid) {
        long sizeInKB = 0;
        String path = BASE_SFTP_DIR + uid;

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            log.info("SFTP session connected to host: {}", SFTP_HOST);

            // SFTP 디렉토리 크기 계산 명령어
            String command = "du -sh " + path;
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            InputStream inputStream = channel.getInputStream();
            channel.connect();

            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNextLine()) {
                String[] output = scanner.nextLine().split("\\s+");
                if (output.length > 0) {
                    String sizeStr = output[0];
                    sizeInKB = parseSizeToKB(sizeStr);
                    log.info("Folder size in KB: {}", sizeInKB);
                }
            } else {
                log.error("No output received from command.");
            }
            scanner.close();

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            log.error("Error calculating folder size: {}", e.getMessage(), e);
        }

        return sizeInKB;
    }


    public boolean makeZip(String path, String folderName) {
        String tmp = "~/"+BASE_SFTP_DIR + "zip/" + folderName + ".zip";
        log.info("경로!!"+path);

        String remoteDir = "";
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            log.info("SFTP session connected to host: {}", SFTP_HOST);

            String command = String.format("cd %s && /usr/bin/zip -r %s .", path, tmp);
            log.info("Executing command: {}", command);

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            InputStream in = channel.getInputStream();
            InputStream err = channel.getErrStream();

            channel.connect();

            // 로그 읽기
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(err))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.info("STDOUT: {}", line);
                }
                while ((line = errorReader.readLine()) != null) {
                    log.error("STDERR: {}", line);
                }
            }

            int exitStatus = channel.getExitStatus();
            if (exitStatus == 0) {
                log.info("Command executed successfully");
            } else {
                log.error("Command execution failed with exit status: {}", exitStatus);
            }

            channel.disconnect();
            session.disconnect();
            log.info("SFTP session disconnected");
            return exitStatus == 0;
        } catch (Exception e) {
            log.error("Error creating zip file: {}", e.getMessage(), e);
            return false;
        }
    }


    // 단위 변환 로직
    private long parseSizeToKB(String sizeStr) {
        // 숫자와 단위를 분리
        String numberPart = sizeStr.replaceAll("[^0-9.]", ""); // 숫자만 추출
        String unitPart = sizeStr.replaceAll("[0-9.]", "").toUpperCase(); // 단위만 추출 (대문자로 변환)

        // 숫자 부분을 파싱
        double size = Double.parseDouble(numberPart);

        // 단위에 따라 바이트로 변환
        switch (unitPart) {
            case "K":
                return (long) (size * 1024); // KiB to Bytes
            case "M":
                return (long) (size * 1024 * 1024); // MiB to Bytes
            case "G":
                return (long) (size * 1024 * 1024 * 1024); // GiB to Bytes
            case "T":
                return (long) (size * 1024 * 1024 * 1024 * 1024); // TiB to Bytes
            default:
                return (long) size; // 기본적으로 Bytes로 간주
        }
    }


}
