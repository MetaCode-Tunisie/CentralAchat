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
import java.util.*;

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
    String phoneNumber;

    // reset password
    String codeTel;
    String resetToken;

    // username and password
    @NotEmpty
    String email;
    @NotEmpty
    String password;


    // setings of account
    Boolean isAccountNonLocked=true;
    Boolean isAccountNonExpired=true;
    Boolean isCredentialsNonExpired=true;
    Boolean isEnabled=false;
    String activateCode;




    @JsonIgnore @ManyToOne
    Account suppliers_to_operator;

    @JsonIgnore @ManyToMany
    Set<Account> suppliers_buyers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.ALL )
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();

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
