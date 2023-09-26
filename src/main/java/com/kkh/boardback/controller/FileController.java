package com.kkh.boardback.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kkh.boardback.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    // 파일 서비스 구현되지 않아도 사용 가능 추상화 의 장점
    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(
        @RequestParam("file") MultipartFile file 
    ){
        String url = fileService.upload(file);
        return url;
    }

    // description : 파일 불러오기 
    @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getFile(
        @PathVariable("fileName") String fileName
    ){  
        Resource resource = fileService.getFile(fileName);
        return resource;
    } 


}
