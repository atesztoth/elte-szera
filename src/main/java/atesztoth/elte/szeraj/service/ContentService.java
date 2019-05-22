package atesztoth.elte.szeraj.service;

// Yeah yeah I know every operation is the same, why not create an interface with default implementations,
// so any service would just look like: public class xyService implements CrudService<EntityType, PresentationType>
// or something like that and no code would be needed to be written but I am no expert like that in Java :(
// also I have -1 time left.

public interface ContentService<T> {
    T create(T presentation);
    T remove(T presentation);
}
