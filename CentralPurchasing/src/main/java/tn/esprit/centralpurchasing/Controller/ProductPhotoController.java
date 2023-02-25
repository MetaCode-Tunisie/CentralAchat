package tn.esprit.centralpurchasing.Controller;

import com.sun.deploy.nativesandbox.NativeSandboxBroker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;
import tn.esprit.centralpurchasing.Repository.ProductPhotoRepository;
import tn.esprit.centralpurchasing.Services.ServiceProductPhoto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/product-photo")
public class ProductPhotoController {
    ServiceProductPhoto serviceProductPhoto;
    ProductPhotoRepository productPhotoRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addProductPhoto(@RequestParam String name, @RequestParam String imagePath, @RequestParam Long idProduct) {
        try {
            serviceProductPhoto.addProductPhoto(name, imagePath,idProduct);
            return ResponseEntity.ok("Product photo added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product photo");
        }


    }

    @PostMapping("/update/{idProductPhoto}")
    public ResponseEntity<?> updateProductPhoto(@PathVariable Long idProductPhoto,
                                                @RequestParam String name,
                                                @RequestParam String imagePath) {
        try {
            serviceProductPhoto.updateProductPhoto(idProductPhoto, name, imagePath);
            return ResponseEntity.ok("Product photo updated successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product photo");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/all")
public List<ProductPhoto> retrieveAllPhoto(){
        return serviceProductPhoto.retrieveAllPhoto();
}
@DeleteMapping("/delete/{idProductPhoto}/{name}")
void deleteProductPhoto(Long idProductPhoto,String name){
    serviceProductPhoto.deleteProductPhoto(idProductPhoto, name);
}
    }



