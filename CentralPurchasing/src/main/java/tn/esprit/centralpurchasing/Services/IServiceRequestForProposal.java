package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.RequestForProposal;

import java.util.List;

public interface IServiceRequestForProposal {

    public RequestForProposal addRequestForProposal(RequestForProposal requestForProposal, Long idAccount);

    public List<RequestForProposal> findAllRequests();

    public RequestForProposal searchRequestForProposal(Long idRequest);

    public RequestForProposal updateRequest(RequestForProposal requestForProposal);

    public void removeRequestProposal(Long idRequest);

 //   public void affectUserToProposalRequest(Long idUser, Long idRequest);


}
