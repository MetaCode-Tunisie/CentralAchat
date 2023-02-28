package tn.esprit.centralpurchasing.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data @Getter @Setter
public class UserDto {

    private String email;
    private String password;

    String firstname;
    String lastname;
    String phoneNumber;

    // reset password
    String codeTel;
    String resetToken;


    // setings of account
    Boolean isAccountNonLocked=true;
    Boolean isAccountNonExpired=true;
    Boolean isCredentialsNonExpired=true;
    Boolean isEnabled=false;
    String activateCode;
}
