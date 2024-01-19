package com.kkh.boardback.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.response.ResponseCode;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class EmailCertificationResponseDto extends ResponseDto{
    
    private EmailCertificationResponseDto(String code, String message){
        super(code, message);
    }

    public static ResponseEntity<EmailCertificationResponseDto> success(){
        EmailCertificationResponseDto responseBody = new EmailCertificationResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    public static ResponseEntity<ResponseDto> duplicateId(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_ID, ResponseMessage.DUPLICATED_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> mailSendFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
