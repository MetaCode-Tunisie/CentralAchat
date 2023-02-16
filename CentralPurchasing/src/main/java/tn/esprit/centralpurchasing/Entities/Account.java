package tn.esprit.centralpurchasing.Entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(AccessLevel.NONE)
    Long idAccount;
    String firstname;
    String lastname;
    String photo;



    @NotEmpty
    String email;
    @NotEmpty
    String password;
    Boolean isAccountNonLocked=true;
    Boolean isAccountNonExpired=true;
    Boolean isCredentialsNonExpired=true;
    Boolean isEnabled=true;

    @OneToMany
    Set<Account> admin_supplier = new HashSet<>();

    @OneToMany
    Set<Account> admin_operator;

    @OneToMany
    Set<Account> admin_buyer;

    @ManyToMany
    Set<Account> supplier_buyer;

    @OneToMany
    Set<Account> operator_supplier;

    @ManyToMany(mappedBy = "accounts")
    Set <Role> roles= new HashSet<>();

    @OneToMany
    Set<Orders> orders=new HashSet<>();

    @OneToMany(mappedBy = "account")
    Set<Cart> carts=new HashSet<>();

    @ManyToOne
    Currency currency;

    @OneToMany(mappedBy = "account")
    Set<Product> products=new HashSet<>();

    @OneToMany(mappedBy = "account")
    Set<RequestForProposal> requestForProposals =new HashSet<>();

}
