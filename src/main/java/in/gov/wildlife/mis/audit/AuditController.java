package in.gov.wildlife.mis.audit;

import in.gov.wildlife.mis.common.ApiResponse;
import in.gov.wildlife.mis.common.Constants;
import in.gov.wildlife.mis.common.MessageByLocale;
import in.gov.wildlife.mis.credential.config.RequestMeta;
import in.gov.wildlife.mis.exception.AccessDeniedException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@CrossOrigin(origins = "10.179.2.92:8080")
public class AuditController {

    private final RequestMeta requestMeta;

    private final MessageByLocale messageByLocale;

    private final AuditService auditService;


    Bucket bucket;

    public AuditController(RequestMeta requestMeta, MessageByLocale messageByLocale, AuditService auditService) {
        this.requestMeta = requestMeta;
        this.messageByLocale = messageByLocale;
        this.auditService = auditService;

        Bandwidth limit = Bandwidth.classic(120, Refill.greedy(120, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/auditTrial")
    public ResponseEntity<ApiResponse> getAuditTrail() {

        if (bucket.tryConsume(1)) {
            if (requestMeta.isSuperAdmin()) {
                ApiResponse apiResponse = auditService.getAudit();
                return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
            }
            else{
                throw new AccessDeniedException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.ACCESS_DENIED));
            }
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
    }
}
