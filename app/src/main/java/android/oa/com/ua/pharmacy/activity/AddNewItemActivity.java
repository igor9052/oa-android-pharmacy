package android.oa.com.ua.pharmacy.activity;

import android.oa.com.ua.pharmacy.R;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddNewItemActivity extends ActionBarActivity {

    public static final String ACTION_ADD_NEW_ITEM = "android.oa.com.ua.pharmacy.activity.AddNewItemActivity.ADD_NEW_ITEM";
    public static final String EXTRA_CATEGORY_NAMES = "CATEGORY_NAMES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        List<String> categoryNames = new ArrayList<>();
        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);
        if (ACTION_ADD_NEW_ITEM.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_CATEGORY_NAMES)) {
                categoryNames = getIntent().getStringArrayListExtra(EXTRA_CATEGORY_NAMES);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryNames);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_item, menu);
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
