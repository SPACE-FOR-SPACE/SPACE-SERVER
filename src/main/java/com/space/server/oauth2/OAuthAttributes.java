package com.space.server.oauth2;

import com.space.server.oauth2.userinfo.GoogleOAuth2UserInfo;
import com.space.server.oauth2.userinfo.KakaoOAuth2UserInfo;
import com.space.server.oauth2.userinfo.NaverOAuth2UserInfo;
import com.space.server.oauth2.userinfo.OAuth2UserInfo;
import com.space.server.user.domain.User;
import com.space.server.user.domain.value.Role;
import com.space.server.user.domain.value.SocialType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;


@Getter
public class OAuthAttributes {

    private String nameAttributeKey;
    private OAuth2UserInfo oauth2UserInfo;

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }


    public static OAuthAttributes of(SocialType socialType,
                                     String userNameAttributeName, Map<String, Object> attributes) {

        if (socialType == SocialType.NAVER) {
            return ofNaver(userNameAttributeName, attributes);
        }
        if (socialType == SocialType.KAKAO) {
            return ofKakao(userNameAttributeName, attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new NaverOAuth2UserInfo((Map<String, Object>) attributes.get("response")))
                .build();
    }


    public User toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
        return User.socialUserBuilder()
                .socialId(oauth2UserInfo.getId())
                .email(UUID.randomUUID() + "@socialUser.com")
                .socialType(socialType)
                .nickname(oauth2UserInfo.getNickname())
                .role(Role.GUEST)
                .build();
    }
}