package android.oa.com.ua.pharmacy.db.dao;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 19.03.2015.
 */
public interface DAO<K, T> {
    void insert(T entity);

    T select(K id);

    List<T> selectAll();

    void delete(K id);

    void update(T entity);
}
