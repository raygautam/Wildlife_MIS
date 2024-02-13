package in.gov.wildlife.mis.credential.authentication;

import in.gov.wildlife.mis.domian.AppUser;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements UserDetailsService {

    @Autowired
    private final AppUserRepository appUserRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUsers = appUserRepository.findByUserNameAndIsActive(username,Boolean.TRUE);

        if (appUsers==null){
            throw  new UsernameNotFoundException("User not found with username: " + username);
        }

//        else{
//
////            log.info("User exists");
//
//        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        //taking the roles form the users and then adding them one by one  the roles in the authoritiees
        appUsers.getRoles().forEach(roles -> {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        });

        return new User(appUsers.getUserName(),appUsers.getPassword(),authorities);
    }
}
