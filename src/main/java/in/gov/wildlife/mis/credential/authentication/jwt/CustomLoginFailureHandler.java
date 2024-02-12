package in.gov.wildlife.mis.credential.authentication.jwt;

import in.gov.wildlife.mis.credential.authentication.AppUserRepository;
import in.gov.wildlife.mis.credential.authentication.AppUserServiceImpl;
import in.gov.wildlife.mis.credential.authentication.UserService;
import in.gov.wildlife.mis.model.AppUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    UserService userService;

    @Autowired
    AppUserServiceImpl appUserService;

    @Autowired
    private AppUserRepository userRepo;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

//        String email = "John";
       String email = request.getHeader("userName");

        UserDetails user =  appUserService.loadUserByUsername(email);
        AppUser appUsers = userRepo.findByUserNameAndIsActive(user.getUsername(), Boolean.TRUE);

        if (user.isEnabled() && user.isAccountNonLocked()) {
            if (appUsers.getFailedAttempt() <= UserService.MAX_FAILED_ATTEMPTS - 1) {
                userService.increaseFailedAttempts(appUsers);
            } else {
                userService.lock(appUsers);
                exception = new LockedException("Your account has been locked due to 3 failed attempts."
                        + " It will be unlocked after 24 hours.");
                throw new AccessDeniedException(exception.getMessage());
            }
        } else if (!user.isAccountNonLocked()) {
            if (userService.unlockWhenTimeExpired(appUsers)) {
                exception = new LockedException("Your account has been unlocked. Please try to login again.");
                throw new AccessDeniedException(exception.getMessage());
            }
        }

    }

}
