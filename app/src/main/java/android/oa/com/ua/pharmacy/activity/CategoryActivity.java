package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.CategoryAdapter;
import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorageFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.List;


public class CategoryActivity extends Activity {

    private IMedicineStorage storage = MedicineStorageFactory.makeStorage(MedicineStorageFactory.TEST_MEDICINE_STORAGE);
    private List<IMedicineCategory> categoryList = storage.getCategories();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        final GridView gridView = (GridView) findViewById(R.id.category_gridview);
        ArrayAdapter<IMedicineCategory> adapter = new CategoryAdapter(this, categoryList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IMedicineCategory category = (IMedicineCategory) gridView.getItemAtPosition(position);
                Intent intent = new Intent(ItemListActivity.ACTION_SHOW_ITEMS_LIST);
                intent.putParcelableArrayListExtra(ItemListActivity.EXTRA_ITEMS_LIST,
                        (java.util.ArrayList<? extends android.os.Parcelable>) category.getItems());
                intent.putExtra(ItemListActivity.EXTRA_CATEGORY_NAME, category.getName());
                startActivity(intent);
            }
        });


    }

}
