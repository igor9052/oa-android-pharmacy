package android.oa.com.ua.pharmacy.activity;


import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.ProductAdapter;
import android.oa.com.ua.pharmacy.db.dao.impl.MedicineProductDAOImpl;
import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.oa.com.ua.pharmacy.entity.impl.MedicineProduct;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends Activity {

    public static final String ACTION_SHOW_PRODUCT_LIST = "android.oa.com.ua.pharmacy.activity.ProductListActivity.SHOW_PRODUCT_LIST";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String EXTRA_NEW_PRODUCT = "NEW_PRODUCT";
    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    public static final int ADD_NEW_PRODUCT_REQUEST_CODE = 27349;

    private List<IMedicineProduct> items;
    private ProductAdapter adapter;
    private String categoryName = "";
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_product_list);
        toolbar.setNavigationIcon(R.drawable.ic_launcher);

        if (ACTION_SHOW_PRODUCT_LIST.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_CATEGORY_ID)) {
                categoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID, -1);
                MedicineProductDAOImpl productDAO = new MedicineProductDAOImpl(getApplicationContext());
                items = new ArrayList<>();
                for (MedicineProduct product : productDAO.selectByCategoryId(categoryId)) {
                    items.add(product);
                }
            }
            if (getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
                categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
            }
        }
        toolbar.setTitle(R.string.title_categories);
        toolbar.setSubtitle(categoryName);
        adapter = new ProductAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        ((ViewGroup) findViewById(R.id.add_new_item_button).getParent()).removeView(findViewById(R.id.add_new_item_button));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductListActivity.this, "NavigationOnClickListener", Toast.LENGTH_SHORT).show();
            }
        });

//        Button button = (Button) findViewById(R.id.add_new_item_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AddNewProductActivity.ACTION_ADD_NEW_PRODUCT);
//                intent.putExtra(AddNewProductActivity.EXTRA_CATEGORY_NAME, categoryName);
//                startActivityForResult(intent, ADD_NEW_PRODUCT_REQUEST_CODE);
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case ADD_NEW_PRODUCT_REQUEST_CODE:
                    IMedicineProduct newItem = data.getParcelableExtra(EXTRA_NEW_PRODUCT);
                    MedicineProductDAOImpl productDAO = new MedicineProductDAOImpl(getApplicationContext());
                    newItem.setId(productDAO.getMaxId() + 1);
                    newItem.setCategoryId(categoryId);
                    productDAO.insert((MedicineProduct) newItem);
                    items.add(newItem);
                    adapter.notifyDataSetChanged();
            }
        }
    }


}
