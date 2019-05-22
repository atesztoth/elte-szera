package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.data.Friend;
import atesztoth.elte.szeraj.data.FriendRepository;
import atesztoth.elte.szeraj.presentation.FriendPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SzerajFriendService implements FriendService {

    @Autowired
    FriendRepository friendRepository;

    @Override
    public FriendPresentation create(FriendPresentation presentation) {
        Friend friend = Friend.createFromPresentation(presentation);
        friendRepository.save(friend);
        presentation.setManagedEntity(friend);
        return presentation;
    }

    public FriendPresentation create(Friend entity) {
        FriendPresentation presentation = FriendPresentation.createFromEntity(entity);
        friendRepository.save(entity);
        presentation.setManagedEntity(entity);
        presentation.setId(entity.getId());
        return presentation;
    }

    @Override
    public FriendPresentation remove(FriendPresentation presentation) {
        return FriendPresentation
                .createFromEntity(friendRepository.removeById(presentation.getId())).dropManaged();
    }

    @Override
    public FriendPresentation findById(int id) {
        Optional<Friend> friendOptional = friendRepository.findById(id);
        return friendOptional.map(FriendPresentation::createFromEntity).orElse(null);
    }
}
