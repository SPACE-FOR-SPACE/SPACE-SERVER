package com.space.server.oauth.service;

import com.space.server.oauth.service.dto.*;
import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
import com.space.server.user.domain.value.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("google")){
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("naver")){
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("kakao")){
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else{
            return null;
        }

        String type = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();

        Users existData = userRepository.findByEmail(oAuth2Response.getEmail());
        Role role;


        if (existData == null) {
            Users users = Users.socialUserBuilder()
                    .email(oAuth2Response.getEmail())
                    .type(type)
                    .role(Role.GUEST)
                    .build();

            userRepository.save(users);
            role = Role.GUEST;
        } else {

            if (existData.getType().equals("NORMAL")) {
                throw new OAuth2AuthenticationException("Normal user already exists");
            }

            existData.updateSocial(oAuth2Response.getEmail(), type);
            userRepository.save(existData);
            role = existData.getAge() == null ? Role.GUEST : existData.getRole();
        }

        UserDto userDto = UserDto.builder()
                .email(oAuth2Response.getEmail())
                .role(role)
                .build();

        return new CustomOAuth2User(userDto);
    }
}
