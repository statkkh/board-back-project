package com.kkh.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kkh.boardback.entity.UserEntity;

@Repository
public interface UserRepository  extends  JpaRepository< UserEntity, String >{
    // code message
    boolean existByEmail(String email);
    boolean existByNickname(String nickname);
    boolean existsByTelNumber(String telNumber);
    
    
    
}
