package com.kkh.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kkh.boardback.dto.request.auth.IdCheckRequestDto;
import com.kkh.boardback.dto.request.auth.SignInRequestDto;
import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.response.auth.IdCheckResponseDto;
import com.kkh.boardback.dto.response.auth.SignInResponseDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.entity.UserEntity;
import com.kkh.boardback.provider.JwtProvider;
import com.kkh.boardback.service.AuthService;
import com.kkh.boardback.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;   
    private final JwtProvider jwtProvider; 

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {

        try {
            String userId = dto.getId();
            boolean existId = userRepository.existsById(userId);
            if(existId) return IdCheckResponseDto.duplicateId();            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IdCheckResponseDto.success();
    }
    




}
