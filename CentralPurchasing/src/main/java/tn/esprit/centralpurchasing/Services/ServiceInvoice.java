package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Invoice;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Repository.InvoiceRepository;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class ServiceInvoice implements IServiceInvoice{
    OrdersRepository ordersRepository;
    InvoiceRepository invoiceRepository;
    @Override
    public void addInvoice(String ref,Long iduser){
        Double Totalprice=0.0;
        List<Orders> orders = ordersRepository.findByRefAndValidAndAccountsIdAccount(ref,true,iduser);
        Invoice invoice = new Invoice();
        Double deliveryPrice = 0.0;
        Boolean deliveryOption = orders.get(0).getDeliveryOption();
        if(deliveryOption){
            deliveryPrice = 7.0;
        }else{
            deliveryPrice=0.0;
        }
        for (Orders o :orders) {

            if (o.getInvoice()==null){
                Totalprice+=(o.getProduct().getPrice()*o.getQuantity());

                o.setInvoice(invoice);
            }
        }
        Totalprice= Totalprice+deliveryPrice;
        invoice.setTotalprice(Totalprice);
        invoiceRepository.save(invoice);
    }
}


