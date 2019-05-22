package atesztoth.elte.szeraj.service;

import atesztoth.elte.szeraj.presentation.FriendPresentation;

public interface FriendService extends ContentService<FriendPresentation> {
    FriendPresentation findById(int id);
}
