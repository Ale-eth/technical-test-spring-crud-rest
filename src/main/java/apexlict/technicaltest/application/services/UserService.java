package apexlict.technicaltest.application.services;

import apexlict.technicaltest.domain.Role;
import apexlict.technicaltest.domain.User;
import apexlict.technicaltest.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Repositorio para acceso a datos


    // CREATE
    public User createUser(User user) {
        return userRepository.save(user); // Guarda el usuario en la base de datos
    }

    // READ
    public Optional<User> getUserByID(Long id){
        return userRepository.findById(id);
    }

    public Set<User> getAllUsers() {
        return Set.copyOf(userRepository.findAll());
    }

    public Optional<Set<Role>> getUserRolesByID(Long userId) {
        return userRepository.findById(userId)
                .map(User::getRoles);
    }

    // UPDATE
    public User updateUserByID(Long id, String newName, String newEmail, String newPassword) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Actualizar los campos
        existingUser.setName(newName);
        existingUser.setEmail(newEmail);
        existingUser.setPassword(newPassword);

        // Guardar el usuario actualizado
        return userRepository.save(existingUser);
    }

    // DELETE
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
