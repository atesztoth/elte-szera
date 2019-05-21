package atesztoth.elte.szeraj.Domain;

import atesztoth.elte.szeraj.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(CurrentUser.class);

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_" + user.getType().toString()));
        this.user = user;
        logger.info("user: " + user.toString());
        logger.info("role: " + user.getType().toString());
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getType();
    }

    @Override
    public String toString() {
        return "CurrentUser{user= " + user + "} " + super.toString();
    }
}