package atesztoth.elte.szeraj.controller;

import atesztoth.elte.szeraj.presentation.CurrentUser;
import atesztoth.elte.szeraj.data.User;
import atesztoth.elte.szeraj.presentation.FriendPresentation;
import atesztoth.elte.szeraj.presentation.Role;
import atesztoth.elte.szeraj.presentation.UserPresentation;
import atesztoth.elte.szeraj.service.AuthorizationService;
import atesztoth.elte.szeraj.service.FriendService;
import atesztoth.elte.szeraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    public static class MeResponse {
        static MeResponse createFromUser(User user) {
            if (user == null) return new MeResponse();
            return new MeResponse(user.getUsername(), user.getFirstName(), user.getLastName(), user.getType().toString());
        }

        private String username;
        private String firstName;
        private String lastName;
        private String type;

        MeResponse(String username, String firstName, String lastName, String type) {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.type = type;
        }

        MeResponse() {
            this.username = "";
            this.firstName = "";
            this.lastName = "";
            this.type = "";
        }

        public String getUsername() {
            return username;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getType() {
            return type;
        }
    }

    @Autowired
    AuthorizationService authService;

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @GetMapping("/me")
    public ResponseEntity<MeResponse> getMyInfo() {
        CurrentUser currentUser = authService.getPrincipal();
        return new ResponseEntity<>(MeResponse.createFromUser(currentUser != null ? currentUser.getUser() : null), HttpStatus.OK);
    }

    @PostMapping("/create-guest")
    public ResponseEntity<MeResponse> createGuest(@RequestBody UserPresentation presentation) {
        presentation.setType(Role.GUEST);
        return new ResponseEntity<>(
                MeResponse.createFromUser(userService.create(presentation).getManagedUser()), HttpStatus.CREATED
        );
    }

    @PostMapping("/create-receptionist")
    public ResponseEntity<MeResponse> createReceptionist(@RequestBody UserPresentation presentation) {
        presentation.setType(Role.RECEPTIONIST);
        return new ResponseEntity<>(
                MeResponse.createFromUser(userService.create(presentation).getManagedUser()), HttpStatus.CREATED
        );
    }

    @PostMapping("/create-friend")
    public ResponseEntity<FriendPresentation> createReceptionist(@RequestBody FriendPresentation presentation) {
        return new ResponseEntity<>(
                FriendPresentation.createFromEntity(friendService.create(presentation).getManagedEntity()),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/friend")
    ResponseEntity<FriendPresentation> removeFriend(FriendPresentation friendPresentation) {
        return new ResponseEntity<>(friendService.remove(friendPresentation), HttpStatus.OK);
    }

}
