package atesztoth.elte.szeraj.presentation;

import atesztoth.elte.szeraj.data.User;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

public class UserPresentation {

    public static UserPresentation createFromEntity(User user) {
        UserPresentation presentation = new UserPresentation();
        presentation
                .setUsername(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setType(user.getType())
                .setManagedUser(user);
        return presentation;
    }

    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "firstname", nullable = true)
    private String firstName;

    @Column(name = "lastname", nullable = true)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role type;

    private User managedUser;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getType() {
        return type;
    }

    public User getManagedUser() {
        return managedUser;
    }

    public String getId() {
        return getUsername();
    }

    public UserPresentation setId(String username) {
        return setUsername(username);
    }

    public UserPresentation setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserPresentation setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserPresentation setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserPresentation setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserPresentation setType(Role type) {
        this.type = type;
        return this;
    }

    public UserPresentation setManagedUser(User managedUser) {
        this.managedUser = managedUser;
        return this;
    }
}