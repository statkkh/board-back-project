package com.kkh.boardback.dto.response.file;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.response.ResponseCode;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.dto.response.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckResponesDto extends ResponseDto{

    public CheckResponesDto(String code, String message){
        super(code, message);
        
    }

    public static ResponseEntity<CheckResponesDto> success(){
        CheckResponesDto  result = new CheckResponesDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }    
}
