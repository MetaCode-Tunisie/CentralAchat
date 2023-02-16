package tn.esprit.centralpurchasing.Entities;

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
    Date orderDate;
    float totalPrice;
    Boolean deliveryOption;
    Boolean payment;

    @OneToMany(mappedBy = "order")
    Set<Cart> carts =new HashSet<>();

    @OneToOne(mappedBy = "orders")
    Delivery delivery;
}
