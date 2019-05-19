package atesztoth.elte.szeraj.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "guests")
public class Guest extends Person implements Serializable {

    private String username;

    private String password;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public Guest() { }
}