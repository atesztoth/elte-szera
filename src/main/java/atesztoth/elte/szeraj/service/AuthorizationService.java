package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.Domain.CurrentUser;

public interface AuthorizationService {
    CurrentUser getPrincipal();
}
