package atesztoth.elte.szeraj.Domain;

import atesztoth.elte.szeraj.data.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(),  AuthorityUtils.createAuthorityList(user.getType().toString()));
        this.user = user;
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
        return "CurrentUser{user= "+ user +"} " + super.toString();
    }
}