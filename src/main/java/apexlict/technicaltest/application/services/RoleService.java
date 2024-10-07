package apexlict.technicaltest.application.services;

import apexlict.technicaltest.domain.Role;
import apexlict.technicaltest.infrastructure.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // CREATE
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // READ
    public Optional<Role> getRoleByID(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // UPDATE
    public Role updateRoleByID(Long id, String newRoleName) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
                existingRole.setName(newRoleName);

        return roleRepository.save(existingRole);
    }

    // DELETE
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
