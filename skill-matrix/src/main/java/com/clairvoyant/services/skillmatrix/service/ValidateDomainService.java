package com.clairvoyant.services.skillmatrix.service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

public interface ValidateDomainService {

    void checkDomain(OidcIdToken idToken, HttpServletResponse httpResponse) throws IOException;
}
