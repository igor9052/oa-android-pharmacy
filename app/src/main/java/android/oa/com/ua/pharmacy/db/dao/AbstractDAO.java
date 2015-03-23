package android.oa.com.ua.pharmacy.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.oa.com.ua.pharmacy.db.PharmacyDbContract;
import android.os.AsyncTask;

/**
 * Created by Igor Kuzmenko on 19.03.2015.
 */
public abstract class AbstractDAO {

    public static final String READ_DB = "READ";
    public static final String WRITE_DB = "WRITE";

    protected PharmacyDbContract.PharmacyDbHelper mDbHelper;
    protected SQLiteDatabase db;
    protected Context context;

    protected AbstractDAO(Context context) {
        this.context = context;
        mDbHelper = new PharmacyDbContract.PharmacyDbHelper(context);
    }

    protected Integer getMaxId(String tableName) {
        db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                mDbHelper.getSelectMaxIdRequestString(tableName),
                null
        );
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        db.close();
        return result;
    }

    protected void getWriteDB() {
        new GetDBAsyncTask().execute(WRITE_DB);
    }

    protected void getReadDB() {
        new GetDBAsyncTask().execute(READ_DB);
    }

    private class GetDBAsyncTask extends AsyncTask<String, Void, SQLiteDatabase> {

        @Override
        protected SQLiteDatabase doInBackground(String... params) {
            switch (params[0]) {
                case READ_DB:
                    return mDbHelper.getReadableDatabase();
                case WRITE_DB:
                    return mDbHelper.getWritableDatabase();
            }
            return null;
        }

        @Override
        protected void onPostExecute(SQLiteDatabase sqLiteDatabase) {
            super.onPostExecute(sqLiteDatabase);
        }
    }
}
