package tn.esprit.centralpurchasing.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @JsonIgnore @ManyToOne
    Account anyaccount_to_admin;

/*
    @JsonIgnore @ManyToOne
    Account operators_to_admin;

    @JsonIgnore @ManyToOne
    Account suppliers_to_admin ;
 */



    @JsonIgnore @ManyToOne
    Account suppliers_to_operator;

    @JsonIgnore @ManyToMany
    Set<Account> suppliers_buyers;

    @JsonIgnore @ManyToMany
    Set <Role> roles= new HashSet<>();

    @JsonIgnore @OneToMany
    Set<Orders> orders=new HashSet<>();

    @JsonIgnore

    @OneToMany(mappedBy = "account")
    Set<Cart> carts=new HashSet<>();

    @JsonIgnore

    @ManyToOne
    Currency currency;

    @JsonIgnore

    @OneToMany(mappedBy = "account")
    Set<Product> products=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    Set<RequestForProposal> requestForProposals =new HashSet<>();

}
