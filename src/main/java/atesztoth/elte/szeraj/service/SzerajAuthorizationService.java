package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.Domain.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class SzerajAuthorizationService implements AuthorizationService {

    private static Logger logger = LoggerFactory.getLogger(SzerajAuthorizationService.class);
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @Override
    public CurrentUser getPrincipal() {
        return (CurrentUser) auth.getPrincipal();
    }

}

