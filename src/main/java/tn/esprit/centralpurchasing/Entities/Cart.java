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

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(AccessLevel.NONE)
    Long idCart;
    int quantity;
    @Temporal(TemporalType.DATE)
    Date dateCreation;
    Boolean valid;
 @JsonIgnore
    @ManyToOne
    Account account;
    @JsonIgnore

    @ManyToOne
    Product product;
    @JsonIgnore
    @ManyToOne
    Orders order;
}
