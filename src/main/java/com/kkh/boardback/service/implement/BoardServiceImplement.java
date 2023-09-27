package com.kkh.boardback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kkh.boardback.dto.request.board.PostBoardRequestDto;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.dto.response.board.PostBoardResponseDto;
import com.kkh.boardback.entity.BoardEntity;
import com.kkh.boardback.entity.BoardImageEntity;
import com.kkh.boardback.repository.BoardImageRepository;
import com.kkh.boardback.repository.BoardRepository;
import com.kkh.boardback.repository.UserRepository;
import com.kkh.boardback.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;

    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto,String email) {
        
        try {
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PostBoardResponseDto.notExistUser() ;   
            
            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            List<String>boardImageList = dto.getBoardImageList();
            Integer boardNumber = boardEntity.getBoardNumber();

            List<BoardImageEntity> boardImageEntities = new ArrayList<>();
            for(String boardImage: boardImageList ){
                BoardImageEntity boardImageEntity = new BoardImageEntity(boardNumber,boardImage );
                boardImageEntities.add(boardImageEntity);
            }
            // collection framework
            boardImageRepository.saveAll(boardImageEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }
    
}
