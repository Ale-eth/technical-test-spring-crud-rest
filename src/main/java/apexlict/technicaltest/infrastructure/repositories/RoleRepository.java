package apexlict.technicaltest.infrastructure.repositories;

import apexlict.technicaltest.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}