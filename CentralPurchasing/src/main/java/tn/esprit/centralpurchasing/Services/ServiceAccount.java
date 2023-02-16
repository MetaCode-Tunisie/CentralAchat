package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Role;
import tn.esprit.centralpurchasing.Entities.TypeRole;
import tn.esprit.centralpurchasing.Repository.AccountRepository;
import tn.esprit.centralpurchasing.Repository.RoleRepository;

import java.util.List;

@Service @AllArgsConstructor
public class ServiceAccount implements IServiceAccount{
    AccountRepository accountRepository;
    RoleRepository roleRepository;
    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //************************************ BUYER *****************************************//
    @Override
    public Account SignUp(Account account)
    {
        Role role= roleRepository.findByTypeRole(TypeRole.BUYER);
        account.getRoles().add(role);
        account.setPassword(passwordEncoder().encode(account.getPassword()));

        Account admin= accountRepository.findByRolesTypeRole(TypeRole.ADMIN);

        account.setAnyaccount_to_admin(admin);

        return accountRepository.save(account);
    }

    @Override
    public Account updateBuyer(Account account) {
        return null;
    }

    @Override
    public Account disableBuyer(Long idBuyer) {
        return accountRepository.findById(idBuyer).map(account1 -> {
            account1.setIsEnabled(false);
            return accountRepository.save(account1);
        }).orElse(null);
    }

    @Override
    public Account affectBuyerToSupplier(Long idSupplier, Long idBuyer) {
       Account supplier=accountRepository.findByIdAccountAndRolesTypeRole(idSupplier,TypeRole.SUPPLIER);
       Account buyer=accountRepository.findById(idBuyer).orElse(null);

       if(supplier!=null) {
           supplier.getSuppliers_buyers().add(buyer);
           return accountRepository.save(supplier);
       }
       return null;
    }


    @Override
    public Account SignUpAdmin(Account account)
    {
        Role role= roleRepository.findByTypeRole(TypeRole.ADMIN);
        account.getRoles().add(role);
        account.setPassword(passwordEncoder().encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Account updateAdmin(Account account) {
        return accountRepository.findById(account.getIdAccount()).map(admin -> {

            return accountRepository.save(admin);
        }).orElse(null);
    }



    //************************************ OPERATOR *****************************************//
    @Override
    public Account addOperator(Account account) {
        Role role= roleRepository.findByTypeRole(TypeRole.OPERATOR);
        account.getRoles().add(role);
        account.setPassword(passwordEncoder().encode(account.getPassword()));

        Account admin= accountRepository.findByRolesTypeRole(TypeRole.ADMIN);

        account.setAnyaccount_to_admin(admin);

        return accountRepository.save(account);
    }

    @Override
    public Account updateOperator(Account account) {
        return null;
    }

    //************************************ SUPPLIER *****************************************//
    @Override
    public Account addSupplier(Account account) {
        Role role= roleRepository.findByTypeRole(TypeRole.SUPPLIER);
        account.getRoles().add(role);
        account.setPassword(passwordEncoder().encode(account.getPassword()));

        Account admin= accountRepository.findByRolesTypeRole(TypeRole.ADMIN);

        account.setAnyaccount_to_admin(admin);

        return accountRepository.save(account);
    }

    @Override
    public Account affectSupplierToOperator(Long idOperator,Long idSupplier){
        Account operator=accountRepository.findByIdAccountAndRolesTypeRole(idOperator,TypeRole.OPERATOR);
        Account supplier=accountRepository.findByIdAccountAndRolesTypeRole(idSupplier,TypeRole.SUPPLIER);

        if(operator != null && supplier !=null) {
            supplier.setSuppliers_to_operator(operator);

            return accountRepository.save(supplier);
        }


        return null;
    }

    @Override
    public Account disaffectSupplierToOperator(Long idSupplier) {
        return accountRepository.findById(idSupplier).map(supplier -> {
            supplier.setSuppliers_to_operator(null);
            return accountRepository.save(supplier);
        }).orElse(null);
    }











    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account loadUserByUsername(String username) {
        return accountRepository.findByEmail(username);
    }




}
