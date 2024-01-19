package com.kkh.boardback.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.request.auth.SignInRequestDto;

import com.kkh.boardback.dto.request.auth.CheckCertificationRequestDto;
import com.kkh.boardback.dto.request.auth.EmailCertificationRequestDto;
import com.kkh.boardback.dto.request.auth.IdCheckRequestDto;

import com.kkh.boardback.dto.response.auth.SignInResponseDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;

import com.kkh.boardback.dto.response.auth.CheckCertificationResponseDto;
import com.kkh.boardback.dto.response.auth.EmailCertificationResponseDto;
import com.kkh.boardback.dto.response.auth.IdCheckResponseDto;
import com.kkh.boardback.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/id-check")
    public ResponseEntity< ? super IdCheckResponseDto> idCheck(
        @RequestBody @Valid IdCheckRequestDto requestBody
    ){
        ResponseEntity< ? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity< ? super EmailCertificationResponseDto> emailCertification(
        @RequestBody @Valid EmailCertificationRequestDto requestBody
    ){
        ResponseEntity< ? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity< ? super CheckCertificationResponseDto> checkCertification(
        @RequestBody @Valid CheckCertificationRequestDto requestBody
    ){
        ResponseEntity< ? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

}
