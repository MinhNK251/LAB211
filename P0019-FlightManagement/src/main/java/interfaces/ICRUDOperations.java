package interfaces;

import java.util.List;

public interface ICRUDOperations <T>{
    public default Boolean addNew (T newElement) {
        return false;
    }
    public default Boolean deleting (T removeElement) {
        return false;
    }
    public default Boolean updating (T updateElement) {
        return false;
    }
    public default Boolean showAll () {
        return false;
    }
    public default T get () {
        return null;
    }
    public default List<T> getAll () {
        return null;
    }
}