package in.gov.wildlife.mis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Range {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rangeName;
}
