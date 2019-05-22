package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.presentation.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public interface AuthorizationService {
    CurrentUser getPrincipal();
    SecurityContext getSecurityContext();
    Authentication getAuth();
}
