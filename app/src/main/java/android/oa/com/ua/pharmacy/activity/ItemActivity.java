package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends Activity {

    public static final String ACTION_SHOW_ITEM = "android.oa.com.ua.pharmacy.activity.ItemListActivity.SHOW_ITEM";

    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String EXTRA_ITEM = "ITEM";

    private IMedicineItem item;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        helper = new Helper();
        helper.title.setText("There is no item...");
        if (ACTION_SHOW_ITEM.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_ITEM)) {
                getItemFromExtra();
            }
            if (getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
                helper.category.setText(getIntent().getStringExtra(EXTRA_CATEGORY_NAME));
            }
        }
    }

    private void getItemFromExtra() {
        IMedicineItem item = (IMedicineItem) getIntent().getParcelableExtra(EXTRA_ITEM);
        helper.title.setText(item.getName());
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.ic_launcher);
        helper.image.setImageDrawable(drawable);
        helper.description.setText(item.getDescription());
    }

    private class Helper {
        TextView title = (TextView) findViewById(R.id.item_title);
        TextView category = (TextView) findViewById(R.id.category);
        ImageView image = (ImageView) findViewById(R.id.item_image);
        TextView description = (TextView) findViewById(R.id.item_description);
    }
}
