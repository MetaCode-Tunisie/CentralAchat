package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Dto.UserDto;
import tn.esprit.centralpurchasing.Entities.Role;
import tn.esprit.centralpurchasing.Entities.TypeRole;
import tn.esprit.centralpurchasing.Repository.AccountRepository;
import tn.esprit.centralpurchasing.Repository.RoleRepository;
import java.util.*;

@Service @AllArgsConstructor
public class ServiceAccount implements IServiceAccount{
    AccountRepository accountRepository;
    RoleRepository roleRepository;

    IServiceEmail serviceEmail;
    @Override
    public Account SearchAccount(Long idAccount) {
        return accountRepository.findById(idAccount).orElse(null);
    }
    //************************************ BUYER *****************************************//
    @Override
    public Account SignUp(Account account)
    {
        Role role= roleRepository.findByTypeRole(TypeRole.BUYER);
        account.getRoles().add(role);

        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));

        return accountRepository.save(account);
    }

    @Override
    public Boolean updateBuyer(UserDto userDto) {
        Account account=accountRepository.findByEmail(userDto.getEmail());
        if(account==null)
            return false;
        if(!userDto.getPassword().equals(""))
            account.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        if(!userDto.getFirstname().equals(""))
            account.setFirstname(userDto.getFirstname());
        if(!userDto.getLastname().equals(""))
            account.setFirstname(userDto.getLastname());
        if(!userDto.getPhoneNumber().equals(""))
            account.setPhoneNumber(userDto.getPhoneNumber());
        accountRepository.save(account);
        return true;
    }

    @Override
    public Boolean disableAccount(UserDto userDto) {
        Account account=accountRepository.findByEmail(userDto.getEmail());
        if(account==null)
            return false;
        account.setIsAccountNonLocked(false);
        accountRepository.save(account);
        return true;
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
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));

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
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Boolean updateOperator(UserDto userDto) {
        Account account=accountRepository.findByEmail(userDto.getEmail());
        if(account==null)
            return false;
        if(!userDto.getPassword().equals(""))
            account.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        if(!userDto.getFirstname().equals(""))
            account.setFirstname(userDto.getFirstname());
        if(!userDto.getLastname().equals(""))
            account.setFirstname(userDto.getLastname());
        if(!userDto.getPhoneNumber().equals(""))
            account.setPhoneNumber(userDto.getPhoneNumber());
        accountRepository.save(account);
        return true;
    }

    //************************************ SUPPLIER *****************************************//
    @Override
    public Account addSupplier(Account account) {
        Role role= roleRepository.findByTypeRole(TypeRole.SUPPLIER);
        account.getRoles().add(role);
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));

        return accountRepository.save(account);
    }

    @Override
    public Boolean updateSupplier(UserDto userDto) {
        Account account=accountRepository.findByEmail(userDto.getEmail());
        if(account==null)
            return false;
        if(!userDto.getPassword().equals(""))
            account.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        if(!userDto.getFirstname().equals(""))
            account.setFirstname(userDto.getFirstname());
        if(!userDto.getLastname().equals(""))
            account.setFirstname(userDto.getLastname());
        if(!userDto.getPhoneNumber().equals(""))
            account.setPhoneNumber(userDto.getPhoneNumber());
        accountRepository.save(account);
        return true;
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
    public Boolean disaffectSupplierToOperator(String email) {
        Account supplier= accountRepository.findByEmail(email);
        if(supplier==null)
            return false;
        supplier.setSuppliers_to_operator(null);
        accountRepository.save(supplier);
        return true;
    }











    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

















    @Override
    public Boolean changePassword(String identifiant,String code,String newpassword) {

        Account account= accountRepository.findByPhoneNumberAndCodeTel(identifiant,code);
        if(account==null)
            account=accountRepository.findByEmailAndCodeTel(identifiant,code);

        if(account==null)
            account=accountRepository.findByResetToken(code);

        if(account==null)
            return false;

        account.setCodeTel(null);
        account.setResetToken(null);
        account.setPassword(new BCryptPasswordEncoder().encode(newpassword));
        accountRepository.save(account);
        return true;
    }



}
