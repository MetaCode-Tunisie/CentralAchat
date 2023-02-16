package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.RequestForProposal;
import tn.esprit.centralpurchasing.Repository.AccountRepository;
import tn.esprit.centralpurchasing.Repository.RequestForProposalRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceRequestForProposal implements IServiceRequestForProposal {
    private RequestForProposalRepository requestForProposalRepository;
    private AccountRepository accountRepository;
    @Override
    public RequestForProposal addRequestForProposal(RequestForProposal requestForProposal, Long idAccount) {
        Account account = accountRepository.findById(idAccount).orElse(null);
        requestForProposal.setAccount(account);

        return requestForProposalRepository.save(requestForProposal);
    }

    @Override
    public List<RequestForProposal> findAllRequests() {
        return requestForProposalRepository.findAll();
    }

    @Override
    public RequestForProposal searchRequestForProposal(Long idRequest) {
        return requestForProposalRepository.findById(idRequest).orElse(null);
    }

    @Override
    public RequestForProposal updateRequest(RequestForProposal requestForProposal) {

        return requestForProposalRepository.findById(requestForProposal.getIdRequestForProposal())
                .map(requestForProposal1 -> {
                    requestForProposal1.setDescription(requestForProposal.getDescription());
                    requestForProposal1.setPhoto(requestForProposal.getPhoto());
                    return requestForProposalRepository.save(requestForProposal1);
                })
                .orElse(null);
    }

    @Override
    public void removeRequestProposal(Long idRequest) {

        requestForProposalRepository.deleteById(idRequest);
    }



   /* @Override
    public void affectUserToProposalRequest(Long idUser, Long idRequest) {
        RequestForProposal requestForProposal = requestForProposalRepository.findById(idRequest).orElse(null);
        Account account = accountRepository.findById(idUser).orElse(null);
        requestForProposal.setAccount(account);
        requestForProposalRepository.save(requestForProposal);
    }*/
}
