package ba.polozi.demo.repositories;

import ba.polozi.demo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Boolean existsByUsername(String username);
}