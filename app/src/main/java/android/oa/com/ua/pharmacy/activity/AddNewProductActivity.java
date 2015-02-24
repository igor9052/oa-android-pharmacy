package android.oa.com.ua.pharmacy.activity;

import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.oa.com.ua.pharmacy.entity.impl.MedicineProduct;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddNewProductActivity extends ActionBarActivity {

    public static final String ACTION_ADD_NEW_PRODUCT = "android.oa.com.ua.pharmacy.activity.AddNewProductActivity.ADD_NEW_PRODUCT";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";

    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        if (ACTION_ADD_NEW_PRODUCT.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
                categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
                TextView categoryNameTextView = (TextView) findViewById(R.id.category_name);
                categoryNameTextView.setText(categoryNameTextView.getText() + categoryName);
            }
        }

        Button addButton = (Button) findViewById(R.id.save_new_item_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                EditText itemName = (EditText) findViewById(R.id.item_name_edit_text);
                EditText itemDescription = (EditText) findViewById(R.id.item_description_edit_text);
                IMedicineProduct newItem = new MedicineProduct(100, itemName.getText().toString(),
                        itemDescription.getText().toString(),
                        0,
                        categoryName);
                intent.putExtra(ProductListActivity.EXTRA_NEW_PRODUCT, newItem);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
