package com.kkh.boardback.service.implement;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kkh.boardback.common.CertificationNumber;
import com.kkh.boardback.dto.request.auth.CheckCertificationRequestDto;
import com.kkh.boardback.dto.request.auth.EmailCertificationRequestDto;
import com.kkh.boardback.dto.request.auth.IdCheckRequestDto;
import com.kkh.boardback.dto.request.auth.SignInRequestDto;
import com.kkh.boardback.dto.request.auth.SignUpRequestDto;
import com.kkh.boardback.dto.response.auth.CheckCertificationResponseDto;
import com.kkh.boardback.dto.response.auth.EmailCertificationResponseDto;
import com.kkh.boardback.dto.response.auth.IdCheckResponseDto;
import com.kkh.boardback.dto.response.auth.SignInResponseDto;
import com.kkh.boardback.dto.response.auth.SignUpResponseDto;
import com.kkh.boardback.dto.response.ResponseDto;
import com.kkh.boardback.entity.CertificationEntity;
import com.kkh.boardback.entity.UserEntity;
import com.kkh.boardback.provider.EmailProvider;
import com.kkh.boardback.provider.JwtProvider;
import com.kkh.boardback.service.AuthService;
import com.kkh.boardback.repository.CertificationRepository;
import com.kkh.boardback.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    
    private final JwtProvider jwtProvider; 

    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {

        try {
            String userId = dto.getId();
            boolean existId = userRepository.existsById(userId);
            if(existId) return IdCheckResponseDto.duplicateId();            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        
        try {
            
            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return EmailCertificationResponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccess = emailProvider.sendCertificationMail(email, certificationNumber);
            if(!isSuccess) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(userId, email,  certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        
        try {
            
            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            if(certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) CheckCertificationResponseDto.certificationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        
        try {
            
            String userId = dto.getId();
            boolean existedByUserId = userRepository.existsByUserId(userId);
            if(!existedByUserId) return SignUpResponseDto.duplicatedId();

            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
            
            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            
            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return SignUpResponseDto.certificationFail();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);
            // UserEntity userEntity = new UserEntity(dto);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        String token = null;

        try {

            String userId = dto.getId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return SignInResponseDto.signInfailed();

            String password = dto.getPassword();
            String encodedpassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedpassword);
            if(!isMatched)  return SignInResponseDto.signInfailed();

            token = jwtProvider.create(userId);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(token);
    }
    




}


