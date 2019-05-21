package atesztoth.elte.szeraj.controller;

import atesztoth.elte.szeraj.Domain.MessagePresentation;
import atesztoth.elte.szeraj.service.AuthorizationService;
import atesztoth.elte.szeraj.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("guest")
public class GuestController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AuthorizationService authService;

    @GetMapping("/messages")
    ResponseEntity<List<MessagePresentation>> getMessages() {
        return new ResponseEntity<>(messageService.getAllForUser(authService.getPrincipal().getUser()), HttpStatus.OK);
    }

}
