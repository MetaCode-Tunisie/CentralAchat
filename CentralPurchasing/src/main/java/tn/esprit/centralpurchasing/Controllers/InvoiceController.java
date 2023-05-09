package tn.esprit.centralpurchasing.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Services.IServiceInvoice;

@RestController
@AllArgsConstructor
public class InvoiceController {
    IServiceInvoice iServiceInvoice;
    @PostMapping("AddInvoice/{idAccount}/{ref}")
public void AddInvoice(@PathVariable String ref ,@PathVariable Long idAccount){
          iServiceInvoice.addInvoice(ref,idAccount);
    }
}
