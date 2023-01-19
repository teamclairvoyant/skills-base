package com.clairvoyant.clarise.service;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ValidateDomainService {

    public void checkDomain(OidcIdToken idToken, HttpServletResponse httpResponse) throws IOException;
}
