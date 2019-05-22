package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.presentation.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

@Service
public class SzerajAuthorizationService implements AuthorizationService {

    private static Logger logger = LoggerFactory.getLogger(SzerajAuthorizationService.class);

    @Autowired
    private AuthenticationFacadeInterface authFacade;

    public Authentication getAuth() {
        return authFacade.getAuthentication();
    }

    @Override
    public CurrentUser getPrincipal() {
        try {
            return (CurrentUser) getAuth().getPrincipal();
        } catch (ClassCastException e) {
            logger.error(e.toString());
            return null;
        }
    }

    @Override
    public SecurityContext getSecurityContext() {
        return authFacade.getSecurityContext();
    }

}

