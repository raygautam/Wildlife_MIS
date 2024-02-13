package in.gov.wildlife.mis.audit;

import in.gov.wildlife.mis.domian.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AudiTrailRepository extends JpaRepository<AuditTrail,Long> {
    @Query(value="Select MAX(sl_no) from audit_trail ",nativeQuery = true)
    Long getSlno();
}
