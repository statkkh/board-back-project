package com.kkh.boardback.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.boardback.dto.request.board.PostBoardRequestDto;
import com.kkh.boardback.dto.response.board.PostBoardResponseDto;
import com.kkh.boardback.service.BoardService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
        @RequestBody @Valid PostBoardRequestDto reqestBody,
        @AuthenticationPrincipal String email
        ) {
            ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(reqestBody, email);
        return response;
    }




}
