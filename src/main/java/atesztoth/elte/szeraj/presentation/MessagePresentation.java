package atesztoth.elte.szeraj.presentation;

import atesztoth.elte.szeraj.data.Friend;
import atesztoth.elte.szeraj.data.Message;
import atesztoth.elte.szeraj.data.User;

import java.util.Date;

public class MessagePresentation {

    public static MessagePresentation createFromEntity(Message message) {
        MessagePresentation presentation = new MessagePresentation();
        presentation.setId(message.getId());
        presentation.setMessage(message.getMessage());
        presentation.setSent(message.getSent());
        presentation.setAttachedPhoneNumber(message.getAttachedPhoneNumber());
        presentation.setDelivered(message.getDelivered());
        presentation.setGuest(message.getGuest());
        presentation.setFriend(message.getFriend());
        presentation.setMessageType(message.getMessageType());
        presentation.setManagedMessage(message);
        return presentation;
    }

    private int id;
    private String message;

    private Date sent;
    private String attachedPhoneNumber;
    private Date delivered;
    private User guest;
    private Friend friend;
    private MessageType messageType;

    private Message managedMessage = null;

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

    public String getAttachedPhoneNumber() {
        return attachedPhoneNumber;
    }

    public void setAttachedPhoneNumber(String attachedPhoneNumber) {
        this.attachedPhoneNumber = attachedPhoneNumber;
    }

    public Date getDelivered() {
        return delivered;
    }

    public void setDelivered(Date delivered) {
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

    public Message getManagedMessage() {
        return managedMessage;
    }

    public void setManagedMessage(Message managedMessage) {
        this.managedMessage = managedMessage;
    }

    public MessagePresentation dropManaged() {
        this.managedMessage = null;
        return this;
    }
}
