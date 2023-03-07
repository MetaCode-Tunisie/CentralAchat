package tn.esprit.centralpurchasing.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface IServiceProductPhoto {
    public void addProductPhoto(String name, String imagePath,Long idProduct) throws IOException;
    public void updateProductPhoto(Long idProductPhoto, String name, String imagePath) throws IOException;
    public List<ProductPhoto> retrieveAllPhoto();
    void deleteProductPhoto(Long idProductPhoto,String name);


}
