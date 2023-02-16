package tn.esprit.centralpurchasing.Entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(AccessLevel.NONE)
    Long idProduct;
    String name;
    String description;
    int status;
    int quantity;
    String unitValue;

    @ManyToOne
    Account account;

    @ManyToOne
    Location location;

    @ManyToOne
    Unit unit;

    @ManyToOne
    Category category;

    @OneToMany(mappedBy = "product")
    Set<ProductPhoto> productPhotos=new HashSet<>();

}
