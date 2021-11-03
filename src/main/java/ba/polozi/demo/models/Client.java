package ba.polozi.demo.models;
import ba.polozi.demo.requests.ClientRequest;
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
public class Client extends User {
    @ManyToOne
    @JoinColumn(
            name = "instructor_id"
    )
    private Instructor instructor;


    public Client(ClientRequest clientRequest, Instructor instructor){
        setFirstName(clientRequest.getFirstName());
        setLastName(clientRequest.getLastName());
        setPhoneNumber(clientRequest.getPhoneNumber());
        setEmail(clientRequest.getEmail());
        setInstructor(instructor);
    }
}