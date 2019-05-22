package atesztoth.elte.szeraj.controller;

import atesztoth.elte.szeraj.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

}