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
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(AccessLevel.NONE)
    Long idOrder;
    @Temporal(TemporalType.DATE)
    Date orderDate = new Date();
    Boolean deliveryOption=false;
    Boolean payment=false;
    int quantity;
    Boolean valid=false;
    String ref=null;
    @JsonIgnore
    @OneToOne
    Product product ;
    @JsonIgnore
    @ManyToOne
    Delivery delivery;
    @ManyToOne(cascade = CascadeType.ALL)
    Account accounts;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Invoice invoice;

}