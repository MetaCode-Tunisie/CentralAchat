package tn.esprit.centralpurchasing.Service;

import tn.esprit.centralpurchasing.Entities.Unit;

import java.util.List;

public interface IServiceUnit {
    public void ajouterUnit(Unit unit);
    List<Unit> getAllUnit();

    void  affecterUnitToProduit(Long idUnit,Long idProduct);

}
