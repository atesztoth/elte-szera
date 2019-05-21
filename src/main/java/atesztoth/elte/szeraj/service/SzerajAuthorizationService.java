package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.Domain.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SzerajAuthorizationService implements AuthorizationService {

    private static Logger logger = LoggerFactory.getLogger(SzerajAuthorizationService.class);

    @Autowired
    private AuthenticationFacadeInterface authFacade;

    private Authentication getAuth() {
        return authFacade.getAuthentication();
    }

    @Override
    public CurrentUser getPrincipal() {
        return (CurrentUser) getAuth().getPrincipal();
    }

}

