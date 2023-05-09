package tn.esprit.centralpurchasing.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.RequestForProposal;

import java.io.IOException;
import java.util.List;

public interface IServiceRequestForProposal {




    public RequestForProposal addRequestForProposal(MultipartFile image, Long idAccount , String description) throws IOException ;


    public RequestForProposal updateRequestForProposal(MultipartFile image, Long idAccount , String description, Long idrequest) throws IOException ;


        public List<RequestForProposal> findAllRequests();

    public RequestForProposal searchRequestForProposal(Long idRequest);


        public void removeRequestProposal(Long idRequest);




}
