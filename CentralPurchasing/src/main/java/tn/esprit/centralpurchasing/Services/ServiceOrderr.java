package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.PeriodType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Repository.AccountRepository;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;
import tn.esprit.centralpurchasing.Repository.ProductRepository;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceOrderr implements IServiceOrder{
    OrdersRepository orderrrRepository;
    ProductRepository productRepository;
     AccountRepository accountRepository;


    @Override
    public Orders UpdateOrder( Long idOrder,int quantity) {
        Orders o = orderrrRepository.findById(idOrder).orElse(null);
        o.setQuantity(quantity);
        return orderrrRepository.save(o);
    }



    @Override
    public List<Orders> AfficherOrder(Long idAccount ) {
        return orderrrRepository.findByValidAndAccountsIdAccount(true,idAccount);
    }

    public Orders AjouterCommecart(Long idProduct , int quantity, Long idAccount){

        Product product = productRepository.findById(idProduct).orElse(null);
        Account account = accountRepository.findById(idAccount).orElse(null);
        Orders  orders = new Orders();
        orders.setAccounts(account);
        orders.setQuantity(quantity);
        orders.setProduct(product);
       return orderrrRepository.save(orders);

    }

    @Override
    public List<Orders> Affichercart(Long idAccount) {
        return orderrrRepository.findByValidAndAccountsIdAccount(false,idAccount);
    }



    //   String randomString = RandomStringGenerator.generateRandomString();

    public void  ChekedValid(Long idAccount,Boolean delivery){
        List<Orders> ordersbyuser =orderrrRepository.findByAccountsIdAccount(idAccount);
        String ref=this.generateRandomString(6);
        for(Orders orders : ordersbyuser){
            if (orders.getValid() == false) {
                if (orders.getQuantity()<= orders.getProduct().getQuantity()) {

                    orders.setRef(ref);
                    orders.setValid(true);
                    orders.getProduct().setQuantity(orders.getProduct().getQuantity()-orders.getQuantity());

                }
                if(delivery.equals(true)){
                    orders.setDeliveryOption(true);
                }
                orderrrRepository.save(orders);
            }
        }
    }

            public String generateRandomString(int length){
        return RandomStringUtils.randomAlphanumeric(length);
            }




    @Scheduled(cron = "* * * 1 * *")
    public void deleteCartsAfterTreeDays() throws ParseException {
        List<Orders> orders= orderrrRepository.findByValid(false);
        Date dateSys=new Date();

        for (Orders cart:orders)
        {
            long diff = dateSys.getTime() - cart.getOrderDate().getTime();
            int Jours = ((int)diff / (1000*60*60*24));

            System.out.println("dateCart : "+cart.getOrderDate()+" dateSys : "+dateSys+" difference :"+Jours);

            if(Jours>3)
            {
                orderrrRepository.deleteById(cart.getIdOrder());
            }

        }


    }
}
