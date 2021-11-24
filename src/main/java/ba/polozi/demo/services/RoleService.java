package ba.polozi.demo.services;

import ba.polozi.demo.models.Role;
import ba.polozi.demo.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
