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
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(AccessLevel.NONE)
    Long idAccount;
    @NotEmpty
    String firstname;
    @NotEmpty
    String lastname;
    @Lob @JsonIgnore
    String photo;
    @NotEmpty
    String phoneNumber;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    Gender gender;
    @NotEmpty
    @Temporal(TemporalType.DATE)
    Date birthdate;

    // reset password
    @JsonIgnore
    String codeTel;
    @JsonIgnore
    String resetToken;

    // username and password
    @NotEmpty
    String email;
    @NotEmpty
    String password;


    // setings of account
    @JsonIgnore
    Boolean isAccountNonLocked=true;
    @JsonIgnore
    Boolean isAccountNonExpired=true;
    @JsonIgnore
    Boolean isCredentialsNonExpired=true;
    @JsonIgnore
    Boolean isEnabled=false;
    @JsonIgnore
    String activateCode;
    @JsonIgnore @Temporal(TemporalType.DATE)
    Date created=new Date();



    @JsonIgnore @ManyToOne
    Account suppliers_to_operator;

    @JsonIgnore @ManyToMany
    Set<Account> suppliers_buyers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.ALL )
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();

    @OneToMany @JsonIgnore
    Set<Orders> orders=new HashSet<>();



    @ManyToOne @JsonIgnore
    Currency currency;


    @OneToMany(mappedBy = "account") @JsonIgnore
    Set<Product> products=new HashSet<>();



   


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<researches> researches;


    @OneToMany(mappedBy = "account") @JsonIgnore
    Set<RequestForProposal> requestForProposals =new HashSet<>();


}
