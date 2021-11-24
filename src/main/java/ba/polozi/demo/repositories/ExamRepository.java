package ba.polozi.demo.repositories;

import ba.polozi.demo.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ExamRepository extends JpaRepository<Exam, Long> {
    public List<Exam> findExamsByCandidateId(Long candidateId);
    List<Exam> findExamById(Long id);
}