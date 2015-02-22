package android.oa.com.ua.pharmacy.adapter;

import android.content.Context;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.activity.ItemActivity;
import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 20.02.2015.
 */
public class ItemAdapter extends ArrayAdapter<IMedicineItem> {


    public ItemAdapter(Context context, List<IMedicineItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        IMedicineItem item = getItem(position);
        View view = View.inflate(getContext(), R.layout.cell_of_item_list, null);
        TextView title = (TextView) view.findViewById(R.id.item_title);
        TextView description = (TextView) view.findViewById(R.id.item_description);
        ImageView image = (ImageView) view.findViewById(R.id.image_view);

        if (title != null) {
            title.setText(item.getName());
        }

        if (description != null) {
            description.setText(item.getDescription());
        }

        if (image != null) {

            image.setImageResource(item.getImageId());
        }

        Button detailsButton = (Button) view.findViewById(R.id.show_item_details_button);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMedicineItem item = getItem(position);
                Intent intent = new Intent(ItemActivity.ACTION_SHOW_ITEM);
                intent.putExtra(ItemActivity.EXTRA_CATEGORY_NAME, item.getCategory());
                intent.putExtra(ItemActivity.EXTRA_ITEM, item);
                getContext().startActivity(intent);
            }
        });

        return view;
    }


}
