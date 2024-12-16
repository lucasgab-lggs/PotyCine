package br.ufrn.imd.PotyCine.domain;

import java.util.Map;

public class Producer {

    private Long id;
    private Long userId;
    private String companyName;
    private Map<String, String> portfolio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Map<String, String> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Map<String, String> portfolio) {
        this.portfolio = portfolio;
    }
}
