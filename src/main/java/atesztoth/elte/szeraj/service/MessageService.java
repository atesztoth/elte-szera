package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.data.User;
import atesztoth.elte.szeraj.presentation.MessagePresentation;

import java.util.List;

public interface MessageService extends ContentService<MessagePresentation> {

    MessagePresentation getMessageById(int id);

    List<MessagePresentation> getAllForUser(User user);

}
