package tn.esprit.centralpurchasing.Services;

import com.sun.deploy.nativesandbox.NativeSandboxBroker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;
import tn.esprit.centralpurchasing.Repository.ProductPhotoRepository;
import tn.esprit.centralpurchasing.Repository.ProductRepository;


import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@Service
@AllArgsConstructor
public class ServiceProductPhoto implements IServiceProductPhoto{

    ProductPhotoRepository productPhotoRepository;
    ProductRepository productRepository;


    @Override
    public void addProductPhoto(String name, String imagePath,Long idProduct) throws IOException {
        Product product = productRepository.findById(idProduct).orElse(null);
        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setName(name);
        productPhoto.setImage(readBytes(imagePath));
        productPhoto.setProduct(product);
        productPhotoRepository.save(productPhoto);
    }




    private byte[] readBytes(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }


    @Override
    public void updateProductPhoto(Long idProductPhoto, String name, String imagePath) throws IOException {
        ProductPhoto productPhoto = productPhotoRepository.findById(idProductPhoto).orElse(null);
        if (productPhoto == null) {
            throw new RuntimeException("Product photo with id " + idProductPhoto + " not found");
        }

        productPhoto.setName(name);
        productPhoto.setImage(readBytes(imagePath));

        productPhotoRepository.save(productPhoto);

    }

    @Override
    public List<ProductPhoto> retrieveAllPhoto() {
        return productPhotoRepository.findAll();
    }

    @Override
    public void deleteProductPhoto(Long idProductPhoto,String name) {
        productPhotoRepository.deleteById(idProductPhoto);
    }






}




