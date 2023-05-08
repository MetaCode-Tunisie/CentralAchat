package tn.esprit.centralpurchasing.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;
import tn.esprit.centralpurchasing.Services.EmailSenderService;
import tn.esprit.centralpurchasing.Services.PDFGeneratorService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;

    OrdersRepository ordersRepository;

    @Autowired
    private EmailSenderService Emailservice;


    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate/{ref}")
    public void generatePDF(HttpServletResponse response, @PathVariable String ref) throws IOException, MessagingException, TransformerException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=FACTURE_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Orders order = this.pdfGeneratorService.export(response,ref);
        Emailservice.sendEmailWithAttachment(order.getAccounts().getEmail(),"This is your invoice","Invoice");


    }
}
