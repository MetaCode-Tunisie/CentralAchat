package tn.esprit.centralpurchasing.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(AccessLevel.NONE)
    Long idDelivery;
    @Temporal(TemporalType.DATE)
    Date dateDelivery = new Date() ;
    String departureAddress;
    String destinationAddress;
    Double price;


    @JsonIgnore
    @OneToOne(mappedBy = "delivery")
    Reciept reciept;
    @JsonIgnore
    @OneToMany(mappedBy = "delivery")
    Set<Orders> orders = new HashSet<>();

}
