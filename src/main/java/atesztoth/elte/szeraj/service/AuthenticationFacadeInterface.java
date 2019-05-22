package atesztoth.elte.szeraj.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public interface AuthenticationFacadeInterface {
    Authentication getAuthentication();
    SecurityContext getSecurityContext();
}
