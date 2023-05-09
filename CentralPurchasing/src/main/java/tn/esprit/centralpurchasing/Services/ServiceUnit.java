package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Entities.Unit;
import tn.esprit.centralpurchasing.Repository.ProductRepository;
import tn.esprit.centralpurchasing.Repository.UnitRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class ServiceUnit implements IServiceUnit {
    private UnitRepository unitRepository;
    private ProductRepository productReposiotry;

    @Override
    public void ajouterUnit(Unit unit) {
        unitRepository.save(unit);
    }

    @Override
    public List<Unit> getAllUnit() {
        return unitRepository.findAll();
    }

    @Override
    public   void affecterUnitToProduit(Long idUnit,Long idProduct)
    {
        Product product = productReposiotry.findById(idProduct).orElse(null);
        Unit unit = unitRepository.findById(idUnit).orElse(null);
        product.setUnit(unit);
        productReposiotry.save(product);
    }
    @Override
    public void supprimerUnit(Long idUnit) {
        unitRepository.deleteById(idUnit);
    }




}
