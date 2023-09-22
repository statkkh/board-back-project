package com.kkh.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kkh.boardback.service.AuthService;

import lombok.RequiredArgsConstructor;

import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;
import com.kkh.boardback.entity.UserEntity;
import com.kkh.boardback.repository.UserRepository;
@RequiredArgsConstructor
@Service
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    


    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        


        try {
            String email = dto.getEmail();
            String nickname = dto.getNickname();
            String telNumber = dto.getTelNumber();
            // description : 이메일중복, 전화번호중복, 비밀번호 중복 //
             boolean hasEmail = userRepository.existByEmail(email);
             if(hasEmail) return SignUpResponseDto.duplicatedEmail();
             
             boolean hasTelnumber = userRepository.existsByTelNumber(telNumber);
             if(hasTelnumber) return SignUpResponseDto.duplicatedTelNumber();

             
            boolean hasNickname = userRepository.existByNickname(nickname);
            if(hasNickname) return SignUpResponseDto.duplicatedNickname();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);

            // ! 암호화된 코드로  
            dto.setPassword(encodedPassword);
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);





        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success(); 
    }


}
