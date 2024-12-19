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
    @Column(name = "company_name")
    private String companyName;

    @ElementCollection
    @CollectionTable(name = "producer_portfolio", joinColumns = @JoinColumn(name = "producer_id"))
    @MapKeyColumn(name = "platform_name")
    @Column(name = "platform_link")
    private Map<String, String> portfolio;


    public Long getId(){
        return id;
    }
    public void setId(Long id) { this.id = id;}

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) {this.companyName = companyName; }

    public Map<String, String> getPortfolio() {return portfolio; }
    public void setPortfolio(Map<String, String> portfolio) {
        this.portfolio = portfolio;
    }
}
