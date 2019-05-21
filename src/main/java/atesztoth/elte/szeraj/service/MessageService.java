package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.Domain.MessagePresentation;
import atesztoth.elte.szeraj.data.User;

import java.util.List;

public interface MessageService {

    MessagePresentation getMessageById(int id);

    MessagePresentation createMessage(MessagePresentation messagePresentation);

    MessagePresentation removeMessage(MessagePresentation messagePresentation);

    List<MessagePresentation> getAllForUser(User user);

}
