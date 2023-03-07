package tn.esprit.centralpurchasing.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.RequestForProposal;

import java.io.IOException;
import java.util.List;

public interface IServiceRequestForProposal {




    public void addRequestForProposal(String description, String imagePath, Long idAccount) throws IOException ;

    public void updateRequestForProposal(Long idrequest, String description, String imagePath) throws IOException;


    public List<RequestForProposal> findAllRequests();

    public RequestForProposal searchRequestForProposal(Long idRequest);


        public void removeRequestProposal(Long idRequest);




}
