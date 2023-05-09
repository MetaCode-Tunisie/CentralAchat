package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.IPlocation;

public interface IPLocationRepository extends JpaRepository<IPlocation,Long> {

    IPlocation findByIpAddress(String ip);
}
