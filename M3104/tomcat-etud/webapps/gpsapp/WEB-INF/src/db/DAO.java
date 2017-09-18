public interface DAO<T> {
    public int insert(T object);
    public boolean update(T object);
    public boolean delete(T object);
    public T find(int id);
}
