package com.space.server.oauth2.userinfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {

        if (attributes == null) {
            return null;
        }
        return (String) attributes.get("id");
    }

    @Override
    public String getNickname() {

        if (attributes == null) {
            return null;
        }

        return (String) attributes.get("nickname");
    }

}