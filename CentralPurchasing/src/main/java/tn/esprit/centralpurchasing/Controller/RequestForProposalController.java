package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.RequestForProposal;
import tn.esprit.centralpurchasing.Services.IServiceRequestForProposal;

import java.util.List;

@RestController
@AllArgsConstructor
public class RequestForProposalController {
    private IServiceRequestForProposal iServiceRequestForProposal;

    @PostMapping("/addRequestForProposal")
    public RequestForProposal addRequestForProposal(@RequestBody RequestForProposal requestForProposal, Long idAccount){
        return iServiceRequestForProposal.addRequestForProposal(requestForProposal,idAccount);
    }

    @GetMapping("/findAllRequests")
    public List<RequestForProposal> findAllRequests(){
        return iServiceRequestForProposal.findAllRequests();
    }

    @GetMapping("/searchRequestForProposal/{idRequest}")
    public RequestForProposal searchRequestForProposal(@PathVariable Long idRequest){
        return  iServiceRequestForProposal.searchRequestForProposal(idRequest);
    }

    @PutMapping("/updateRequestForProposal")
    public RequestForProposal updateRequest(@RequestBody RequestForProposal requestForProposal){
        return iServiceRequestForProposal.updateRequest(requestForProposal);
    }

    @DeleteMapping("/removeRequestForProposal/{idRequest}")
    public void removeRequestProposal(@PathVariable Long idRequest){
        iServiceRequestForProposal.removeRequestProposal(idRequest);
    }

   /* @PostMapping("/affectUserToProposalRequest/{idUser}/{idRequest}")
    public void affectUserToProposalRequest(@PathVariable Long idUser,@PathVariable Long idRequest){
        iServiceRequestForProposal.affectUserToProposalRequest(idUser,idRequest);
    }*/
}
