package phamquoc.khanh.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class NewsItemsAdapter extends ArrayAdapter {

    List<NewsItems> listData;
    Context context;
    LayoutInflater layoutInflater;

    public NewsItemsAdapter(List<NewsItems> listData, Context context) {
        super(context, 0, listData);
        this.listData = listData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView imgLogo;
        view = layoutInflater.inflate(R.layout.grid_item_layout, null);
        imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        imgLogo.setImageResource(listData.get(i).getLogo());

        return view;
    }

}
