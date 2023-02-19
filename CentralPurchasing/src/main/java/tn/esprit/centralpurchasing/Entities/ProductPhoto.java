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
    String url;
    String name;
    @Lob
    @Column(name = "content")
    private byte[] content;
    @ManyToOne
    Product product;
}
