package com.kkh.boardback.service.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kkh.boardback.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

import java.net.URLConnection;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        // description : 파일 일 비어있는지 검증 //
        if(file.isEmpty()) return null;
        // description :  원본 파일명 //
        String originFilename = file.getOriginalFilename();
        // description : 파일 확장가 구하기 //
        String extension = originFilename.substring(originFilename.lastIndexOf("."));
        // description :  파일 명 변경 UUID 형식의 임의의 파일명 생성 //
        String uuid = UUID.randomUUID().toString();
        // description : UUID형식의 파일명 + 확장자로 새로운 파일명 생성 //
        String saveFileName = uuid + extension;
        // description : 저장할 경로 생성 //
        String savePath = filePath + saveFileName;

        try {
            file.transferTo(new File(savePath));
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFileName;
        return url;

    }

    @Override
    public Resource getFile(String fileName) {

        Resource resource = null;
        
        // description : 파일 저장 경로에서 파일명에 해당하는 파일 불러오기//
        try {
            resource = new UrlResource("file:" + filePath + fileName);    
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return resource;
    }


    @Override
    public void download(String fileUrl,HttpServletResponse response) {

        File file = new File(filePath + fileUrl);

        try {
            FileInputStream fis = new FileInputStream(file);    
            
            // 다운로드할 파일의 이름과 MIME 타입 설정
            String fileName = file.getName();
            String mimeType = URLConnection.guessContentTypeFromName(fileName);

            // MIME 타입이 알 수 없는 경우 기본 타입 설정
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            
            // 클라이언트에게 파일 다운로드 응답 설정
            response.setContentType(mimeType);
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");            

            // 파일을 클라이언트에 전송
            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            // 스트림 닫기
            fis.close();
            os.close();            

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }




    

    
    

}
