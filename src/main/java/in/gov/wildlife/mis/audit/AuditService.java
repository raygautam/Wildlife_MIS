package in.gov.wildlife.mis.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    @Autowired
    AudiTrailRepository audiTrailRepository;
    public ApiResponse getAudit() {

        List<AuditTrail> trails = audiTrailRepository.findAll();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(trails);
        return apiResponse;
    }
}
