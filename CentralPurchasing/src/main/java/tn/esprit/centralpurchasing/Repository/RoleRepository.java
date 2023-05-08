package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Role;

import tn.esprit.centralpurchasing.Entities.TypeRole;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByTypeRole(TypeRole typeRole);

}
