package in.gov.wildlife.mis.credential.authentication;

import in.gov.wildlife.mis.audit.AudiTrailRepository;
import in.gov.wildlife.mis.common.ApiResponse;
import in.gov.wildlife.mis.common.Constants;
import in.gov.wildlife.mis.common.MessageByLocale;
import in.gov.wildlife.mis.credential.authentication.dto.AppUserDTO;
import in.gov.wildlife.mis.credential.authentication.dto.ChangePasswordDTO;
import in.gov.wildlife.mis.credential.authentication.dto.LoginRequestDTO;
import in.gov.wildlife.mis.credential.authentication.dto.LoginResponseDTO;
import in.gov.wildlife.mis.credential.authentication.jwt.CustomLoginFailureHandler;
import in.gov.wildlife.mis.credential.authentication.jwt.JwtUtil;
import in.gov.wildlife.mis.credential.config.RequestMeta;
import in.gov.wildlife.mis.domian.AuditTrail;
import in.gov.wildlife.mis.exception.AccessDeniedException;
import in.gov.wildlife.mis.exception.ResourceNotFoundException;
import in.gov.wildlife.mis.exception.UnauthourizedException;
import in.gov.wildlife.mis.helper.RSAKeyPairGenerator;
import in.gov.wildlife.mis.helper.RSAUtil;
import in.gov.wildlife.mis.domian.AppUser;
import in.gov.wildlife.mis.domian.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserRepository userRepo;
    @Autowired
    private MessageByLocale messageByLocale;

    @Autowired
    private RSAKeyPairGenerator rsaKeyPairGenerator;

    @Autowired
    private AudiTrailRepository audiTrailRepository;

    @Autowired
    private RequestMeta requestMeta;

    @Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;

    @Autowired
    UserService userService;

    public ApiResponse userLogin(LoginRequestDTO loginRequestDTO,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse response) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, ServletException, IOException {


//        log.info("inside login");
//        log.info(Base64.getEncoder().encodeToString(rsaKeyPairGenerator.getPrivateKey().getEncoded())) ;
//        log.info("Public key");
//        log.info(Base64.getEncoder().encodeToString(rsaKeyPairGenerator.getPublicKey().getEncoded()) );
        String name = loginRequestDTO.getUserName();
        String decryptPassword = RSAUtil.decrypt(loginRequestDTO.getPassword(), Base64.getEncoder().encodeToString(rsaKeyPairGenerator.getPrivateKey().getEncoded()));
//        log.info("Decrypted Password" + decryptPassword);

        this.authenticate(loginRequestDTO.getUserName(), decryptPassword, httpServletRequest, response);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginRequestDTO.getUserName());

        AppUser user = this.userRepo.findByUserNameAndIsActive(name, Boolean.TRUE);
        if(!userService.unlockWhenTimeExpired(user)){
            throw new AccessDeniedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.ACCOUNT_LOCKED));
        }


            if (Objects.nonNull(user) && user.getAccountNonLocked()) {
                if (user.getFailedAttempt() > 0)
                    user.setFailedAttempt(0);

                Set<Role> roles = new HashSet<>(user.getRoles());
                List<Role> rolesList = new ArrayList<>();
                rolesList.addAll(roles);
                String token = this.jwtUtil.generateToken(userDetails,user);

                String refreshToken = this.jwtUtil.generateRefreshToken(userDetails);


                //Audit trial for login
                AuditTrail auditTrail = new AuditTrail();
                if (Objects.isNull(audiTrailRepository.getSlno()))
                    auditTrail.setSlNo(1L);
                else
                    auditTrail.setSlNo(audiTrailRepository.getSlno() + 1);

                auditTrail.setRemoteAddress(requestMeta.getRemoteAddrs());

                auditTrail.setMethod(requestMeta.getRequestMethod());

                auditTrail.setUrl(requestMeta.getRequestURL());

                auditTrail.setUserName(loginRequestDTO.getUserName());
                audiTrailRepository.save(auditTrail);

                LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
                loginResponseDTO.setToken(token);
                loginResponseDTO.setRefreshToken(refreshToken);
                AppUserDTO appUserDTO = new AppUserDTO();

                loginResponseDTO.setUser(appUserDTO.convertToDTO(user));

//        response.setRole(rolesList.stream().map(roles1 -> roles1.getName()).collect(Collectors.toList()));
                // rolesList.forEach((role)-> response.setRole(role.getRolename().substring(5)));

                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setData(loginResponseDTO).setStatus(apiResponse.getStatus());
                return apiResponse;
            }

        else

    {
        throw new AccessDeniedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.ACCOUNT_LOCKED));
    }

}

    private void authenticate(String username, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        }catch(BadCredentialsException e) {

            customLoginFailureHandler.onAuthenticationFailure(request,response,e);
            throw new AccessDeniedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.ACCESS_DENIED));
        }
    }


    public ApiResponse regenerateToken() {
        String jwtToken = requestMeta.getJwtToken();
        String usernameFromToken = jwtUtil.getUsernameFromToken(jwtToken.substring(7));
        AppUser user = this.userRepo.findByUserNameAndIsActive(usernameFromToken,Boolean.TRUE);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(usernameFromToken);
        String token = this.jwtUtil.generateToken(userDetails, user);
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);

        AppUserDTO appUserDTO = new AppUserDTO();

        response.setUser( appUserDTO.convertToDTO(user));

//        response.setRole(rolesList.stream().map(roles1 -> roles1.getName()).collect(Collectors.toList()));
        // rolesList.forEach((role)-> response.setRole(role.getRolename().substring(5)));

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(response).setStatus(apiResponse.getStatus());
        return apiResponse;

    }

    public ApiResponse changePassword(ChangePasswordDTO changePasswordDTO) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        AppUser users = userRepo.findByUserNameAndIsActive(changePasswordDTO.getUserName(), Boolean.TRUE);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(Objects.isNull(users)){

            throw  new ResourceNotFoundException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.RESOURCE_NOT_FOUND));
        }
//        String decryptPassword = RSAUtil.decrypt(changePasswordDTO.getExistingPassword(), Base64.getEncoder().encodeToString(rsaKeyPairGenerator.getPrivateKey().getEncoded()));
//        boolean matches = bCryptPasswordEncoder.matches(decryptPassword, users.getPassword());
        boolean matches = bCryptPasswordEncoder.matches(changePasswordDTO.getExistingPassword(), users.getPassword());

        if(!matches){

           throw new UnauthourizedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.UN_AUTHORIZED));


        }

        users.setPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));
        AppUser save = userRepo.save(users);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(save);

        return apiResponse;
    }
}
