package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.presentation.UserPresentation;
import atesztoth.elte.szeraj.data.User;
import atesztoth.elte.szeraj.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserPresentation create(UserPresentation userPresentation) {
        User user = User.createFromPresentation(userPresentation);
        userRepository.save(user);
        userPresentation.setManagedUser(user);
        return userPresentation;
    }

    @Override
    public UserPresentation remove(UserPresentation presentation) {
        Optional<User> user = userRepository.findById(presentation.getId());
        return user.map(UserPresentation::createFromEntity).orElse(null);
    }


}
