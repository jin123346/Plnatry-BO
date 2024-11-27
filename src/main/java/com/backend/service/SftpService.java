package com.backend.service;


import com.jcraft.jsch.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Log4j2
@Service
@RequiredArgsConstructor
public class SftpService {

    @Value("${file-server.url}")
    private String SFTP_HOST;
    private static final int SFTP_PORT = 22;
    private static final String SFTP_USER = "sftpuser";
    private static final String SFTP_PASSWORD = "Lotte4!@12";

    private static final String BASE_SFTP_DIR = "/uploads/";

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

            // chmod 실행 (JSch Exec 명령 사용)
            String command = String.format("chmod 755 %s", remoteDir);
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);
            channelExec.connect();
            int exitStatus = channelExec.getExitStatus();
            channelExec.disconnect();

            if (exitStatus != 0) {
                log.error("Failed to set permissions for folder: {}", remoteDir);
                return false;
            }

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

    public void uploadFile(String username, String localFilePath, String remoteFilePath) throws JSchException, SftpException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASSWORD);

        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        // 사용자별 경로 설정
        String remoteDir = "/data/sftp/uploads/" + username + "/";
        channelSftp.put(localFilePath, remoteDir + remoteFilePath);

        channelSftp.disconnect();
        session.disconnect();

        System.out.println("File uploaded successfully to " + remoteDir + remoteFilePath);
    }

    public void downloadFile(String username, String remoteFilePath, String localFilePath) throws JSchException, SftpException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASSWORD);

        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        // 사용자별 경로 설정
        String remoteDir = "/data/sftp/uploads/" + username + "/";
        channelSftp.get(remoteDir + remoteFilePath, localFilePath);

        channelSftp.disconnect();
        session.disconnect();

        System.out.println("File downloaded successfully from " + remoteDir + remoteFilePath);
    }

    public boolean createFolder(String folderName) {
        String remoteFolderPath = BASE_SFTP_DIR + folderName;

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            Channel channel = session.openChannel("exec");
            ChannelExec channelExec = (ChannelExec) channel;

            // 폴더 생성 명령
            String command = String.format("mkdir -p %s && chown %s:%s %s", remoteFolderPath, SFTP_USER, SFTP_USER, remoteFolderPath);
            channelExec.setCommand(command);

            channelExec.connect();

            int exitStatus = channelExec.getExitStatus();
            channelExec.disconnect();
            session.disconnect();

            if (exitStatus == 0) {
                log.info("Folder created successfully: {}", remoteFolderPath);
                return true;
            } else {
                log.error("Failed to create folder. Exit status: {}", exitStatus);
                return false;
            }
        } catch (Exception e) {
            log.error("Error while creating folder: {}", e.getMessage());
            return false;
        }
    }

    public boolean createNestedFoldersWithCommand(String nestedPath) {
        String remoteDir = BASE_SFTP_DIR + nestedPath; // 절대 경로로 조정
        String command = String.format("mkdir -p %s", remoteDir);

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);

            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);

            log.info("Executing command: {}", command);

            channelExec.connect();
            int exitStatus = channelExec.getExitStatus();
            channelExec.disconnect();
            session.disconnect();

            if (exitStatus == 0) {
                log.info("Nested folders created successfully with command: {}", remoteDir);
                return true;
            } else {
                log.error("Failed to create nested folders. Exit status: {}", exitStatus);
                return false;
            }
        } catch (Exception e) {
            log.error("Failed to create nested folders: {}", e.getMessage());
            return false;
        }
    }



}
