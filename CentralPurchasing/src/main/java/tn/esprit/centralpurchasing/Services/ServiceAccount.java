package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Repository.AccountRepository;

@Service
@AllArgsConstructor
public class ServiceAccount implements IServiceAccount{
    private AccountRepository accountRepository;
    @Override
    public Account SearchAccount(Long idAccount) {
        return accountRepository.findById(idAccount).orElse(null);
    }
}
