package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.presentation.MessagePresentation;
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
    public List<MessagePresentation> getAllForUser(User user) {
        ArrayList<Message> messages = new ArrayList<>(messageRepository.findAllByGuest(user));
        return messages.stream().map(MessagePresentation::createFromEntity).collect(Collectors.toList());
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
        return MessagePresentation
                .createFromEntity(messageRepository.removeById(presentation.getId())).dropManaged();
    }
}
