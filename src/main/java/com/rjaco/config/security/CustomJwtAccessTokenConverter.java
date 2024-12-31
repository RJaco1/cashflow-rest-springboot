package com.rjaco.config.security;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rjaco.model.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, ?> additionalInfo = accessToken.getAdditionalInformation();
        Map<String, Object> info = new LinkedHashMap<>(additionalInfo);

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            info.put("user_id", ((UserAccount) userDetails).getUserId());
        } else {
            info.put("user_id", authentication.getName());
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return super.enhance(accessToken, authentication);
    }

}
