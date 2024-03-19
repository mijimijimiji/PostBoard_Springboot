package com.example.editer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class PhotoUtil {

    public static String ckUpload(MultipartHttpServletRequest request) {

        MultipartFile uploadFile = request.getFile("upload");
        System.out.println("PhotoUtil에서 uploadFile:::::::::::::"+uploadFile);
        
        //파일 이름을 뽑고
        String fileName = getFileName(uploadFile);
        System.out.println("PhotoUtil에서 fileName:::::::::::::"+fileName);
        
        String realPath = getPath(request);
        System.out.println("PhotoUtil에서 realPath:::::::::::::"+realPath);

        String savePath = realPath + fileName;
        System.out.println("PhotoUtil에서 savePath:::::::::::::"+savePath);

        String uploadPath = request.getContextPath() + "/webapp/upload/upload/" + fileName;
        System.out.println("PhotoUtil에서 uploadPath:::::::::::::"+uploadPath);

        uploadFile(savePath, uploadFile);

        return uploadPath;
    }

    private static void uploadFile(String savePath, MultipartFile uploadFile) {
        File file = new File(savePath);
        try {
            uploadFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload the file", e);
        }
    }

    private static String getFileName(MultipartFile uploadFile) {
        String originalFileName = uploadFile.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID() + ext;
    }

    private static String getPath(MultipartHttpServletRequest request) {
        // 실제 파일 저장 경로
    	String realPath = request.getServletContext().getRealPath("/upload/");
    	
        Path directoryPath = Paths.get(realPath);
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                throw new RuntimeException("Could not create upload directory", e);
            }
        }
        return realPath;
    }
    
}