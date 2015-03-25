package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Context;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.entity.impl.ShoppingCart;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ShoppingCartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_shopping_cart);
        toolbar.setTitle(getString(R.string.text_shopping_cart_title));

        ListView listView = (ListView) findViewById(R.id.listview_shopping_cart_entities);
        listView.setAdapter(new ShoppingCartAdapter(this, ShoppingCart.getInstance().getShoppingCartEntities()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
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

    private static class ShoppingCartAdapter extends ArrayAdapter<ShoppingCart.ShoppingCartEntity> {

        private ShoppingCartAdapter(Context context, List<ShoppingCart.ShoppingCartEntity> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ShoppingCart.ShoppingCartEntity shoppingCartEntity = getItem(position);
            View view;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.cell_of_shopping_cart, parent, false);
            } else {
                view = convertView;
            }

            ImageView productImage = (ImageView) view.findViewById(R.id.image_view_shopping_cart_cell);
            TextView productName = (TextView) view.findViewById(R.id.text_product_name_shopping_cart_cell);
            TextView productAmount = (TextView) view.findViewById(R.id.text_amount_shopping_cart_cell);

            ImageLoader.getInstance().displayImage(shoppingCartEntity.getProduct().getImageUrl(), productImage);
            productName.setText(shoppingCartEntity.getProduct().getName());
            productAmount.setText(String.valueOf(shoppingCartEntity.getAmount()));

            return view;
        }
    }
}
