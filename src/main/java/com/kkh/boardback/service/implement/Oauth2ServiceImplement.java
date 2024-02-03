package com.kkh.boardback.service.implement;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkh.boardback.entity.CustomOAuth2User;
import com.kkh.boardback.entity.UserEntity;
import com.kkh.boardback.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class Oauth2ServiceImplement extends DefaultOAuth2UserService{

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();
        UserEntity userEntity = null;
        
        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
        
        String userId = null;
        String email = "email@email.com";

        if(oauthClientName.equals("kakko") ){
            userId = "kakkao_" + oAuth2User.getAttributes().get("id");
            userEntity = new UserEntity(userId, "email@email.com", "kakko");
        }

        if(oauthClientName.equals("naver") ){
            Map<String, String> responseMap =  (Map<String, String>) oAuth2User.getAttributes().get("response");
            userId = "naver_" + responseMap.get("id").substring(0, 14);
            email = responseMap.get("email");
            userEntity = new UserEntity(userId, email, "naver");
        }

        userRepository.save(userEntity);


        return new CustomOAuth2User(userId);
        
    }


}
