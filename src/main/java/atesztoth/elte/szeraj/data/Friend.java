package atesztoth.elte.szeraj.data;

import atesztoth.elte.szeraj.presentation.FriendPresentation;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friends")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Friend createFromPresentation(FriendPresentation friendPresentation) {
        Friend friend = new Friend();
        friend.setId(friendPresentation.getId());
        friend.setName(friendPresentation.getName());
        return friend;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = false)
    private String name;

    // GET SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}