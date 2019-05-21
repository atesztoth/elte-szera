package atesztoth.elte.szeraj.data;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;

    private Date sent;

    @Nullable
    private String attachedPhoneNumber;

    @Nullable
    private Date delivered;

    private User guest;

    private Friend friend;

    // GET SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    @Nullable
    public String getAttachedPhoneNumber() {
        return attachedPhoneNumber;
    }

    public void setAttachedPhoneNumber(@Nullable String attachedPhoneNumber) {
        this.attachedPhoneNumber = attachedPhoneNumber;
    }

    @Nullable
    public Date getDelivered() {
        return delivered;
    }

    public void setDelivered(@Nullable Date delivered) {
        this.delivered = delivered;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}