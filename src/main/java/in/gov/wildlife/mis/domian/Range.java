package in.gov.wildlife.mis.domian;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Range {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rangeName;
}
