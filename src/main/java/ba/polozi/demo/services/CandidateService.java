package ba.polozi.demo.services;

import ba.polozi.demo.models.Candidate;
import ba.polozi.demo.repositories.CandidateRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CandidateService {
    @Autowired
    private final CandidateRepository candidateRepository;

    public List<Candidate> findAll() {
        return (List<Candidate>) candidateRepository.findAll();
    }

    public Candidate saveCandidate(Candidate c) {
        return candidateRepository.save(c);
    }

    public Candidate findCandidateById(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            return null;
        }
    }

    public Candidate findCandidateByUsername(String username) {
        Optional<Candidate> candidate = candidateRepository.findCandidateByUsername(username);
        if(candidate.isPresent()){
            return candidate.get();
        }
        else {
            return null;
        }
    }

    public void deleteById(Long id) {
        candidateRepository.deleteById(id);
    }
}