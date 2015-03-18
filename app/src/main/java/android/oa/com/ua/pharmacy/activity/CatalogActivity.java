package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.ImageTextAdapterMenu;
import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorageFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class CatalogActivity extends Activity {

    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_grid);

        IMedicineStorage catalog = MedicineStorageFactory.makeStorage(getApplication(), MedicineStorageFactory.LOCAL_JSON_DATA);
        Log.i("CATALOG_ACTIVITY", catalog.toString());
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageTextAdapterMenu(this, catalog.getCategories()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // send product list of chosen category and category name to ProductListActivity
                IMedicineCategory category = (IMedicineCategory) gridView.getItemAtPosition(position);
                Intent intent = new Intent(ProductListActivity.ACTION_SHOW_PRODUCT_LIST);
                intent.putParcelableArrayListExtra(ProductListActivity.EXTRA_PRODUCT_LIST,
                        (java.util.ArrayList<? extends android.os.Parcelable>) category.getItems());
                intent.putExtra(ProductListActivity.EXTRA_CATEGORY_NAME, category.getName());
                startActivity(intent);
            }
        });
    }

}

