package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.presentation.CurrentUser;
import atesztoth.elte.szeraj.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SzerajUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger( SzerajUserDetailsService.class );

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Authenticating " + username);
        Optional<User> user = userService.getByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        logger.info("User data " + user.toString());
        return new CurrentUser(user.get());
    }
}