package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.RequestForProposal;

public interface RequestForProposalRepository extends JpaRepository<RequestForProposal, Long> {
}
