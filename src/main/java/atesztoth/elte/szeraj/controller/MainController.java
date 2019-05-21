package atesztoth.elte.szeraj.controller;

import atesztoth.elte.szeraj.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    AuthorizationService authorizationService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView getDashboard() {
        authorizationService.getPrincipal();
        return new ModelAndView("guest-dashboard");
    }

    @RequestMapping({"/index", "/"})
    public ModelAndView index() {
        logger.debug("Getting home page");
        return new ModelAndView("index.html");
    }

    @RequestMapping(value = "/guest/dispatch", method = RequestMethod.POST)
    public ResponseEntity<Void> dispatchUser() {
        logger.warn("DISPATCHING");
        SecurityContext cc = SecurityContextHolder.getContext();
        HttpHeaders headers = new HttpHeaders();
        if (cc.getAuthentication() != null) {
            try {
                headers.setLocation(
                        new URI("/dashboard"));
            } catch (URISyntaxException e) {
                logger.warn("Dispatcher cannot redirect");
            }
        }

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}