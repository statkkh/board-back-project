package com.kkh.boardback.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kkh.boardback.dto.request.board.PostBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="board")
@Table(name ="board")
public class BoardEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int boardNumber;
    private String title;
    private String contents;
    private String writeDatetime;
    private int viewCount;
    private int commentCount;
    private int favoriteCount;
    private String writerEmail;

    public BoardEntity(PostBoardRequestDto dto, String email){
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDateTime = simpleDateFormat.format(now);

        this.title = dto.getTitle();
        this.contents = dto.getContent();
        this.writeDatetime = writeDateTime;
        this.viewCount = 0;
        this.commentCount = 0;
        this.favoriteCount = 0;
        this.writerEmail = email;
    }
}
