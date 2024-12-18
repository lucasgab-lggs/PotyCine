package br.ufrn.imd.PotyCine.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Entity
@Data
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;


    private String companyName;

    @ElementCollection
    @CollectionTable(name = "producer_portfolio", joinColumns = @JoinColumn(name = "producer_id"))
    @MapKeyColumn(name = "platform_name")
    @Column(name = "platform_link")
    private Map<String, String> portfolio;
}
