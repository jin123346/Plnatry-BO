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

    private static final String BASE_SFTP_DIR = "/data/sftp/uploads/";

    //user별 sftpUser 생성
    public void createUserFolder(String username) {
        File userFolder = new File(BASE_SFTP_DIR + username);
        if (!userFolder.exists()) {
            if (userFolder.mkdirs()) {
                System.out.println("Folder created for user: " + username);
            } else {
                System.err.println("Failed to create folder for user: " + username);
            }
        } else {
            System.out.println("Folder already exists for user: " + username);
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


}
