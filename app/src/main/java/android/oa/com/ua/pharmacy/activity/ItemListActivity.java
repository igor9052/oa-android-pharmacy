package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.adapter.ItemAdapter;
import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ItemListActivity extends Activity {

    public static final String ACTION_SHOW_ITEMS_LIST = "android.oa.com.ua.pharmacy.activity.ItemListActivity.SHOW_ITEMS_LIST";
    public static final String EXTRA_ITEMS_LIST = "ITEMS_LIST";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String EXTRA_NEW_ITEM = "NEW_ITEM";
    public static final int ADD_NEW_ITEM_REQUEST_CODE = 27349;

    List<IMedicineItem> items;
    ListView listView;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        final TextView categoryName = (TextView) findViewById(R.id.category_name);
        if (ACTION_SHOW_ITEMS_LIST.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_ITEMS_LIST)) {
                items = getIntent().getParcelableArrayListExtra(EXTRA_ITEMS_LIST);
            }
            if (getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
                categoryName.setText(getIntent().getStringExtra(EXTRA_CATEGORY_NAME));
            }
        }
        adapter = new ItemAdapter(this, items);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.add_new_item_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewItemActivity.ACTION_ADD_NEW_ITEM);
                intent.putExtra(AddNewItemActivity.EXTRA_CATEGORY_NAME, categoryName.getText());
                startActivityForResult(intent, ADD_NEW_ITEM_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case ADD_NEW_ITEM_REQUEST_CODE:
                    IMedicineItem newItem = data.getParcelableExtra(EXTRA_NEW_ITEM);
                    items.add(newItem);
                    adapter.notifyDataSetChanged();
            }
        }
    }
}
