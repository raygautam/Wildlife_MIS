package in.gov.wildlife.mis.model;

import jakarta.persistence.*;

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String divisionName;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Service service;
}
