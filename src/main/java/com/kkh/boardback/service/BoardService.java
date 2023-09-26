package com.kkh.boardback.service;

import org.springframework.http.ResponseEntity;

import com.kkh.boardback.dto.request.board.PostBoardRequestDto;
import com.kkh.boardback.dto.response.board.PostBoardResponseDto;

public interface BoardService {

    ResponseEntity< ? super PostBoardResponseDto > postBoard(PostBoardRequestDto dto, String email);

}
