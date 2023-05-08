package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;
import tn.esprit.centralpurchasing.Repository.ProductPhotoRepository;
import tn.esprit.centralpurchasing.Repository.ProductRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiceProductPhoto implements IServiceProductPhoto{

    ProductPhotoRepository productPhotoRepository;
    ProductRepository productRepository;
ServiceProduct serviceProduct;
    @Override
    public ProductPhoto addProductPhoto(MultipartFile image, Long idProduct) throws IOException {
        Product product = productRepository.retriveProduct(idProduct);
        ProductPhoto productPhoto = new ProductPhoto();
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        productPhoto.setName(Base64.getEncoder().encodeToString(image.getBytes()));
        productPhoto.setProduct(product);
        return productPhotoRepository.save(productPhoto);
    }


    @Override
    public ProductPhoto UpdateProductPhoto(MultipartFile image, Long idProduct ,Long idProductPhoto) throws IOException {
        Product product = productRepository.findById(idProduct).orElse(null);
        ProductPhoto productPhoto=productPhotoRepository.findById(idProductPhoto).orElse(null);
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        productPhoto.setName(Base64.getEncoder().encodeToString(image.getBytes()));
        productPhoto.setProduct(product);
        return productPhotoRepository.save(productPhoto);
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







