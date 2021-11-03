package ba.polozi.demo.responses;

import ba.polozi.demo.models.Instructor;

public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    public InstructorResponse(Instructor i){
        this.id= i.getId();
        this.firstName= i.getFirstName();
        this.lastName= i.getLastName();
        this.email=i.getEmail();
        this.phoneNumber=i.getPhoneNumber();
    }
}