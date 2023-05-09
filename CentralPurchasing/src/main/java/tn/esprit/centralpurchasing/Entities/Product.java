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
   public String description;
    Double price;
    @Enumerated(EnumType.STRING)
    status status ;
    int quantity;
    String unitValue;
    Double x;
    Double y;
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
   // @JsonIgnore
    @ManyToOne
    Category category;

    @OneToMany(mappedBy = "product")
    Set<ProductPhoto> productPhotos=new HashSet<>();

 public String generateQRCodeContent() {
  StringBuilder sb = new StringBuilder();
  sb.append("Name: ").append(this.name).append("\n");
  sb.append("Description: ").append(this.description).append("\n");
  sb.append("Price: ").append(this.price).append("\n");
  sb.append("Status: ").append(this.status).append("\n");
  sb.append("Quantity: ").append(this.quantity).append("\n");
  sb.append("Unit Value: ").append(this.unitValue).append("\n");
  sb.append("Address: ").append(this.adress).append("\n");
  return sb.toString();
 }

}
