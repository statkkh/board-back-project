package com.kkh.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kkh.boardback.entity.CertificationEntity;

@Repository
public interface CertificationRepository extends JpaRepository< CertificationEntity,String>{
    
}
