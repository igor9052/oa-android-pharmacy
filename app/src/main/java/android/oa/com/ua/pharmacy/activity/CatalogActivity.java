package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;


import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class CatalogActivity extends Activity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grid);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageTextAdapterMenu(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // посылаем идентификатор картинки в для ItemListActivity
                Intent i = new Intent(getApplicationContext(), ItemListActivity.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }
}

