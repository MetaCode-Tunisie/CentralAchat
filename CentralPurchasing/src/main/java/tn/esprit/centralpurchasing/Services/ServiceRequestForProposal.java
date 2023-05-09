package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.RequestForProposal;
import tn.esprit.centralpurchasing.Repository.AccountRepository;
import tn.esprit.centralpurchasing.Repository.RequestForProposalRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceRequestForProposal implements IServiceRequestForProposal {
    private RequestForProposalRepository requestForProposalRepository;
    private AccountRepository accountRepository;



    @Override
    public RequestForProposal addRequestForProposal(MultipartFile image, Long idAccount , String description) throws IOException {
        Account account = accountRepository.findById(idAccount).orElse(null);
        RequestForProposal requestForProposal = new RequestForProposal();
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if (filename.contains("..")) {
            System.out.println("!!! Not a valid File");
        }
        requestForProposal.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        requestForProposal.setDescription(description);
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
    public RequestForProposal updateRequestForProposal(MultipartFile image, Long idAccount , String description, Long idrequest) throws IOException {

            Account account = accountRepository.findById(idAccount).orElse(null);
            RequestForProposal requestForProposal = requestForProposalRepository.findById(idrequest).orElse(null);
            String filename = StringUtils.cleanPath(image.getOriginalFilename());
            if (filename.contains("..")) {
                System.out.println("!!! Not a valid File");
            }
            requestForProposal.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
            requestForProposal.setDescription(description);
            requestForProposal.setAccount(account);
            return requestForProposalRepository.save(requestForProposal);

    }

    @Override
    public void removeRequestProposal(Long idRequest) {

        requestForProposalRepository.deleteById(idRequest);
    }

}
