package com.kkh.boardback.dto.response.user;

import java.net.ResponseCache;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.response.ResponseCode;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.dto.response.ResponseMessage;
import com.kkh.boardback.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDto  extends ResponseDto{
    
    private String email;
    private String nickname;
    private String profileImage;
    // 보안과 관련 dto 직접 생성x
    private GetUserResponseDto(String code, String message, UserEntity userEntity){
        super(code, message);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImageUrl();
    }

    public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity){

        GetUserResponseDto result = new GetUserResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExist(UserEntity userEntity){
        
        ResponseDto result = new  ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
   
}
