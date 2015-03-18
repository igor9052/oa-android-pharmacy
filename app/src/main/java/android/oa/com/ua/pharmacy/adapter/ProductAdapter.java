package android.oa.com.ua.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.activity.ProductDetailsActivity;
import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 20.02.2015.
 */
public class ProductAdapter extends ArrayAdapter<IMedicineProduct> {


    public ProductAdapter(Context context, List<IMedicineProduct> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        IMedicineProduct item = getItem(position);
        View view;
        if (convertView == null) {
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell_of_product_list, parent, false);
        } else {
            view = convertView;
        }
        TextView productName = (TextView) view.findViewById(R.id.product_name);
        TextView description = (TextView) view.findViewById(R.id.product_description);
        ImageView image = (ImageView) view.findViewById(R.id.image_view);

        productName.setText(item.getName());
        description.setText(item.getDescription());
        ImageLoader.getInstance().displayImage(item.getImageUrl(), image);

        Button detailsButton = (Button) view.findViewById(R.id.show_item_details_button);

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMedicineProduct item = getItem(position);
                Intent intent = new Intent(ProductDetailsActivity.ACTION_SHOW_PRODUCT);
                intent.putExtra(ProductDetailsActivity.EXTRA_CATEGORY_NAME, item.getCategory());
                intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT, item);
                getContext().startActivity(intent);
            }
        });

        return view;
    }


}
