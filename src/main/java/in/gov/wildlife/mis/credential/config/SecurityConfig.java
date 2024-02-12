package in.gov.wildlife.mis.credential.config;

import in.gov.wildlife.mis.credential.authentication.jwt.CustomAuthorizationFilter;
import in.gov.wildlife.mis.credential.authentication.jwt.JWTAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    CustomAuthorizationFilter customAuthorizationFilter;

    @Autowired
    JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
}
