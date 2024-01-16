package com.kkh.boardback.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kkh.boardback.dto.request.auth.SignUpRequestDto;

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
    
    // public UserEntity(SignUpRequestDto dto){
    //     this.email = dto.getEmail();
    //     this.password = dto.getPassword();
    //     this.nickname = dto.getNickname();
    //     this.telNumber = dto.getTelNumber();
    //     this.address = dto.getAddress();
    //     this.addressDetail = dto.getAddressDetail();
    //     this.agreedPersonal =dto.getAgreedPersonal();
    // }
}
