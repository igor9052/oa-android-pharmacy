package android.oa.com.ua.pharmacy.adapter;

import android.content.Context;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageTextAdapterMenu extends ArrayAdapter<IMedicineCategory> {

    public ImageTextAdapterMenu(Context context, List<IMedicineCategory> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IMedicineCategory category = getItem(position);
        View grid;
        if (convertView == null) {
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.cell_of_catalog_grid, parent, false);
        } else {
            grid = convertView;
        }
        ImageView imageView = (ImageView) grid.findViewById(R.id.image_view_grid);
        TextView textView = (TextView) grid.findViewById(R.id.text_view_grid);
        imageView.setImageResource(category.getImage());
        textView.setText(category.getName());
        return grid;
    }
}
