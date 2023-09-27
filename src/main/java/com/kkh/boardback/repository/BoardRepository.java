package com.kkh.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kkh.boardback.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    
    // description : nativeQuery - 하나의 테이블사용에 대한 제한을 없애 수정,삭제//
    // description : read : view / cud : 쿼리 직접 작성 
        
        

}
