package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Entities.RequestForProposal;
import tn.esprit.centralpurchasing.Services.IServiceRequestForProposal;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/RequestForProposalImage")
public class RequestForProposalController {
    private IServiceRequestForProposal iServiceRequestForProposal;


    // http://localhost:8585/add
    @PostMapping(path="/add/{idAccount}",consumes = {MULTIPART_FORM_DATA_VALUE   })
    public RequestForProposal addRequesForProposal(@RequestParam MultipartFile file, @PathVariable Long idAccount, @RequestParam String description) throws IOException {

        return iServiceRequestForProposal.addRequestForProposal(file , idAccount,description);

    }





        // http://localhost:8585/update
    @PostMapping(path="/update/{idAccount}/{description}/{idrequest}",consumes = {MULTIPART_FORM_DATA_VALUE   })
    public RequestForProposal updateRequesForProposal(@RequestParam MultipartFile file, @PathVariable Long idAccount, @PathVariable String description, @PathVariable Long idrequest) throws IOException {

        return iServiceRequestForProposal.updateRequestForProposal(file,idAccount,description,idrequest);

    }

    // http://localhost:8585/findAllRequests
        @GetMapping("/findAllRequests")
    public List<RequestForProposal> findAllRequests(){
        return iServiceRequestForProposal.findAllRequests();
    }

    // http://localhost:8585/searchRequestForProposal
    @GetMapping("/searchRequestForProposal/{idRequest}")
    public RequestForProposal searchRequestForProposal(@PathVariable Long idRequest){
        return  iServiceRequestForProposal.searchRequestForProposal(idRequest);
    }



    // http://localhost:8585/removeRequestForProposal
    @DeleteMapping("/removeRequestForProposal/{idRequest}")
    public void removeRequestProposal(@PathVariable Long idRequest){
        iServiceRequestForProposal.removeRequestProposal(idRequest);
    }

}
