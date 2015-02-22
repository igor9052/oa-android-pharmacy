package android.oa.com.ua.pharmacy.activity;

import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.ItemAdapter;
import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorageFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends ActionBarActivity {

    public static final String ACTION_SHOW_ITEMS_LIST = "android.oa.com.ua.pharmacy.activity.ItemListActivity.SHOW_ITEMS_LIST";
    public static final String EXTRA_ITEMS_LIST = "ITEMS_LIST";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    private static final int ADD_NEW_ITEM_RESULT_CODE = 27349;

    List<IMedicineItem> items;
    ListView listView;
    private IMedicineStorage storage = MedicineStorageFactory.makeStorage(MedicineStorageFactory.TEST_MEDICINE_STORAGE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        TextView categoryName = (TextView) findViewById(R.id.category_name);
        if (ACTION_SHOW_ITEMS_LIST.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_ITEMS_LIST)) {
                items = storage.getCategories().get(getIntent().getIntExtra(EXTRA_ITEMS_LIST, 0)).getItems();
            }
            if (getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
                categoryName.setText(getIntent().getStringExtra(EXTRA_CATEGORY_NAME));
            }
        }
        else {
            items = storage.getCategories().get(0).getItems();
        }
        ItemAdapter adapter = new ItemAdapter(this, items);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.add_new_item_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewItemActivity.ACTION_ADD_NEW_ITEM);
                intent.putStringArrayListExtra(AddNewItemActivity.EXTRA_CATEGORY_NAMES, (ArrayList<String>) storage.getCategoryNames());
                startActivityForResult(intent, ADD_NEW_ITEM_RESULT_CODE);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
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
