package com.kkh.boardback.service.implement;

import java.io.File;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kkh.boardback.service.FileService;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String filepath;
    @Value("${file.url}")
    private String fileUrl;
    
    @Override
    public String upload(MultipartFile file) {
        // description : 파일 일 비어있는지 검증 //
        if(file.isEmpty()) return null;
        // description :  원본 파일명 //
        String originFilename = file.getOriginalFilename();
        // description : 파일 확장가 구하기 //
        String extension =originFilename.substring(originFilename.lastIndexOf("."));
        // description :  파일 명 변경 UUID 형식의 임의의 파일명 생성 //
        String uuid = UUID.randomUUID().toString();
        // description : UUID형식의 파일명 + 확장자로 새로운 파일명 생성 //
        String saveFilename = uuid + extension;

        // description : 저장할 경로 생성 //
        String savePath = filepath + saveFilename;

        try {
            file.transferTo(new File(savePath));
            
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFilename;
        return url;

    }

    @Override
    public Resource getFile(String fileName) {

        Resource resource = null;
        
        // description : 파일 저장 경로에서 파일명에 해당하는 파일 불러오기//
        try {
            resource = new UrlResource("file:" + filepath + fileName);    
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return resource;
    }
    

}
