package dao;

public interface DAO<T> {
    T add(T obj);

    boolean remove(T obj);

    T get(long id);

    void update(long id);
}
