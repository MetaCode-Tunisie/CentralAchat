package tn.esprit.centralpurchasing.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;

import java.io.IOException;

public interface IServiceProductPhoto {

    public ProductPhoto addPhotoFromUrl(String name, String url) throws IOException;

}
