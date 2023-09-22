package com.kkh.boardback.service;

import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;


public interface AuthService {
    // ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);  
    ResponseEntity< ? super SignUpResponseDto> signUp(SignUpRequestDto dto);
}
