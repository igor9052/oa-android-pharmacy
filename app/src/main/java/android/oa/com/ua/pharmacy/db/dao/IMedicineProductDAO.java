package android.oa.com.ua.pharmacy.db.dao;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 19.03.2015.
 */
public interface IMedicineProductDAO<T> extends DAO<Integer, T> {

    List<T> selectByCategoryId(Integer id);

    void insertList(List<T> productList);

    Integer getMaxId();
}
