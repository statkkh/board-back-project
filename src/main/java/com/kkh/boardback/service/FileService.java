package com.kkh.boardback.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;

public interface FileService {
    
    String upload(MultipartFile file);
    Resource getFile(String fileName);
    

    void download(String fileUrl, HttpServletResponse response);

    
}
