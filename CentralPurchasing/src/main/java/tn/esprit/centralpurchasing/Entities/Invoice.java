package tn.esprit.centralpurchasing.Entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(AccessLevel.NONE)
    Long idFacture;
    @Temporal(TemporalType.DATE)
    Date FactureDate = new Date();
    Double Totalprice;
    @OneToOne(mappedBy = "invoice")
    Orders order;
}

