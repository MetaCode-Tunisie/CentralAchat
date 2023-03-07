package tn.esprit.centralpurchasing.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    Double price;
    @Enumerated(EnumType.STRING)
    status status ;
    int quantity;
    String unitValue;
    String adress;
    @JsonIgnore
    @ManyToOne
    Account account;
    @JsonIgnore
    @ManyToOne
    Location location;
    @JsonIgnore
    @ManyToOne
    Unit unit;
    @JsonIgnore
    @ManyToOne
    Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    Set<ProductPhoto> productPhotos=new HashSet<>();


}
