package tn.esprit.centralpurchasing.Entities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

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








    





