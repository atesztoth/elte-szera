package atesztoth.elte.szeraj.controller;

import atesztoth.elte.szeraj.data.Friend;
import atesztoth.elte.szeraj.data.User;
import atesztoth.elte.szeraj.presentation.FriendPresentation;
import atesztoth.elte.szeraj.presentation.MessagePresentation;
import atesztoth.elte.szeraj.presentation.MessageType;
import atesztoth.elte.szeraj.service.AuthorizationService;
import atesztoth.elte.szeraj.service.FriendService;
import atesztoth.elte.szeraj.service.MessageService;
import atesztoth.elte.szeraj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("guest")
public class GuestController {

    private static final Logger logger = LoggerFactory.getLogger(GuestController.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private AuthorizationService authService;

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @GetMapping("/messages")
    ResponseEntity<List<MessagePresentation>> getMessages() {
        return new ResponseEntity<>(messageService.getAllForUser(authService.getPrincipal().getUser()), HttpStatus.OK);
    }

    @PostMapping("/message")
    ResponseEntity<MessagePresentation> createMessage(@RequestBody MessagePresentation presentation) {
        Optional<User> me = userService.getById(authService.getPrincipal().getUser().getId());
        Friend friend = friendService.findById(presentation.getFriend().getId()).getManagedEntity();
        presentation.setGuest(me.get()); // like force unwrapping in swift ... Living dangerously wow 😂
        presentation.setFriend(friend);
        presentation.setMessageType(MessageType.OUTGOING);
        return new ResponseEntity<>(messageService.create(presentation).dropManaged(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    public ResponseEntity<Void> dispatchUser() {
        logger.warn("DISPATCHING");
        HttpHeaders headers = new HttpHeaders();
        if (authService.getAuth() != null) {
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
