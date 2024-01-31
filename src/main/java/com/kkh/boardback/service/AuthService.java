package com.kkh.boardback.service;

import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.request.auth.CheckCertificationRequestDto;
import com.kkh.boardback.dto.request.auth.EmailCertificationRequestDto;
import com.kkh.boardback.dto.request.auth.IdCheckRequestDto;
import com.kkh.boardback.dto.response.auth.CheckCertificationResponseDto;
import com.kkh.boardback.dto.response.auth.EmailCertificationResponseDto;
import com.kkh.boardback.dto.response.auth.IdCheckResponseDto;

import com.kkh.boardback.dto.request.auth.SignInRequestDto;
import com.kkh.boardback.dto.response.auth.SignInResponseDto;
import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    ResponseEntity< ? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity< ? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity< ? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity< ? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity< ? super SignInResponseDto> signIn(SignInRequestDto dto);
    
}

// https://www.youtube.com/watch?v=PnM_IIXvzpg&t=381s

// https://www.youtube.com/watch?v=Qtv-3rq5K2U&t=142s