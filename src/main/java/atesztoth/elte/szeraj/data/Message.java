package atesztoth.elte.szeraj.data;

import atesztoth.elte.szeraj.Domain.MessagePresentation;
import atesztoth.elte.szeraj.Domain.MessageType;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message implements Serializable {

    public static Message createFromPresentation(MessagePresentation presentation) {
        Message message = new Message();
        message.setId(presentation.getId());
        message.setMessage(presentation.getMessage());
        message.setSent(presentation.getSent());
        message.setAttachedPhoneNumber(presentation.getAttachedPhoneNumber());
        message.setDelivered(presentation.getDelivered());
        message.setGuest(presentation.getGuest());
        message.setFriend(presentation.getFriend());
        message.setMessageType(presentation.getMessageType());

        return message;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String message;

    private Date sent;

    @Nullable
    private String attachedPhoneNumber;

    @Nullable
    private Date delivered;

    @ManyToOne(cascade = CascadeType.ALL)
    private User guest;

    @ManyToOne(cascade = CascadeType.ALL)
    private Friend friend;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

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

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}