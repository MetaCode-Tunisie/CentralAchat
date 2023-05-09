package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Unit;

import java.util.List;

public interface IServiceUnit {
     void ajouterUnit(Unit unit);
    List<Unit> getAllUnit();

    void  affecterUnitToProduit(Long idUnit,Long idProduct);
    void supprimerUnit( Long idUnit);

}
