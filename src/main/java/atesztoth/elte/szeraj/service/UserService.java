package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.Domain.PresentationUser;
import atesztoth.elte.szeraj.data.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByUsername(String username);

    Optional<User> getById(String username);

    User createUser(PresentationUser user);
}