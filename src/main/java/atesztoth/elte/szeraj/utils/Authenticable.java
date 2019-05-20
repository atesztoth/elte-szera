package atesztoth.elte.szeraj.utils;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Mark authenticable objects with this interface.
 */
public interface Authenticable {
    /**
     * Should return the username the objects is authenticable by.
     * @return String
     */
    public String getUsername();


    /**
     * Should return the hashed password that can be used for authentication.
     * @return String
     */
    public String getPassword();

    /**
     *
     */
    public Collection<? extends GrantedAuthority> getAuthorities();
}