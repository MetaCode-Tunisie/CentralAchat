package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;
import tn.esprit.centralpurchasing.Services.ServiceProductPhoto;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class ProductPhotoController {
    ServiceProductPhoto serviceProductPhoto;

    @PostMapping("/photo")
    public ProductPhoto addPhotoFromUrl(@RequestParam("name") String name, @RequestParam("url") String url) throws IOException {
        return serviceProductPhoto.addPhotoFromUrl(name,url);
    }
}