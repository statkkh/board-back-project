package com.kkh.boardback.service;

import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.response.user.GetSignInUserResponseDto;

public interface UserService {
    
    ResponseEntity< ? super  GetSignInUserResponseDto> getSignInUser(String email);
    
}
