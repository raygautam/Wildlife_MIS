package in.gov.wildlife.mis.credential.authentication.jwt;


import in.gov.wildlife.mis.common.Constants;
import in.gov.wildlife.mis.common.MessageByLocale;
import in.gov.wildlife.mis.credential.authentication.AppUserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserServiceImpl userDetailsService;


    @Autowired
    private final MessageByLocale messageByLocale;

    public CustomAuthorizationFilter(JwtUtil jwtUtil, AppUserServiceImpl userDetailsService, MessageByLocale messageByLocale) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.messageByLocale = messageByLocale;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username=null;
        String jwtToken=null;

        // we will check if the request is made to the urls that have permit all access because here we dont need to
        // check anything
        if (request.getServletPath().startsWith("/public")) {
            filterChain.doFilter(request, response);
        } else {
//            response.addHeader("Access-Control-Allow-Origin", "*");
            String authorizationHeader = request.getHeader("Auth");
//            System.out.println("auth"+authorizationHeader);
            Enumeration<String> headerNames = request.getHeaderNames();
//            System.out.println(headerNames);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                jwtToken = authorizationHeader.substring(7);
//                System.out.println(jwtToken +"jwtToken");

                try{
                    username = this.jwtUtil.getUsernameFromToken(jwtToken);
//                    log.info("Claims",jwtUtil.getAllClaimsFromToken(jwtToken));
//                    Claims claims = jwtUtil.getAllClaimsFromToken(jwtToken);
                } catch(IllegalArgumentException e) {
                    throw new BadRequestException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.BAD_REQUEST));

                } catch(ExpiredJwtException e) {

                    AccessDeniedException accessDeniedException = new AccessDeniedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.ACCESS_DENIED));
                    throw  accessDeniedException;
                } catch(MalformedJwtException e) {
                    throw new BadRequestException(messageByLocale.getMessage("malformed-jwt-token"));

                }
            }else {

                throw  new AccessDeniedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.ACCESS_DENIED));
            }
            //validate token
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                // to get the authorities if implemented in a different way
                String authorities = userDetails.getAuthorities().toString();
                if(this.jwtUtil.validateToken(jwtToken, userDetails)){

                    //authenticate
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }else {

                    throw  new AccessDeniedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.ACCESS_DENIED));
                }

            }else {

                throw  new AccessDeniedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.ACCESS_DENIED));
            }

            filterChain.doFilter(request, response);

        }

    }
}
