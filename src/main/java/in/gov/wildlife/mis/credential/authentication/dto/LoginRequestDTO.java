package in.gov.wildlife.mis.credential.authentication.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String userName;
    private String password;
//    private String captcha;
}
