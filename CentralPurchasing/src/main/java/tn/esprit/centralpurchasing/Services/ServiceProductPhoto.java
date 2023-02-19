package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;
import tn.esprit.centralpurchasing.Repository.ProductPhotoRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
@AllArgsConstructor
public class ServiceProductPhoto implements IServiceProductPhoto{
    ProductPhotoRepository productPhotoRepository;


    @Override
    public ProductPhoto addPhotoFromUrl(String name, String url) throws IOException  {

        URL imageUrl = new URL(url);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = imageUrl.openStream()) {
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, n);
            }
        }
        byte[] content = outputStream.toByteArray();
        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setName(name);
        productPhoto.setUrl(url);
        productPhoto.setContent(content);
        return productPhotoRepository.save(productPhoto);
    }
}
