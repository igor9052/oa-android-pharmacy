package android.oa.com.ua.pharmacy.adapter;

import android.content.Context;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 22.02.2015.
 */
public class CategoryAdapter extends ArrayAdapter<IMedicineCategory> {

    public CategoryAdapter(Context context, List<IMedicineCategory> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(getContext(), R.layout.cell_of_category_list, null);
        ImageView image = (ImageView) view.findViewById(R.id.category_image);
        TextView textView = (TextView) view.findViewById(R.id.category_name);
        IMedicineCategory category = getItem(position);
        //need to implement adding image
        //image.setImageDrawable();
        textView.setText(category.getName());
        return view;
    }
}
