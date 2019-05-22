package atesztoth.elte.szeraj.presentation;

import java.util.Date;

public class MessageAnswerType {

    public static MessageAnswerType createFromPresentation(MessagePresentation presentation) {
        return new MessageAnswerType()
                .setId(presentation.getId())
                .setMessage(presentation.getMessage())
                .setSent(presentation.getSent())
                .setAttachedPhoneNumber(presentation.getAttachedPhoneNumber())
                .setDelivered(presentation.getDelivered())
                .setMessageType(presentation.getMessageType());
    }

    private int id;
    private String message;
    private Date sent;
    private String attachedPhoneNumber;
    private Date delivered;
    private MessageType messageType;

    // GET SET


    public int getId() {
        return id;
    }

    public MessageAnswerType setId(int id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageAnswerType setMessage(String message) {
        this.message = message;
        return this;
    }

    public Date getSent() {
        return sent;
    }

    public MessageAnswerType setSent(Date sent) {
        this.sent = sent;
        return this;
    }

    public String getAttachedPhoneNumber() {
        return attachedPhoneNumber;
    }

    public MessageAnswerType setAttachedPhoneNumber(String attachedPhoneNumber) {
        this.attachedPhoneNumber = attachedPhoneNumber;
        return this;
    }

    public Date getDelivered() {
        return delivered;
    }

    public MessageAnswerType setDelivered(Date delivered) {
        this.delivered = delivered;
        return this;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public MessageAnswerType setMessageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }
}
