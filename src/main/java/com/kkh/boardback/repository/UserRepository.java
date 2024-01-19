package com.kkh.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kkh.boardback.entity.UserEntity;


@Repository
public interface UserRepository  extends  JpaRepository< UserEntity, String>{
    // code message
    boolean existsByUserId(String userId);
    
    UserEntity findByUserId(String userId);
    
    UserEntity findByEmail(String email);


    
}
