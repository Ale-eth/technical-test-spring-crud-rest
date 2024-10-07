package apexlict.technicaltest.infrastructure.repositories;

import apexlict.technicaltest.domain.Role;
import apexlict.technicaltest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.roles FROM User u WHERE u.id = :id")
    Set<Role> getUserRolesByID(@Param("id") Long id);

}
