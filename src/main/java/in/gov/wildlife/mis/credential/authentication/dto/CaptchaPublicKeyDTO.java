package in.gov.wildlife.mis.credential.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaPublicKeyDTO {
    private String captchaString;
    private String publicKey;
}
