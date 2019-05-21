package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.Domain.MessagePresentation;
import atesztoth.elte.szeraj.data.Message;
import atesztoth.elte.szeraj.data.MessageRepository;
import atesztoth.elte.szeraj.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SzerajMessageService implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessagePresentation getMessageById(int id) {
        return MessagePresentation.createFromEntity(messageRepository.findOneById(id));
    }

    @Override
    public MessagePresentation createMessage(MessagePresentation messagePresentation) {
        Message message = Message.createFromPresentation(messagePresentation);
        messageRepository.save(message);
        messagePresentation.setManagedMessage(message);
        return messagePresentation;
    }

    @Override
    public MessagePresentation removeMessage(MessagePresentation messagePresentation) {
        return MessagePresentation
                .createFromEntity(messageRepository.removeById(messagePresentation.getId())).dropManaged();
    }

    @Override
    public List<MessagePresentation> getAllForUser(User user) {
        ArrayList<Message> messages = new ArrayList<>(messageRepository.findAllByGuest(user));
        return messages.stream().map(MessagePresentation::createFromEntity).collect(Collectors.toList());
    }


}
