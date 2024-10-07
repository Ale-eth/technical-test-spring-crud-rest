package apexlict.technicaltest.infrastructure.controllers;


import apexlict.technicaltest.application.services.UserService;
import apexlict.technicaltest.domain.Role;
import apexlict.technicaltest.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/users")   // http://localhost:8081/api/users/
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    // READ
    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserByID(@PathVariable Long userID) {
        return userService.getUserByID(userID)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK)) // Devolver el usuario recuperado
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("")
    public ResponseEntity<Set<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userID}/roles")
    public ResponseEntity<Set<Role>> getUserRolesByID(@PathVariable Long userID) {
        return userService.getUserRolesByID(userID)
                .map(roles -> new ResponseEntity<>(roles, HttpStatus.OK)) // Devolver los roles del usuario
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user); // Llama al servicio para crear el usuario
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // Devuelve el usuario creado con el estado 201
    }

    // UPDATE
    @PutMapping("/{userID}")
    public ResponseEntity<User> updateUserByID( @PathVariable Long userID,  @RequestBody User userDetails) {
        // Llamar al servicio para actualizar el usuario
        User updatedUser = userService.updateUserByID(userID, userDetails.getName(), userDetails.getEmail(), userDetails.getPassword());

        return ResponseEntity.ok(updatedUser); // 200 OK con el usuario actualizado
    }

    // DELETE
    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> deleteUserByID(@PathVariable Long userID) {
        if (userService.getUserByID(userID).isPresent()) {
            userService.deleteUserById(userID);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}

