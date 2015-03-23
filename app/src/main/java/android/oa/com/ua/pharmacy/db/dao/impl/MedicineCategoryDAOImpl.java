package android.oa.com.ua.pharmacy.db.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.oa.com.ua.pharmacy.db.PharmacyDbContract;
import android.oa.com.ua.pharmacy.db.dao.AbstractDAO;
import android.oa.com.ua.pharmacy.db.dao.IMedicineCategoryDAO;
import android.oa.com.ua.pharmacy.db.dao.IMedicineProductDAO;
import android.oa.com.ua.pharmacy.entity.impl.MedicineCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Kuzmenko on 19.03.2015.
 */
public class MedicineCategoryDAOImpl extends AbstractDAO implements IMedicineCategoryDAO<MedicineCategory> {

    public MedicineCategoryDAOImpl(Context context) {
        super(context);
    }

    @Override
    public void insert(MedicineCategory entity) {
        db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PharmacyDbContract.Category._ID, entity.getId());
        values.put(PharmacyDbContract.Category.COLUMN_NAME_NAME, entity.getName());
        values.put(PharmacyDbContract.Category.COLUMN_NAME_IMAGE_URL, entity.getImageUrl());

        db.insert(PharmacyDbContract.Category.TABLE_NAME,
                null,
                values);
    }

    @Override
    public MedicineCategory select(Integer id) {
        db = mDbHelper.getReadableDatabase();
        String[] projection = {
                PharmacyDbContract.Category._ID,
                PharmacyDbContract.Category.COLUMN_NAME_NAME,
                PharmacyDbContract.Category.COLUMN_NAME_IMAGE_URL
        };

        Cursor cursor = db.query(
                PharmacyDbContract.Category.TABLE_NAME,
                projection,
                PharmacyDbContract.Category._ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        MedicineCategory medicineCategory = new MedicineCategory();
        medicineCategory.setId(cursor.getInt(cursor.getColumnIndexOrThrow(PharmacyDbContract.Category._ID)));
        medicineCategory.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(PharmacyDbContract.Category.COLUMN_NAME_IMAGE_URL)));
        medicineCategory.setName(cursor.getString(cursor.getColumnIndexOrThrow(PharmacyDbContract.Category.COLUMN_NAME_NAME)));

        IMedicineProductDAO productDAO = new MedicineProductDAOImpl(context);
        medicineCategory.setItems(productDAO.selectByCategoryId(medicineCategory.getId()));
        cursor.close();
        return medicineCategory;
    }

    @Override
    public List<MedicineCategory> selectAll() {
        db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                PharmacyDbContract.Category.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<MedicineCategory> medicineCategoryList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MedicineCategory medicineCategory = new MedicineCategory();
                medicineCategory.setId(cursor.getInt(cursor.getColumnIndexOrThrow(PharmacyDbContract.Category._ID)));
                medicineCategory.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(PharmacyDbContract.Category.COLUMN_NAME_IMAGE_URL)));
                medicineCategory.setName(cursor.getString(cursor.getColumnIndexOrThrow(PharmacyDbContract.Category.COLUMN_NAME_NAME)));
                medicineCategoryList.add(medicineCategory);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return medicineCategoryList;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(MedicineCategory entity) {

    }
}
