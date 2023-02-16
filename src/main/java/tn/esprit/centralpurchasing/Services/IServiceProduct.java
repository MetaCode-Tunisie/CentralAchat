package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Product;

public interface IServiceProduct {
    public Product ajouterProduitAvecCategorie(Product produit, Long idCategory);
}
