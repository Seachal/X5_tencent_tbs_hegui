package com.tencent.tbs.demo.viewutils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tencent.tbs.demo.R;
import java.util.ArrayList;
import java.util.TreeSet;

public class SectionListAdapter extends BaseAdapter {

    private static final String TAG = "SectionListAdapter";

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private final ArrayList<String> mData = new ArrayList<>();
    private final TreeSet<Integer> sectionHeader = new TreeSet<>();

    private LayoutInflater mInflater;

    public SectionListAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int rowType = getItemViewType(i);
        if (view == null) {
            switch (rowType) {
                case TYPE_ITEM:
                    view = mInflater.inflate(R.layout.nav_list_item, null);
                    break;
                case TYPE_SEPARATOR:
                    view = mInflater.inflate(R.layout.nav_section_header, null);
                    break;
            }
        }
        TextView item = (TextView) view;
        item.setText(mData.get(i));
        Log.i(TAG, "getView: " + i);
        return item;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public boolean isEnabled(int position) {
        return !sectionHeader.contains(position);
    }
}
