package in.gov.wildlife.mis.domian;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor

public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long slNo;
    private String remoteAddress;
    private String userName;
    private String url;
    private String simpleDate;
    private String method;
    private Date entryDate;

    @PrePersist
    public void onCreate() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();

       this.setEntryDate(date);
       this.setSimpleDate(date.toString());
    }
}
