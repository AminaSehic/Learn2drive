package ba.polozi.demo.models;

import ba.polozi.demo.requests.AdminRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
public class Admin extends User {

    public Admin(AdminRequest adminRequest) {
        this.setFirstName(adminRequest.getFirstName());
        this.setLastName(adminRequest.getLastName());
        this.setPhoneNumber(adminRequest.getPhoneNumber());
        this.setEmail(adminRequest.getEmail());
        this.setUsername(adminRequest.getUsername());
    }
}