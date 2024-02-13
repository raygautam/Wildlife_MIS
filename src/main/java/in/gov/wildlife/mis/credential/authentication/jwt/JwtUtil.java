package in.gov.wildlife.mis.credential.authentication.jwt;

import in.gov.wildlife.mis.domian.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    public static final long JWT_TOKEN_VALIDITY =  1; //900000
    public static final long JWT_REFRESH_TOKEN = 5 * 60 * 60;

    private String secret = "myTokenSecret";

    public String getUsernameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {

        final Date expiration = getExpirationDateFromToken(token);

        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails, AppUser user){

        Map<String, Object> claims = new HashMap<>();
        claims.put("name",userDetails.getUsername());
        claims.put("authority",userDetails.getAuthorities());
        if(user.getService()!=null){
            claims.put("service",user.getService());
        }
        if (user.getDivision()!=null){
            claims.put("district",user.getDivision());
        }
        if (user.getRange()!=null){
            claims.put("district",user.getRange());
        }

        // claims.put("roles",userDetails)


        return doGenerateRefreshToken(claims, userDetails.getUsername());
    }

    public String generateRefreshToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();
        claims.put("name",userDetails.getUsername());
        claims.put("authority",userDetails.getAuthorities());
        claims.put("refreshToken","REFRESH_TOKEN");

        // claims.put("roles",userDetails)


        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)

                .setIssuedAt(new Date(System.currentTimeMillis()))
                // This is the main
//                .setExpiration(new Date(System.currentTimeMillis() +600000))
//                .signWith(
//                        SignatureAlgorithm.HS512, secret).compact();
        .setExpiration(new Date(System.currentTimeMillis()+1500000)).signWith(
                SignatureAlgorithm.HS512, secret).compact();


    }


    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1500000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
