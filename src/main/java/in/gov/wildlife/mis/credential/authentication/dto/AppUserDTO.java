package in.gov.wildlife.mis.credential.authentication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.gov.wildlife.mis.domian.AppUser;
import in.gov.wildlife.mis.domian.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    private Long id;
    private String name;
    private String userName;
    //or Include.NON_EMPTY, if that fits your use case

    @Getter(onMethod = @__( @JsonIgnore))
    @Setter
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Boolean isActive;
    private Long service;
    private Long division;
    private Long range;
//    private String officeLocation;


    public   AppUserDTO convertToDTO(AppUser appUsers){
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setId(appUsers.getId());
        appUserDTO.setName(appUsers.getName());
        appUserDTO.setUserName(appUsers.getUserName());
        appUserDTO.setRoles(appUsers.getRoles());
        appUserDTO.setIsActive(appUsers.getIsActive());
        appUserDTO.setService(appUsers.getService().getId());
        appUserDTO.setDivision(appUsers.getDivision().getId());
        appUserDTO.setRange(appUsers.getRange().getId());
        return appUserDTO;
    }

    public static AppUser convertToEntity(AppUserDTO appUserDTO){
        AppUser appUsers = new AppUser();

        appUsers.setUserName(appUserDTO.getUserName());
        appUsers.setName(appUserDTO.getName());
        appUserDTO.setService(appUsers.getService().getId());
        appUserDTO.setDivision(appUsers.getDivision().getId());
        appUserDTO.setRange(appUsers.getRange().getId());
        appUsers.setIsActive(Boolean.TRUE);
        appUsers.setRoles(appUserDTO.getRoles());
        appUsers.setAccountNonLocked(Boolean.TRUE);
        appUsers.setLockTime(null);
        appUsers.setFailedAttempt(0);
        appUsers.setPassword("$2a$10$QqMLhiXXH/SpaPbbow8gZ.LsLS9gTSb2sb6wSQPm6ZVvaQEkvAITa");

        return appUsers;
    }

//    public AppUser updateUser(AppUser users) {
//
//        if(Objects.nonNull(name)){
//            users.setName(name);
//        }
//        if(Objects.nonNull(districtId)&& Objects.nonNull(officeLocation)){
//            users.setDistrictId(districtId);
//            users.setOfficeLocation(officeLocation);
//        }
//        return users;
//
//    }
}
