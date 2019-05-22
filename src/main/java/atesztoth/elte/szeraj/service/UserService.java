package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.presentation.UserPresentation;
import atesztoth.elte.szeraj.data.User;

import java.util.Optional;

public interface UserService extends ContentService<UserPresentation> {
    Optional<User> getByUsername(String username);

    Optional<User> getById(String username);
}