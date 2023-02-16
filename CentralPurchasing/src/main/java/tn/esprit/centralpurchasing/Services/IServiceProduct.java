package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Product;

import java.util.List;

public interface IServiceProduct {
    public Product ajouterProduitAvecCategorie(Product produit, Long idCategory);
    public List<Product> retrieveAllProducts();

}
