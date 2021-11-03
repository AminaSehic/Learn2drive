package ba.polozi.demo.models;

import ba.polozi.demo.requests.InstructorRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User {
    @NotBlank
    private String car;
    public Instructor(InstructorRequest instructorRequest){
        setFirstName(instructorRequest.getFirstName());
        setLastName(instructorRequest.getLastName());
        setEmail(instructorRequest.getEmail());
        setPhoneNumber(instructorRequest.getPhoneNumber());
        setCar(instructorRequest.getCar());
    }
}