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
    public void addRequestForProposal(String description, String imagePath, Long idAccount) throws IOException {

        Account account = accountRepository.findById(idAccount).orElse(null);
        RequestForProposal requestForProposal= new RequestForProposal();
        requestForProposal.setDescription(description);
        requestForProposal.setImage(readBytes(imagePath));
        requestForProposal.setAccount(account);
        requestForProposalRepository.save(requestForProposal);
        }

        private byte[] readBytes(String filePath) throws IOException {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
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
    public void updateRequestForProposal(Long idRequest, String description, String imagePath) throws IOException {

        RequestForProposal requestForProposal = requestForProposalRepository.findById(idRequest).orElse(null);
        if (requestForProposal == null) {
            throw new RuntimeException("request photo with id " + idRequest + " not found");
        }

        requestForProposal.setDescription(description);
        requestForProposal.setImage(readBytes(imagePath));

        requestForProposalRepository.save(requestForProposal);
    }

    @Override
    public void removeRequestProposal(Long idRequest) {

        requestForProposalRepository.deleteById(idRequest);
    }

}
