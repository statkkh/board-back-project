package com.kkh.boardback.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.response.ResponseCode;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.dto.response.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpResponseDto  extends ResponseDto{

    public SignUpResponseDto(String code, String message){
        super(code, message);
    }
    // description :모든 성공과 실패에 대해서 언급 유지 //
    public static ResponseEntity<SignUpResponseDto> success(){
        SignUpResponseDto result = new SignUpResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    public static ResponseEntity<ResponseDto> duplicatedEmail(){
        ResponseDto  result = new ResponseDto(ResponseCode.DUPLICATED_EMAIL, ResponseMessage.DUPLICATED_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicatedPassword(){
        ResponseDto  result  = new ResponseDto(ResponseCode.DUPLICATED_PASSWORD, ResponseMessage.DUPLICATED_PASSWORD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
    public static ResponseEntity<ResponseDto> duplicatedId(){
        ResponseDto  result  = new ResponseDto(ResponseCode.DUPLICATED_ID, ResponseMessage.DUPLICATED_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> certificationFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }    
}
