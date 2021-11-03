package ba.polozi.demo.services;

import ba.polozi.demo.models.UserPrincipal;
import ba.polozi.demo.models.User;
import ba.polozi.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private static final String USER_NOT_FOUND_MSG = "User with email %s not found";
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return UserPrincipal.create(user);
    }
}
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//        Optional<User> user = userRepository.findByUsername(s);
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException("User does not exist");
//        }
//        return user.get();
//    }
