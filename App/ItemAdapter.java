package com.example.leandri.itrw_324;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Leandri on 2017-09-11.
 */

public class ItemAdapter extends BaseAdapter {
    ArrayList<String> titlesArrayList;
    ArrayList<String> editionsArrayList;
    ArrayList<String> pricesArrayList;
    ArrayList<String> authorsArrayList;

    LayoutInflater mInflater;
    public ItemAdapter(Context c, ArrayList<String> titles, ArrayList<String> editions, ArrayList<String> prices, ArrayList<String> authors)
    {
        titlesArrayList = titles;
        editionsArrayList = editions;
        pricesArrayList = prices;
        authorsArrayList = authors;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return titlesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return titlesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView txtViewTitle = (TextView) v.findViewById(R.id.txtViewTitle);
        TextView txtViewPrice = (TextView) v.findViewById(R.id.txtViewPrice);
        TextView txtViewAuthor = (TextView) v.findViewById(R.id.txtViewAuthor);

        String title = titlesArrayList.get(position);
        String edition = editionsArrayList.get(position);
        String price = pricesArrayList.get(position);
        String author = authorsArrayList.get(position);

        txtViewTitle.setText(title + ", edition " + edition);
        txtViewAuthor.setText(author);
        txtViewPrice.setText("R" + price);

        return v;
    }
}
