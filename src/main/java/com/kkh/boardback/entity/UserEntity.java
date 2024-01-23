package com.kkh.boardback.entity;

    

import com.kkh.boardback.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
// ! table 작업
public class UserEntity {
    @Id
    private String userId;
    private String password;
    private String email;
    private String type;
    private String role;
    
    public UserEntity(String userId,String email, String type){
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.type = type;
        this.role = "ROLE_USER";
    }

}
