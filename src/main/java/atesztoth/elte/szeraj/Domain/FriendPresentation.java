package atesztoth.elte.szeraj.Domain;

import atesztoth.elte.szeraj.data.Friend;

public class FriendPresentation {
    public static final FriendPresentation createFromEntity(Friend entity) {
        FriendPresentation presentation = new FriendPresentation();
        presentation.setId(entity.getId());
        presentation.setName(entity.getName());
        presentation.setManagedEntity(entity);
        return presentation;
    }

    private int id;
    private String name;
    private Friend managedEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Friend getManagedEntity() {
        return managedEntity;
    }

    public void setManagedEntity(Friend managedEntity) {
        this.managedEntity = managedEntity;
    }
}
