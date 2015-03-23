package android.oa.com.ua.pharmacy.db.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.oa.com.ua.pharmacy.db.PharmacyDbContract;
import android.oa.com.ua.pharmacy.db.dao.AbstractDAO;
import android.oa.com.ua.pharmacy.db.dao.IMedicineProductDAO;
import android.oa.com.ua.pharmacy.entity.impl.MedicineProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Kuzmenko on 19.03.2015.
 */
public class MedicineProductDAOImpl extends AbstractDAO implements IMedicineProductDAO<MedicineProduct> {

    private static final String TABLE_NAME = PharmacyDbContract.Product.TABLE_NAME;

    public MedicineProductDAOImpl(Context context) {
        super(context);
    }

    @Override
    public void insert(MedicineProduct entity) {
        db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PharmacyDbContract.Product._ID, entity.getId());
        values.put(PharmacyDbContract.Product.COLUMN_NAME_NAME, entity.getName());
        values.put(PharmacyDbContract.Product.COLUMN_NAME_DESCRIPTION, entity.getDescription());
        values.put(PharmacyDbContract.Product.COLUMN_NAME_IMAGE_URL, entity.getImageUrl());
        values.put(PharmacyDbContract.Product.COLUMN_NAME_CATEGORY_ID, entity.getCategoryId());

        db.insert(
                TABLE_NAME,
                null,
                values
        );
    }

    @Override
    public MedicineProduct select(Integer id) {
        return null;
    }

    @Override
    public List<MedicineProduct> selectAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(MedicineProduct entity) {

    }

    @Override
    public List<MedicineProduct> selectByCategoryId(Integer id) {
        db = mDbHelper.getReadableDatabase();
        String[] projection = {
                PharmacyDbContract.Product._ID,
                PharmacyDbContract.Product.COLUMN_NAME_NAME,
                PharmacyDbContract.Product.COLUMN_NAME_DESCRIPTION,
                PharmacyDbContract.Product.COLUMN_NAME_IMAGE_URL,
                PharmacyDbContract.Product.COLUMN_NAME_CATEGORY_ID
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection, //we can use null here if we want to select all columns
                PharmacyDbContract.Product.COLUMN_NAME_CATEGORY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        List<MedicineProduct> productList = new ArrayList<>();
        //CategoryDAO categoryDAO = new CategoryDAOImpl(context);
        //String categoryName = categoryDAO.select(id).getName();

        do {
            MedicineProduct medicineProduct = new MedicineProduct();
            medicineProduct.setId(cursor.getInt(cursor.getColumnIndexOrThrow(PharmacyDbContract.Product._ID)));
            medicineProduct.setName(cursor.getString(cursor.getColumnIndexOrThrow(PharmacyDbContract.Product.COLUMN_NAME_NAME)));
            medicineProduct.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(PharmacyDbContract.Product.COLUMN_NAME_DESCRIPTION)));
            medicineProduct.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(PharmacyDbContract.Product.COLUMN_NAME_IMAGE_URL)));
            medicineProduct.setCategoryId(id);
            medicineProduct.setCategoryName("test");
            productList.add(medicineProduct);

        } while (cursor.moveToNext());

        cursor.close();

        return productList;
    }

    @Override
    public void insertList(List<MedicineProduct> productList) {
        for (MedicineProduct item : productList) {
            this.insert(item);
        }
    }

    @Override
    public Integer getMaxId() {
        return super.getMaxId(TABLE_NAME);
    }
}
