package atesztoth.elte.szeraj.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacadeInterface {
    Authentication getAuthentication();
}
