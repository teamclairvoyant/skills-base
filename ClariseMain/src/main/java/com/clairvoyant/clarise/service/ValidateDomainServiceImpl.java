package com.clairvoyant.clarise.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.clairvoyant.clarise.model.AccessToken;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ValidateDomainServiceImpl implements ValidateDomainService {

    @Value("${app.fixedDomain}")
    private String fixedDomain;

    @Value("${app.authorizedUrl}")
    private String authorizedUrl;

    @Value("${app.unAuthorizedUrl}")
    private String unAuthorizedUrl;

    @Autowired
    UserRepository userRepository;


    @Override
    public void checkDomain(OidcIdToken idToken, HttpServletResponse httpResponse) throws IOException {

        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(idToken.getTokenValue());

        DecodedJWT jwt = JWT.decode(idToken.getTokenValue());
        //System.out.println("IDTOKEN---------------------------"+idToken.getEmail());
        //fixedDomain = clairvoyantsoft.com;
        if(fixedDomain.equals(jwt.getClaim("hd").asString()))
        {
            //if domain is authorized redirect to homepage
            httpResponse.sendRedirect(authorizedUrl + idToken.getTokenValue());
        }
        else{
            //if domain is unauthorized redirect to login page again
            httpResponse.sendRedirect(unAuthorizedUrl + 1001);
        }
    }
}
