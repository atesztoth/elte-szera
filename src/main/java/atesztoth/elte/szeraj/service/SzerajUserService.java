package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.Domain.PresentationUser;
import atesztoth.elte.szeraj.data.User;
import atesztoth.elte.szeraj.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SzerajUserService implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findById(username);
    }

    @Override
    public Optional<User> getById(String username) {
        return getByUsername(username);
    }

    @Override
    public User createUser(PresentationUser presentationUser) {
        User user = new User();
        user.setUsername(presentationUser.getUsername());
        user.setFirstName(presentationUser.getFirstName());
        user.setLastName(presentationUser.getLastName());
        user.setPassword(new BCryptPasswordEncoder().encode(presentationUser.getPassword()));
        user.setType(presentationUser.getType());
        return userRepository.save(user);
    }


}
