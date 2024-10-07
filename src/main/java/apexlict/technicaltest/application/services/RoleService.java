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
    private RoleRepository roleRepository; // Repositorio para acceso a datos

    // CREATE
    public Role createRole(Role role) {
        return roleRepository.save(role); // Guarda el rol en la base de datos
    }

    // READ
    public Optional<Role> getRoleByID(Long id) {
        return roleRepository.findById(id); // Retorna un rol por su ID
    }

    // UPDATE
    public Role updateRoleByID(Long id, String newRoleName) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Actualizar el nombre del rol
        existingRole.setName(newRoleName);

        // Guardar el rol actualizado
        return roleRepository.save(existingRole);
    }

    // DELETE
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id); // Elimina el rol por su ID
    }

    // Optional: Puedes agregar métodos adicionales como obtener todos los roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll(); // Retorna una lista de todos los roles
    }
}
