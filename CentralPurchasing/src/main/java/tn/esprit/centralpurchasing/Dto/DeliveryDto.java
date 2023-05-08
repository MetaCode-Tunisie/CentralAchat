package tn.esprit.centralpurchasing.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
 @Setter @Getter
public class DeliveryDto {

    Long idDelivery;
    @Temporal(TemporalType.DATE)
    Date dateDelivery = new Date() ;
    String departureAddress;
    String destinationAddress;
    int status;

}
