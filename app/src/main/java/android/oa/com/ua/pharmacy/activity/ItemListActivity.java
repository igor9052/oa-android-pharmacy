package android.oa.com.ua.pharmacy.activity;

import android.content.Intent;
import android.oa.com.ua.pharmacy.adapter.ItemAdapter;
import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorageFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.oa.com.ua.pharmacy.R;
import android.widget.ListView;

import java.util.List;

public class ItemListActivity extends ActionBarActivity {

    private IMedicineStorage storage = MedicineStorageFactory.makeStorage(MedicineStorageFactory.TEST_MEDICINE_STORAGE);

    public static final String ACTION_SHOW_ITEMS_LIST = "android.oa.com.ua.pharmacy.activity.ItemListActivity.SHOW_ITEMS_LIST";
    public static final String EXTRA_ITEMS_LIST = "ITEMS_LIST";
    List<IMedicineItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        if (ACTION_SHOW_ITEMS_LIST.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_ITEMS_LIST)) {
                items = storage.getCategories().get(getIntent().getIntExtra(EXTRA_ITEMS_LIST, 0)).getItems();
            }
        }
        else {
            items = storage.getCategories().get(0).getItems();
        }
        ItemAdapter adapter = new ItemAdapter(this, R.layout.cell_of_item_list, items);
        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);
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

    private void createAdapter() {

        String[] fromColumns = {};



    }

}
