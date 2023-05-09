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

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/RequestForProposalImage")
public class RequestForProposalController {
    private IServiceRequestForProposal iServiceRequestForProposal;


    @PostMapping(path="/add/{idAccount}/{description}",consumes = {MULTIPART_FORM_DATA_VALUE   })
    public RequestForProposal addRequesForProposal(@RequestParam MultipartFile file, @PathVariable Long idAccount, @PathVariable String description) throws IOException {

        return iServiceRequestForProposal.addRequestForProposal(file , idAccount,description);

    }


    @PostMapping(path="/update/{idAccount}/{description}/{idrequest}",consumes = {MULTIPART_FORM_DATA_VALUE   })
    public RequestForProposal updateRequesForProposal(@RequestParam MultipartFile file, @PathVariable Long idAccount, @PathVariable String description, @PathVariable Long idrequest) throws IOException {

        return iServiceRequestForProposal.updateRequestForProposal(file,idAccount,description,idrequest);

    }

        @GetMapping("/findAllRequests")
    public List<RequestForProposal> findAllRequests(){
        return iServiceRequestForProposal.findAllRequests();
    }

    @GetMapping("/searchRequestForProposal/{idRequest}")
    public RequestForProposal searchRequestForProposal(@PathVariable Long idRequest){
        return  iServiceRequestForProposal.searchRequestForProposal(idRequest);
    }



    @DeleteMapping("/removeRequestForProposal/{idRequest}")
    public void removeRequestProposal(@PathVariable Long idRequest){
        iServiceRequestForProposal.removeRequestProposal(idRequest);
    }

}
