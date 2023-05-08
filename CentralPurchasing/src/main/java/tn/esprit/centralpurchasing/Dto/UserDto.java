package tn.esprit.centralpurchasing.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.centralpurchasing.Entities.Gender;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data @Getter @Setter
public class UserDto {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;


    @JsonIgnore
    String firstname;
    @JsonIgnore
    String lastname;
    @Lob @JsonIgnore
    String photo;
    @JsonIgnore
    String phoneNumber;
    @JsonIgnore
    Gender gender;
    @JsonIgnore
    Date birthdate;
    // reset password
    @JsonIgnore
    String codeTel;
    @JsonIgnore
    String resetToken;


    // setings of account
    @JsonIgnore
    Boolean isAccountNonLocked;
    @JsonIgnore
    Boolean isAccountNonExpired;
    @JsonIgnore
    Boolean isCredentialsNonExpired;
    @JsonIgnore
    Boolean isEnabled;
    @JsonIgnore
    String activateCode;
    @JsonIgnore
    Date crated;
}
