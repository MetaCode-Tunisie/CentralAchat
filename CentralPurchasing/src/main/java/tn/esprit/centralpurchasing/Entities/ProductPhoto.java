package tn.esprit.centralpurchasing.Entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ProductPhoto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(AccessLevel.NONE)
    Long idProductPhoto;
    String name;
    @Lob
    @Column(name = "image")
    private byte[] image;

    @ManyToOne
    Product product;
    // constructeurs, getters et setters






}