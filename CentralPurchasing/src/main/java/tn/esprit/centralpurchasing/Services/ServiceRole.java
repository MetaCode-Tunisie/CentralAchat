package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Role;
import tn.esprit.centralpurchasing.Entities.TypeRole;
import tn.esprit.centralpurchasing.Repository.RoleRepository;

import java.util.List;
@Service @AllArgsConstructor
public class ServiceRole implements IServiceRole{

    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole()
    {
        return roleRepository.findAll();
    }

    @Override
    public void addRoles(){
        if (roleRepository.findAll().isEmpty())
        {
            Role roleADMIN = new Role();
            roleADMIN.setTypeRole(TypeRole.ADMIN);
            roleRepository.save(roleADMIN);

            Role roleOPERATOR = new Role();
            roleOPERATOR.setTypeRole(TypeRole.OPERATOR);
            roleRepository.save(roleOPERATOR);

            Role roleSUPPLIER = new Role();
            roleSUPPLIER.setTypeRole(TypeRole.SUPPLIER);
            roleRepository.save(roleSUPPLIER);

            Role roleBuyer = new Role();
            roleBuyer.setTypeRole(TypeRole.BUYER);
            roleRepository.save(roleBuyer);
        }
    }
}
