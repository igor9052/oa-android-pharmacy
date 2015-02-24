package android.oa.com.ua.pharmacy.activity;

import android.content.Context;
import android.oa.com.ua.pharmacy.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageTextAdapterMenu extends BaseAdapter {
    private Context mContext;
    private Integer[] mThumbIds = {R.drawable.aromat,R.drawable.cosmet,R.drawable.gomeo,R.drawable.med,R.drawable.mom_child,R.drawable.preparaty};
    private String[] textArray = {"Ароматерапия","Косметика","Гомеопатия","Изделия медицинского назначения","Товары для детей и мам","Лекарственные препараты"};

    public ImageTextAdapterMenu(Context context){
        this.mContext=context;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        if (convertView == null) {
            grid = new View(mContext);
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.cell_of_item_grid, parent, false);
        } else {
            grid = (View) convertView;
        }
        ImageView imageView = (ImageView) grid.findViewById(R.id.image_view_grid);
        TextView textView = (TextView) grid.findViewById(R.id.text_view_grid);
        imageView.setImageResource(mThumbIds[position]);
        textView.setText(textArray[position]);
        return grid;
    }
}
