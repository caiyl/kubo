package kubo.dao;


public interface BaseDao<T> {
	public void add(T obj);
	public void delete(int objId);
	public void update(T obj);
    public T query(int objId);
}
