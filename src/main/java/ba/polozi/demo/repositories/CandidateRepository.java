package ba.polozi.demo.repositories;

import ba.polozi.demo.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findCandidateByUsername(String username);
}