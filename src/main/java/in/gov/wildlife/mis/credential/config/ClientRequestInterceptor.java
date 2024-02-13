package in.gov.wildlife.mis.credential.config;

import in.gov.wildlife.mis.audit.AudiTrailRepository;
import in.gov.wildlife.mis.credential.authentication.jwt.JwtUtil;
import in.gov.wildlife.mis.domian.AuditTrail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 *
 * Custom class for the client intercepter
 * This class intercepts all the request and then add the data in the requestmeta
 * */

public class ClientRequestInterceptor implements HandlerInterceptor {

   private RequestMeta requestMeta;
    @Autowired
    private JwtUtil jwtUtil;

   @Autowired
   private AudiTrailRepository repository;
    public ClientRequestInterceptor(RequestMeta requestMeta) {

        this.requestMeta = requestMeta;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {

        String requestIdHeader = request.getHeader("X-Request-Id");
        requestMeta.setRequestId(requestIdHeader);
        requestMeta.setRemoteAddrs(request.getRemoteAddr());
        requestMeta.setRemoteHost(request.getRemoteHost());
        requestMeta.setRequestURL(request.getRequestURI());
        requestMeta.setRequestMethod(request.getMethod());
        requestMeta.setXForwardFor(request.getHeader("X-Forwarded-For"));
        requestMeta.setJwtToken(request.getHeader("auth"));
        String serviceheader = request.getHeader("authToken");
        requestMeta.setServicePlusHeader(serviceheader);
//         response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        System.out.println(request.getServletPath());

        //Here we will check it the jwt token is already in use or not ;

        if (!request.getRequestURI().equals("/public/login")) {

            AuditTrail auditTrail = new AuditTrail();

            if (Objects.isNull(repository.getSlno()))
                auditTrail.setSlNo(1L);
            else
                auditTrail.setSlNo(repository.getSlno() + 1);

            auditTrail.setRemoteAddress(request.getRemoteAddr());
            String requestJwt = request.getHeader("auth");
            auditTrail.setMethod(request.getMethod());

            auditTrail.setUrl(request.getRequestURI());

            if (Objects.nonNull(requestJwt)) {
                String token = requestJwt.substring(7);
//                System.out.println(jwtUtil.getAllClaimsFromToken(token));
                String userName = jwtUtil.getUsernameFromToken(token);

                auditTrail.setUserName(userName);

            }


            repository.save(auditTrail);
        }
        return true;

    }
}
