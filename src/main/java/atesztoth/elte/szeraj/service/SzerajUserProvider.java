package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.data.Guest;
import atesztoth.elte.szeraj.data.GuestRepository;
import atesztoth.elte.szeraj.data.Receptionist;
import atesztoth.elte.szeraj.data.ReceptionistRepository;
import atesztoth.elte.szeraj.utils.SzerajUserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SzerajUserProvider implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger( SzerajUserProvider.class );

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Authenticating " + username);
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        Guest guest = guestRepository.findByUsername(username);
        Receptionist receptionist = receptionistRepository.findByUsername(username);
        logger.info("User data " + guest.getUsername());
        return new SzerajUserPrincipal(guest.getUsername().equals(username) ? guest : receptionist);
    }
}