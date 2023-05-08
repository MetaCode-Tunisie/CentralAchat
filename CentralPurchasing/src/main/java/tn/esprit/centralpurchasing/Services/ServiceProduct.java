package tn.esprit.centralpurchasing.Services;

import com.google.zxing.*;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.*;
import tn.esprit.centralpurchasing.Repository.*;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class ServiceProduct implements IServiceProduct {
ProductRepository productRepository;
CategoryRepository categoryRepository;
LocationRepository locationRepository;
ProductPhotoRepository productPhotoRepository;
UnitRepository unitRepository;
AccountRepository accountRepository;
researchRepository researchRepository;


    @Override
    public List<Product> searchProducts(String name, String adress, Double minPrice, Double maxPrice) {
        return productRepository.findBynameContainingAndAdressContainingAndPriceBetween(
                name, adress, minPrice, maxPrice);
    }

    @Override
    public Product addProductWithCategoryAndLocation(Long idAccount,Long idCategory, Long idLocation, Product product,Long idUnit) {

        Account account = accountRepository.getReferenceById(idAccount);

        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + idCategory + " not found"));
        Location location = locationRepository.findById(idLocation)
                .orElseThrow(() -> new EntityNotFoundException("Location with id " + idLocation + " not found"));
        Unit unit = unitRepository.findById(idUnit)
                .orElseThrow(() -> new EntityNotFoundException("Unit with id " + idUnit + " not found"));
        //System.out.println("***************    HELLOO   *****************");
        if (product.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        product.setCategory(category);
        //System.out.println("***************    Set Category Done   *****************");
        product.setLocation(location);
        //System.out.println("***************    Set Location Done   *****************");

       // System.out.println("Unite :"+unit);
        product.setUnit(unit);
        product.setAccount(account);
        return productRepository.save(product);


    }


    @Override
    public List<Product> retrieveAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public void updateProduct(Long idProduct, String Name) {
Product product =productRepository.findById(idProduct).get();
product.setName(Name);
productRepository.save(product);
    }

    @Override
    public Map<String, Long> getProductCountsByStatus() {
        List<Object[]> counts = productRepository.countProductsByStatus();
        Map<String, Long> result = new HashMap<>();
        for (Object[] row : counts) {
            String status = row[0].toString();
            Long count = (Long) row[1];
            result.put(status, count);
        }
        return result;
    }

    @Override
    public List<Product> getAllProductsSortedByName() {
        return productRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<Product> getAllProductsSortedByPrice() {
        return productRepository.findAll(Sort.by("price"));
    }

    @Override
    public Product getProduct(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);

    }

    @Override
    public byte[] generateQRCode(Long productId) throws WriterException, IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        String content = product.generateQRCodeContent();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, 350, 350,
                Collections.singletonMap(EncodeHintType.MARGIN, 0));

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
        return out.toByteArray();
    }

    @Override
    public List<Product> getSimilarProducts(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productRepository.findSimilarProducts(product.getCategory(), productId);
    }
    @Override
    public researches addResearch(Long id_user, researches r) {
        Account user = accountRepository.findById(id_user).orElse(null);
        List<researches> researchess = researchRepository.retrieveresearchesByUser(id_user);
        for (researches re: researchess ){
            if(re.getSearchPhrase().toLowerCase().equals(r.getSearchPhrase().toLowerCase())){
                return null;
            }
        }
        r.setUser(user);
        return researchRepository.save(r);
    }
    @Override
    public List<Product> SuggestedProducts(Long id_user) {
        List<researches> userResearches = researchRepository.retrieveresearchesByUser(id_user);
        List<Product> products =productRepository.findAll();
        List<Product> suggestedproducts = new ArrayList<>();

        for (Product p: products ){
            for (researches r: userResearches ){
                String s1[]=r.getSearchPhrase().split("[ ]+");
                for (String s: s1 ){
                    if((p.getName().toLowerCase().contains(s.toLowerCase()))||(p.getCategory().getName().toLowerCase().contains(s.toLowerCase()))){
                        if(!suggestedproducts.contains(p)){
                            suggestedproducts.add(p);
                        }
                    }
                }
            }
        }
        return suggestedproducts;
    }



}

