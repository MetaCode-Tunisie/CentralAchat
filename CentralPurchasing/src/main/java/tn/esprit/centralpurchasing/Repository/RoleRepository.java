package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
