package com.kkh.boardback.service;

import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.request.auth.EmailCertificationRequestDto;
import com.kkh.boardback.dto.request.auth.IdCheckRequestDto;
import com.kkh.boardback.dto.request.auth.SignInRequestDto;
import com.kkh.boardback.dto.response.auth.EmailCertificationResponseDto;
import com.kkh.boardback.dto.response.auth.IdCheckResponseDto;
import com.kkh.boardback.dto.response.auth.SignInResponseDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    ResponseEntity< ? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity< ? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
}
