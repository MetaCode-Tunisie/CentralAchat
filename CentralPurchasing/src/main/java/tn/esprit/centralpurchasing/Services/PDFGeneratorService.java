package tn.esprit.centralpurchasing.Services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import tn.esprit.centralpurchasing.Entities.Invoice;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;
import java.util.List;

@Service
@AllArgsConstructor
public class PDFGeneratorService {

    OrdersRepository ordersRepository;



    public Orders export(HttpServletResponse response,String ref) throws IOException, TransformerException {

        Double DeliveryPrice = 0.0;
        List<Orders> orders = ordersRepository.findByRef(ref);
        Orders order = orders.get(0);
        if(order.getDeliveryOption().equals(true)){
            DeliveryPrice = 7.0;
        }
        Invoice invoice = ordersRepository.findInvoice(order.getIdOrder());
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\MSI\\IdeaProjects\\CentralPurchasing\\src\\Factures\\Facture.pdf"));
        Font f= FontFactory.getFont(FontFactory.TIMES_ROMAN,22,Color.RED);
        Font f2= FontFactory.getFont(FontFactory.TIMES_ROMAN,22,Color.BLACK);
        Font f1= FontFactory.getFont(FontFactory.TIMES_ROMAN,15.0f,Color.BLACK);

        Image image = Image.getInstance("D:\\logo (2).png");
        image.scaleAbsoluteWidth(160);
        image.scaleAbsoluteHeight(92);
        Paragraph p7 = new Paragraph("\n");
        Paragraph paragraph = new Paragraph("FACTURE", f2);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        Paragraph p1 = new Paragraph ("Information du Client    :",f);
        Paragraph p10 = new Paragraph("Information du Commande  :",f);
        Paragraph p = new Paragraph ("Nom Client                  :      "+ order.getAccounts().getFirstname(),f1);
        Paragraph p9 = new Paragraph("Prenom du Client            :      "+order.getAccounts().getLastname(),f1);
        Paragraph p2 = new Paragraph("Date_Commande               :      "+invoice.getFactureDate(),f1);
        Paragraph p3 = new Paragraph("Prix Livraison              :      "+DeliveryPrice+"    DT",f1);
        Paragraph p4 = new Paragraph("Total des produits          :      "+order.getInvoice().getTotalprice()+"    DT",f1);

        document.open();
        image.setAlignment(Image.ALIGN_RIGHT);
        document.add(paragraph);
        document.add(image);
        document.add(new Paragraph(" "));
        document.add(p1);
        document.add(new Paragraph(" "));
        document.add(p7);
        document.add(p);
        document.add(p9);
        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(new Paragraph(" "));
        document.add(p7);
        document.add(p10);
        document.add(new Paragraph(" "));
        document.add(p7);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Nom Produit",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Prix",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Quantité",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        for(Orders o: orders){
            cell = new PdfPCell(new Phrase(o.getProduct().getName(),FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.WHITE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(o.getProduct().getPrice()),FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.WHITE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(o.getQuantity()),FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.WHITE);
            table.addCell(cell);
        }
        document.add(table);


        document.close();

        return order;
    }


}
