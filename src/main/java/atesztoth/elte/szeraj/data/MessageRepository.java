package atesztoth.elte.szeraj.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByFriend(Friend friend);
    List<Message> findAllByGuest(User user);
    Message removeById(int messageId);
    Message findOneById(int messageId);
}
