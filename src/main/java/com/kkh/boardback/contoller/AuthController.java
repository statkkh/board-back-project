package com.kkh.boardback.contoller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;
import com.kkh.boardback.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto > signUp(
        @RequestBody @Valid SignUpRequestDto requestbody
    ){
        ResponseEntity<? super SignUpResponseDto > response = authService.signUp(requestbody);
        return response;
    }




    
}
