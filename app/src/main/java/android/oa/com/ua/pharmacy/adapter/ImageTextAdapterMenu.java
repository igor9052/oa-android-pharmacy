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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ImageTextAdapterMenu extends ArrayAdapter<IMedicineCategory> {

    private DisplayImageOptions options;

    public ImageTextAdapterMenu(Context context, List<IMedicineCategory> objects) {
        super(context, 0, objects);
        initDisplayImageOptions();
    }

    private void initDisplayImageOptions() {
        int imageForEmptyUriId = R.drawable.log;
        int imageOnFailId = R.drawable.log;
        int imageOnLoading = R.drawable.log;
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(imageForEmptyUriId)
                .showImageOnFail(imageOnFailId)
                .showImageOnLoading(imageOnLoading).build();
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


        ImageLoader.getInstance().displayImage(category.getImageUrl(), imageView, options);
        textView.setText(category.getName());
        return grid;
    }
}
