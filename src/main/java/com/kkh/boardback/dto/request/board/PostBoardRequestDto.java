package com.kkh.boardback.dto.request.board;

import javax.validation.constraints.NotBlank;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostBoardRequestDto {
    
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NonNull
    private List<String>boardImageList; 
    
}
