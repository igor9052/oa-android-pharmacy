package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ProductDetailsActivity extends Activity {

    public static final String ACTION_SHOW_PRODUCT = "android.oa.com.ua.pharmacy.activity.ProductDetailsActivity.SHOW_PRODUCT";

    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String EXTRA_PRODUCT = "PRODUCT";

    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        helper = new Helper();
        helper.title.setText("There is no item...");
        if (ACTION_SHOW_PRODUCT.equals(getIntent().getAction())) {
            if (getIntent().hasExtra(EXTRA_PRODUCT)) {
                getItemFromExtra();
            }
            if (getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
                helper.category.setText(getIntent().getStringExtra(EXTRA_CATEGORY_NAME));
            }
        }
    }

    private void getItemFromExtra() {
        IMedicineProduct item = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        helper.title.setText(item.getName());
        helper.description.setText(item.getDescription());
        ImageLoader.getInstance().displayImage(item.getImageUrl(), helper.image);
    }

    private class Helper {
        TextView title = (TextView) findViewById(R.id.product_name);
        TextView category = (TextView) findViewById(R.id.category);
        ImageView image = (ImageView) findViewById(R.id.item_image);
        TextView description = (TextView) findViewById(R.id.product_description);
    }
}
