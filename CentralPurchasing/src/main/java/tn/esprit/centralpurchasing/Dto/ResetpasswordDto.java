package tn.esprit.centralpurchasing.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResetpasswordDto {

    String code;
    String newPassword;
    String identifiant;
}
