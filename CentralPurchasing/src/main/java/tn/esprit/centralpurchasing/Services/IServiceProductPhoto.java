package tn.esprit.centralpurchasing.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;

import java.io.IOException;
import java.util.List;

public interface IServiceProductPhoto {
  public ProductPhoto addProductPhoto(MultipartFile image, Long idProduct) throws IOException;
 //**************************************
 public ProductPhoto UpdateProductPhoto(MultipartFile image, Long idProduct ,Long idProductPhoto) throws IOException;
    public List<ProductPhoto> retrieveAllPhoto();
    void deleteProductPhoto(Long idProductPhoto,String name);
   // public void addProductPhoto(String name, String imagePath, Long productId) throws IOException;
   //public void saveProductPhoto(ProductPhoto productPhoto) throws IOException;
}
