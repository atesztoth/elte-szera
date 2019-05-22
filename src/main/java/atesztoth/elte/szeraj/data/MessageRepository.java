package atesztoth.elte.szeraj.data;

import atesztoth.elte.szeraj.presentation.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByGuestUsernameAndFriendIdAndMessageType(String guestId, int friendId, MessageType messageType);
    List<Message> findAllByGuest(User user);
    Message findOneById(int messageId);
}
