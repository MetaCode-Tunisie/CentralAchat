package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Role;

import java.util.List;

public interface IServiceRole {
    List<Role> getAllRole();
    void addRoles();
}
