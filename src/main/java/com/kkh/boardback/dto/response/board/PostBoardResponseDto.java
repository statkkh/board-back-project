package com.kkh.boardback.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.response.ResponseCode;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class PostBoardResponseDto extends ResponseDto{

    private PostBoardResponseDto(String code , String message){
        super(code, message);
    }

    public static ResponseEntity<PostBoardResponseDto> success(){
        PostBoardResponseDto result = new PostBoardResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        ;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body(result);
    }

}
