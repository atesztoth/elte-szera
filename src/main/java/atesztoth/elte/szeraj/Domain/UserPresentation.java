package atesztoth.elte.szeraj.Domain;

import atesztoth.elte.szeraj.data.User;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

public class UserPresentation {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getType() {
        return type;
    }

    public void setType(Role type) {
        this.type = type;
    }

    public User getManagedUser() {
        return managedUser;
    }

    public void setManagedUser(User managedUser) {
        this.managedUser = managedUser;
    }
}