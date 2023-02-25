package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.centralpurchasing.Entities.Location;
import tn.esprit.centralpurchasing.Entities.ProductPhoto;

public interface ProductPhotoRepository extends JpaRepository<ProductPhoto, Long> {


}
