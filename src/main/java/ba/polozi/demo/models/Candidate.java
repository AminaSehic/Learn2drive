package ba.polozi.demo.models;
import ba.polozi.demo.requests.CandidateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Candidate extends User {
    @ManyToOne
    @JoinColumn(
            name = "instructor_id"
    )
    private Instructor instructor;
    public Candidate(CandidateRequest candidateRequest, Instructor instructor){
        setFirstName(candidateRequest.getFirstName());
        setLastName(candidateRequest.getLastName());
        setPhoneNumber(candidateRequest.getPhoneNumber());
        setEmail(candidateRequest.getEmail());
        setInstructor(instructor);
    }
}