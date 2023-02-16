package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceOrderr implements IServiceOrder{
    OrdersRepository orderrrRepository;
    @Override
    public Orders AddOrder(Orders o) {
        return orderrrRepository.save(o);
    }

    @Override
    public Orders UpdateOrder(Orders o) {
        return orderrrRepository.save(o);
    }

    @Override
    public void DeleteOrder(Long id) {
         orderrrRepository.findById(id)
                 .map(orders ->{ orders.setIsEnable(false);
       return   orderrrRepository.save(orders);}).orElse(null);
    }

    @Override
    public List<Orders> AfficherOrder() {
        return orderrrRepository.findAll();
    }
}
