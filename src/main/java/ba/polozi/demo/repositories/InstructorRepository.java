package ba.polozi.demo.repositories;

import ba.polozi.demo.models.Instructor;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
//@Repository
//public interface InstructorRepository extends CrudRepository<Instructor, Long> {
//}

@Repository
@Transactional(readOnly = true)
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}