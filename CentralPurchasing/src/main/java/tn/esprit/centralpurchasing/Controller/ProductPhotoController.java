package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;
import tn.esprit.centralpurchasing.Repository.ProductPhotoRepository;
import tn.esprit.centralpurchasing.Services.ServiceProduct;
import tn.esprit.centralpurchasing.Services.ServiceProductPhoto;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor

public class ProductPhotoController {
    ServiceProductPhoto serviceProductPhoto;
    ProductPhotoRepository productPhotoRepository;
ServiceProduct serviceProduct;

    @PostMapping(path="/add/{idProduct}",consumes = {MULTIPART_FORM_DATA_VALUE})
    public ProductPhoto addProductPhoto(@RequestParam MultipartFile file, @PathVariable Long idProduct) throws IOException {

return serviceProductPhoto.addProductPhoto(file, idProduct);

 }

    @PostMapping(path="/update/{idProduct}/{idProductPhoto}",consumes = {MULTIPART_FORM_DATA_VALUE})
    public ProductPhoto uppdateProductPhoto(@RequestParam MultipartFile file, @PathVariable Long idProduct, @PathVariable Long idProductPhoto) throws IOException {

        return serviceProductPhoto.UpdateProductPhoto(file,idProduct,idProductPhoto);

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



