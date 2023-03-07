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

@RestController
@AllArgsConstructor
@RequestMapping("/RequestForProposalImage")
public class RequestForProposalController {
    private IServiceRequestForProposal iServiceRequestForProposal;


    @PostMapping("/addRequestForProposal")
    public ResponseEntity<?> addRequestForProposal(@RequestParam String description, @RequestParam String imagePath, @RequestParam Long idAccount) {
        try {
            iServiceRequestForProposal.addRequestForProposal(description, imagePath, idAccount);
            return ResponseEntity.ok("Request photo added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Request photo");
        }

    }

        @GetMapping("/findAllRequests")
    public List<RequestForProposal> findAllRequests(){
        return iServiceRequestForProposal.findAllRequests();
    }

    @GetMapping("/searchRequestForProposal/{idRequest}")
    public RequestForProposal searchRequestForProposal(@PathVariable Long idRequest){
        return  iServiceRequestForProposal.searchRequestForProposal(idRequest);
    }

    @PostMapping("/update/{idrequest}")
    public ResponseEntity<?> updateRequestForProposal(@PathVariable Long idrequest,
                                                @RequestParam String description,
                                                @RequestParam String imagePath) {
        try {
            iServiceRequestForProposal.updateRequestForProposal(idrequest,description,imagePath);
            return ResponseEntity.ok("request for proposal updated successfully");
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/removeRequestForProposal/{idRequest}")
    public void removeRequestProposal(@PathVariable Long idRequest){
        iServiceRequestForProposal.removeRequestProposal(idRequest);
    }

}
