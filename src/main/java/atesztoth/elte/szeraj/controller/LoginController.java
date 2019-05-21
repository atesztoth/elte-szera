package atesztoth.elte.szeraj.controller;

import atesztoth.elte.szeraj.Domain.MessagePresentation;
import atesztoth.elte.szeraj.Domain.UserPresentation;
import atesztoth.elte.szeraj.Domain.Role;
import atesztoth.elte.szeraj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private void createDummyUser() {
        final UserPresentation userPresentation = new UserPresentation();
        userPresentation.setUsername("atesztoth");
        userPresentation.setFirstName("Attila");
        userPresentation.setLastName("Tóth");
        userPresentation.setPassword("aa");
        userPresentation.setType(Role.GUEST);
        userService.createUser(userPresentation);
    }

    private void createMessage() {
        final MessagePresentation presentation = new MessagePresentation();
        presentation.setMessage("New message mannn");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
//        createDummyUser();
        logger.debug("Getting login page, error={}", error);
        return new ModelAndView("login", "error", error);
    }
}
