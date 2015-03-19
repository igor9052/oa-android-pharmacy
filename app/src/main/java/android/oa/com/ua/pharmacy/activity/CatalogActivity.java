package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.ImageTextAdapterMenu;
import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.impl.MedicineCategory;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorageFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class CatalogActivity extends Activity {

    private static final String TAG = CatalogActivity.class.getSimpleName();
    private static final String BUNDLE_CATALOG_KEY = "Catalog";
    private GridView gridView;
    private MedicineStorage catalog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_grid);

        if (catalog == null) {
            if (savedInstanceState != null) {
                catalog = savedInstanceState.getParcelable(BUNDLE_CATALOG_KEY);
            } else {
                catalog = (MedicineStorage) MedicineStorageFactory.makeStorage(getApplication(), MedicineStorageFactory.LOCAL_JSON_DATA);
            }
        }
        Log.i(TAG, catalog.toString());

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageTextAdapterMenu(this, catalog.getCategories()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // send product list of chosen category and category name to ProductListActivity
                IMedicineCategory category = (IMedicineCategory) gridView.getItemAtPosition(position);
                Intent intent = new Intent(ProductListActivity.ACTION_SHOW_PRODUCT_LIST);
                intent.putParcelableArrayListExtra(ProductListActivity.EXTRA_PRODUCT_LIST,
                        (java.util.ArrayList<? extends android.os.Parcelable>) ((MedicineCategory) category).getItems());
                intent.putExtra(ProductListActivity.EXTRA_CATEGORY_NAME, category.getName());
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_CATALOG_KEY, catalog);
    }
}

