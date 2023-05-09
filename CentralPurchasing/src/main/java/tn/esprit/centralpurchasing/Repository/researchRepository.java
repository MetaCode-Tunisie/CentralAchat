package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.centralpurchasing.Entities.researches;

import java.util.List;

public interface researchRepository extends JpaRepository<researches,Integer> {


    @Query("SELECT r FROM researches r, Account a where r.user.idAccount=a.idAccount and a.idAccount=:id_user")
    List<researches> retrieveresearchesByUser(@Param("id_user") Long id_user);

}
