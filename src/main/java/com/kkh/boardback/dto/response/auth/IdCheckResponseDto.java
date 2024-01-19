package com.kkh.boardback.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.response.ResponseCode;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class IdCheckResponseDto extends ResponseDto{
    
    private IdCheckResponseDto(String code, String message){
        super(code, message);
    }

    public static ResponseEntity<ResponseDto> success(){
        IdCheckResponseDto responseBody = new IdCheckResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicateId(){
        IdCheckResponseDto responseBody = new IdCheckResponseDto(ResponseCode.DUPLICATED_ID, ResponseMessage.DUPLICATED_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
