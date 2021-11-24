package ba.polozi.demo.models;

import ba.polozi.demo.requests.InstructorRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User {
    private String car;

    public Instructor(InstructorRequest instructorRequest){
        setFirstName(instructorRequest.getFirstName());
        setLastName(instructorRequest.getLastName());
        setEmail(instructorRequest.getEmail());
        setPhoneNumber(instructorRequest.getPhoneNumber());
        setUsername(instructorRequest.getUsername());
        setCar(instructorRequest.getCar());
    }
}