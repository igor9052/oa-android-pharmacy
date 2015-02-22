package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class CatalogActivity extends Activity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grid);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

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

}

class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mThumbIds = {R.drawable.aromat, R.drawable.cosmet, R.drawable.gomeo, R.drawable.med, R.drawable.mom_child, R.drawable.preparaty};

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setLayoutParams(new GridView.LayoutParams(260, 240));
        return imageView;
    }
}
