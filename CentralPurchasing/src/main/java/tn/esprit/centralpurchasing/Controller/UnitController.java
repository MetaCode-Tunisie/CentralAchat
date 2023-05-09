package tn.esprit.centralpurchasing.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Unit;
import tn.esprit.centralpurchasing.Services.IServiceUnit;

import java.util.List;

@RestController

public class UnitController {
    @Autowired
    IServiceUnit ServiceUnit;


    @PostMapping("/ajouterUnit")
    public void ajouterUnit(@RequestBody Unit unit){
        ServiceUnit.ajouterUnit(unit);
    }

    @GetMapping("/AfficherUnit")
    public List<Unit> getAllUnit() {
        return ServiceUnit.getAllUnit();
    }


    @PostMapping("/affecterUnitToProduit/{idProduct}/{idUnit}")
    public void affecterUnitToProduit(@PathVariable("idUnit") Long idUnit, @PathVariable("idProduct") Long idProduct){
        ServiceUnit.affecterUnitToProduit(idUnit, idProduct);

    }

    @DeleteMapping("/supprimerUnit/{idUnit}")
    public void supprimerUnit(@PathVariable Long idUnit) {

        ServiceUnit.supprimerUnit(idUnit);
    }

}
