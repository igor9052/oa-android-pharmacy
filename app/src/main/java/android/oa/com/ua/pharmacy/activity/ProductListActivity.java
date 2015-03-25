package android.oa.com.ua.pharmacy.activity;


import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.ProductAdapter;
import android.oa.com.ua.pharmacy.db.dao.impl.MedicineProductDAOImpl;
import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.oa.com.ua.pharmacy.entity.impl.MedicineProduct;
import android.oa.com.ua.pharmacy.entity.impl.ShoppingCart;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProductListActivity extends Activity implements Observer {

    public static final String TAG = ProductListActivity.class.getSimpleName();

    public static final String ACTION_SHOW_PRODUCT_LIST = "android.oa.com.ua.pharmacy.activity.ProductListActivity.SHOW_PRODUCT_LIST";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String EXTRA_NEW_PRODUCT = "NEW_PRODUCT";
    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    public static final int ADD_NEW_PRODUCT_REQUEST_CODE = 27349;

    private List<IMedicineProduct> items;
    private ProductAdapter adapter;
    private String categoryName = "";
    private int categoryId;
    private ImageView cart;
    private TextView cartTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_product_list);
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        cart = (ImageView) toolbar.findViewById(R.id.image_view_cart);
        cartTotalAmount = (TextView) findViewById(R.id.text_cart_total);
        cartTotalAmount.setText(String.valueOf(ShoppingCart.getInstance().getTotalAmountOfProducts()));

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

        showAddButton(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductListActivity.this, "NavigationOnClickListener", Toast.LENGTH_SHORT).show();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShoppingCartActivity();
            }
        });

        cartTotalAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductListActivity.this, "Cart has " + cartTotalAmount.getText() + " products!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openShoppingCartActivity() {
        startActivity(new Intent(this, ShoppingCartActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "ON_START");
        ShoppingCart.getInstance().addObserver(this);
    }

    //if
    private void showAddButton(boolean isAddButtonVisible) {
        if (isAddButtonVisible) {
            Button button = (Button) findViewById(R.id.add_new_item_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AddNewProductActivity.ACTION_ADD_NEW_PRODUCT);
                    intent.putExtra(AddNewProductActivity.EXTRA_CATEGORY_NAME, categoryName);
                    startActivityForResult(intent, ADD_NEW_PRODUCT_REQUEST_CODE);
                }
            });

        } else {
            ((ViewGroup) findViewById(R.id.add_new_item_button).getParent()).removeView(findViewById(R.id.add_new_item_button));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "ON_STOP");
        ShoppingCart.getInstance().deleteObserver(this);
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

    @Override
    public void update(Observable observable, Object data) {
        cartTotalAmount.setText(String.valueOf(ShoppingCart.getInstance().getTotalAmountOfProducts()));
    }
}
