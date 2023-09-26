package com.kkh.boardback.controller;

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
}
