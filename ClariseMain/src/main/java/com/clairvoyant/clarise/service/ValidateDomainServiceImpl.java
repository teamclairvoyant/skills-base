package com.clairvoyant.clarise.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.clairvoyant.clarise.model.Employee;
import com.clairvoyant.clarise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Service
public class ValidateDomainServiceImpl implements ValidateDomainService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Value("${app.fixedDomain}")
    private String fixedDomain;
    @Value("${app.authorizedUrl}")
    private String authorizedUrl;
    @Value("${app.unAuthorizedUrl}")
    private String unAuthorizedUrl;
    @Value("${app.superAdminId}")
    private String superAdminId;
    @Value("${app.defaultRole}")
    private String defaultRole;
    @Value("${app.superAdminRole}")
    private String superAdminRole;
    @Value("${app.insertQuery}")
    private String insertQuery;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void checkDomain(OidcIdToken idToken, HttpServletResponse httpResponse) throws IOException {

        //AccessToken accessToken = new AccessToken();
        //accessToken.setAccessToken(idToken.getTokenValue());

        DecodedJWT jwt = JWT.decode(idToken.getTokenValue());
        //fixedDomain = clairvoyantsoft.com;
        if (fixedDomain.equals(jwt.getClaim("hd").asString())) {
            //if domain is authorized redirect to homepage
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            //defaultRole = ROLE_USER
            String role = defaultRole;
            //superAdminId = clarise@clairvoyantsoft.com
            if (superAdminId.equals(idToken.getEmail())) {
                // superAdminRole = ROLE_SUPERADMIN
                role = superAdminRole;
            }
            JwtUtil customToken = new JwtUtil();
            String token = customToken.generateToken(idToken.getTokenValue(), role);
            //System.out.println("Custom token---------------------"+token + " Custom token end");
            Employee employee = employeeRepository.findByEmail(idToken.getEmail());
            if (employee == null) {
                String query = insertQuery;
                jdbcTemplate.update(query, uuidAsString, idToken.getEmail(), idToken.getFullName(), role);
            }
            //httpResponse.sendRedirect(authorizedUrl + idToken.getTokenValue()+"&role="+role);
            httpResponse.sendRedirect(authorizedUrl + token);
        } else {
            //if domain is unauthorized redirect to login page again
            httpResponse.sendRedirect(unAuthorizedUrl + 1001);
        }
    }
}
