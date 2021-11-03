package ba.polozi.demo.repositories;

import ba.polozi.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//public interface UserRepository extends CrudRepository<User, Long> {
//    User getUserByUsername(String username);
//    Optional<User> findByUsername(String username);
//}

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Boolean existsByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}