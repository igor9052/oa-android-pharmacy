package android.oa.com.ua.pharmacy.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 20.02.2015.
 */
public class ItemAdapter extends ArrayAdapter<IMedicineItem> {

    Context context;

    public ItemAdapter(Context context, int resource, List<IMedicineItem> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IMedicineItem item = getItem(position);
        View v = convertView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.cell_of_item_list, null);

        TextView title = (TextView) v.findViewById(R.id.item_title);
        TextView description = (TextView) v.findViewById(R.id.item_description);
        ImageView image = (ImageView) v.findViewById(R.id.image_view);

        if (title != null) {
            title.setText(item.getName());
        }

        if (description != null) {
            description.setText(item.getDescription());
        }

        if (image != null) {
            image.setImageResource(item.getImageId());
        }
        return v;
    }
}
