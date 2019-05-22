package atesztoth.elte.szeraj.controller;

import atesztoth.elte.szeraj.data.Friend;
import atesztoth.elte.szeraj.data.User;
import atesztoth.elte.szeraj.presentation.MessageAnswerType;
import atesztoth.elte.szeraj.presentation.MessagePresentation;
import atesztoth.elte.szeraj.presentation.MessageType;
import atesztoth.elte.szeraj.service.*;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    ResponseEntity<List<MessageAnswerType>> getMessages() {
        return new ResponseEntity<>(
                messageService.getAllForUser(authService.getPrincipal().getUser())
                        .stream().map(MessageAnswerType::createFromPresentation).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/message/{mid}")
    ResponseEntity<MessageAnswerType> readMessage(@PathVariable("mid") int messageId) {
        MessagePresentation presentation = messageService.getMessageById(messageId);
        ((SzerajMessageService) messageService).setReadIfNot(presentation);
        return new ResponseEntity<>(MessageAnswerType.createFromPresentation(presentation), HttpStatus.OK);
    }

    @DeleteMapping("/message/{mid}")
    ResponseEntity<MessageAnswerType> deleteMessage(@PathVariable("mid") int messageId) {
        MessagePresentation presentation = messageService.removeById(messageId);
        return new ResponseEntity<>(MessageAnswerType.createFromPresentation(presentation), HttpStatus.OK);
    }

    @PostMapping("/message")
    ResponseEntity<MessagePresentation> createMessage(@RequestBody MessagePresentation presentation) {
        Optional<User> me = userService.getById(authService.getPrincipal().getUser().getId());
        Friend friend = friendService.findById(presentation.getFriend().getId()).getManagedEntity();
        presentation.setGuest(me.get()); // like force unwrapping in swift ... Living dangerously wow 😂
        presentation.setFriend(friend);
        presentation.setMessageType(MessageType.OUTGOING);
        presentation.setSent(new Date());
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
