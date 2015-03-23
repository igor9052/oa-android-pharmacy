package android.oa.com.ua.pharmacy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Igor Kuzmenko on 19.03.2015.
 * Schema of Pharmacy database.
 */
public final class PharmacyDbContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String INTEGER_PRIMARY_KEY_TYPE = " INTEGER PRIMARY KEY";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_PRODUCT_TABLE =
            "CREATE TABLE " + Product.TABLE_NAME + " (" +
                    Product._ID + INTEGER_PRIMARY_KEY_TYPE + COMMA_SEP +
                    Product.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    Product.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    Product.COLUMN_NAME_IMAGE_URL + TEXT_TYPE + COMMA_SEP +
                    Product.COLUMN_NAME_CATEGORY_ID + INTEGER_TYPE + ")";
    private static final String SQL_CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + Category.TABLE_NAME + " (" +
                    Category._ID + INTEGER_PRIMARY_KEY_TYPE + COMMA_SEP +
                    Category.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    Category.COLUMN_NAME_IMAGE_URL + TEXT_TYPE + ")";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Category.TABLE_NAME + ";" +
                    "DROP TABLE IF EXISTS " + Product.TABLE_NAME;

    private PharmacyDbContract() {
    }

    public static abstract class Category implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";
    }

    public static abstract class Product implements BaseColumns {

        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
    }

    public static class PharmacyDbHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "pharmacy.db";
        private Context context;

        public PharmacyDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_CATEGORY_TABLE);
            db.execSQL(SQL_CREATE_PRODUCT_TABLE);
            Log.i("DB_HELPER", "ON_CREATE");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public String getSelectMaxIdRequestString(String tableName) {
            return "select MAX(" + tableName + "." + BaseColumns._ID + ") from " + tableName;
        }
    }


}
