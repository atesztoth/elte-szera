package atesztoth.elte.szeraj.controller;

import atesztoth.elte.szeraj.data.Friend;
import atesztoth.elte.szeraj.data.User;
import atesztoth.elte.szeraj.presentation.MessageAnswerType;
import atesztoth.elte.szeraj.presentation.MessagePresentation;
import atesztoth.elte.szeraj.presentation.MessageType;
import atesztoth.elte.szeraj.service.FriendService;
import atesztoth.elte.szeraj.service.MessageService;
import atesztoth.elte.szeraj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("receptionist")
public class ReceptionistController {

    private static final Logger logger = LoggerFactory.getLogger(ReceptionistController.class);

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @GetMapping("/messages/{gid}/{fid}")
    ResponseEntity<List<MessageAnswerType>> messagesForFriendByGuest(@PathVariable("gid") String guestId, @PathVariable("fid") int friendId) {
        logger.error("guestId: " + guestId);
        logger.error("friendId: " + friendId);
        return new ResponseEntity<>(
                messageService.getAllForFriendByGuest(friendId, guestId)
                        .stream().map(MessageAnswerType::createFromPresentation).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @PostMapping("/messages/{gid}/{fid}")
    ResponseEntity<MessageAnswerType> messageForGuestByFriend(@PathVariable("gid") String guestId,
                                                              @PathVariable("fid") int friendId,
                                                              @RequestBody MessagePresentation messagePresentation) {
        MessagePresentation presentation = new MessagePresentation();
        User guest = userService.getById(guestId).get();
        Friend friend = friendService.findById(friendId).getManagedEntity();
        presentation.setFriend(friend);
        presentation.setGuest(guest);
        presentation.setSent(new Date());
        presentation.setMessageType(MessageType.INCOMING);
        presentation.setMessage(messagePresentation.getMessage());
        presentation.setAttachedPhoneNumber(messagePresentation.getAttachedPhoneNumber());
        messageService.create(presentation);
        return new ResponseEntity<>(MessageAnswerType.createFromPresentation(presentation), HttpStatus.CREATED);
    }

}
