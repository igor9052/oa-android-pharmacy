package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.ImageTextAdapterMenu;
import android.oa.com.ua.pharmacy.db.dao.impl.MedicineCategoryDAOImpl;
import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.impl.MedicineCategory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;


public class CatalogActivity extends Activity {

    private static final String TAG = CatalogActivity.class.getSimpleName();
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_grid);

        List<MedicineCategory> categoryList = new MedicineCategoryDAOImpl(getApplicationContext()).selectAll();

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageTextAdapterMenu(this, categoryList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Send category ID and category name to ProductListActivity
                IMedicineCategory category = (IMedicineCategory) gridView.getItemAtPosition(position);
                Intent intent = new Intent(ProductListActivity.ACTION_SHOW_PRODUCT_LIST);
                intent.putExtra(ProductListActivity.EXTRA_CATEGORY_NAME, category.getName());
                intent.putExtra(ProductListActivity.EXTRA_CATEGORY_ID, category.getId());
                startActivity(intent);
            }
        });

        ImageView settings = (ImageView) findViewById(R.id.image_view_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CatalogActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            }
        });

        Log.i(TAG, "ON_CREATE");
    }
}

