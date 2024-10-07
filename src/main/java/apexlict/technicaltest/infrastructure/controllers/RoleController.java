package apexlict.technicaltest.infrastructure.controllers;

import apexlict.technicaltest.application.services.RoleService;
import apexlict.technicaltest.domain.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")   // http://localhost:8081/api/roles/
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // READ
    @GetMapping("/{roleID}")
    public ResponseEntity<Role> getRoleByID(@PathVariable Long roleID) {
        return roleService.getRoleByID(roleID)
                .map(role -> new ResponseEntity<>(role, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{roleID}")
    public ResponseEntity<Role> updateRoleByID(@PathVariable Long roleID, @RequestBody Role roleDetails) {
        Role updatedRole = roleService.updateRoleByID(roleID, roleDetails.getName());

        return ResponseEntity.ok(updatedRole);
    }

    // DELETE
    @DeleteMapping("/{roleID}")
    public ResponseEntity<Void> deleteRoleByID(@PathVariable Long roleID) {
        if (roleService.getRoleByID(roleID).isPresent()) {
            roleService.deleteRoleById(roleID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

