package com.kkh.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kkh.boardback.dto.request.auth.SignInRequestDto;
import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.response.auth.SignInResponseDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.entity.UserEntity;
import com.kkh.boardback.provider.JwtProvider;
import com.kkh.boardback.service.AuthService;
import com.kkh.boardback.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;   
    private final JwtProvider jwtProvider; 

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        
        try {

            String email = dto.getEmail();
            String nickname = dto.getNickname();
            String telNumber = dto.getTelNumber();
            // description : 이메일중복,닉네임 중복  전화번호중복, 비밀번호 중복 //
            boolean hasEmail = userRepository.existsByEmail(email);
             if(hasEmail) return SignUpResponseDto.duplicatedEmail();
             
            boolean hasTelnumber = userRepository.existsByTelNumber(telNumber);
             if(hasTelnumber) return SignUpResponseDto.duplicatedTelNumber();
             
            boolean hasNickname = userRepository.existsByNickname(nickname);
            if(hasNickname) return SignUpResponseDto.duplicatedNickname();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);

            // description: 비밀번호를 평문으로 된  암호화된 코드로 변경 //
            dto.setPassword(encodedPassword);
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        
        return SignUpResponseDto.success(); 
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
        // ! password 암호화된 password 평문의 password인지 비교  //
        try {
            
            String email = dto.getEmail();

            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return SignInResponseDto.signInfailed();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();

            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInfailed();

            token = jwtProvider.create(email);            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }

}
