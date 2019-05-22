package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.presentation.MessagePresentation;
import atesztoth.elte.szeraj.data.Message;
import atesztoth.elte.szeraj.data.MessageRepository;
import atesztoth.elte.szeraj.data.User;
import atesztoth.elte.szeraj.presentation.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SzerajMessageService implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessagePresentation getMessageById(int id) {
        return MessagePresentation.createFromEntity(messageRepository.findOneById(id));
    }

    @Override
    public List<MessagePresentation> getAllForUser(User user) {
        ArrayList<Message> messages = new ArrayList<>(messageRepository.findAllByGuest(user));
        return messages.stream().map(MessagePresentation::createFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<MessagePresentation> getAllForFriendByGuest(int friendId, String guestId) {
        return messageRepository.findAllByGuestUsernameAndFriendIdAndMessageType(guestId, friendId, MessageType.OUTGOING)
                .stream().map(MessagePresentation::createFromEntity).collect(Collectors.toList());
    }

    public MessagePresentation setReadIfNot(MessagePresentation presentation) {
        return presentation.getManagedMessage().getDelivered() == null
                ? setRead(presentation) : presentation;
    }

    @Override
    public MessagePresentation setRead(MessagePresentation unRead) {
        Message entity = unRead.getManagedMessage();
        entity.setDelivered(new Date());
        unRead.setDelivered(messageRepository.save(entity).getDelivered());
        return unRead;
    }

    @Override
    public MessagePresentation removeById(int id) {
        MessagePresentation presentation = MessagePresentation.createFromEntity(messageRepository.findOneById(id));
        return remove(presentation);
    }

    public MessagePresentation create(Message message) {
        return MessagePresentation.createFromEntity(messageRepository.save(message));
    }

    // Content Service conformation
    @Override
    public MessagePresentation create(MessagePresentation presentation) {
        Message message = Message.createFromPresentation(presentation);
        messageRepository.save(message);
        presentation.setManagedMessage(message);
        return presentation;
    }

    @Override
    public MessagePresentation remove(MessagePresentation presentation) {
        Message managedMessage = presentation.getManagedMessage();
        if (managedMessage == null) return presentation;
        messageRepository.delete(managedMessage);
        return presentation.dropManaged();
    }
}
