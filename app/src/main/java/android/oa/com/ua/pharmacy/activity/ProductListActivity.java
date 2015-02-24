package android.oa.com.ua.pharmacy.activity;


import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.ProductAdapter;
import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ProductListActivity extends Activity {

    public static final String ACTION_SHOW_PRODUCT_LIST = "android.oa.com.ua.pharmacy.activity.ProductListActivity.SHOW_PRODUCT_LIST";
    public static final String EXTRA_PRODUCT_LIST = "PRODUCT_LIST";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String EXTRA_NEW_PRODUCT = "NEW_PRODUCT";
    public static final int ADD_NEW_PRODUCT_REQUEST_CODE = 27349;

    List<IMedicineProduct> items;
    ListView listView;
    ProductAdapter adapter;
    String categoryName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        if (ACTION_SHOW_PRODUCT_LIST.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_PRODUCT_LIST)) {
                items = getIntent().getParcelableArrayListExtra(EXTRA_PRODUCT_LIST);
            }
            if (getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
                categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
                ((TextView) findViewById(R.id.category_name)).setText(categoryName);
            }
        }
        adapter = new ProductAdapter(this, items);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.add_new_item_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewProductActivity.ACTION_ADD_NEW_PRODUCT);
                intent.putExtra(AddNewProductActivity.EXTRA_CATEGORY_NAME, categoryName);
                startActivityForResult(intent, ADD_NEW_PRODUCT_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case ADD_NEW_PRODUCT_REQUEST_CODE:
                    IMedicineProduct newItem = data.getParcelableExtra(EXTRA_NEW_PRODUCT);
                    items.add(newItem);
                    adapter.notifyDataSetChanged();
            }
        }
    }
}
