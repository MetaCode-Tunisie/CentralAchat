package tn.esprit.centralpurchasing.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class IPlocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "continent")
    private String continent;
    @Column(name = "CurrencyCode")
    private String CurrencyCode;
    @Column(name = "visit_count")
    private Long visitCount;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long  getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String CurrencyCode) {
        this.city = CurrencyCode;
    }




    @Override
    public String toString() {
        return "UserLocation [id=" + id + ", ipAddress=" + ipAddress + ", city=" + city + ", country=" + country
                + ", continent=" + continent +  "]";
    }


}