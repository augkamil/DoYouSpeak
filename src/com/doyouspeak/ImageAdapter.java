package com.doyouspeak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ImageAdapter extends BaseAdapter {
    private Context mContext;
    
    static final LauncherIcon[] ICONS = {
        new LauncherIcon(R.drawable.ic_localization, "Lokalizacja"),
        new LauncherIcon(R.drawable.ic_expressions, "Zwroty"),
        new LauncherIcon(R.drawable.ic_my_list, "Moja Lista"),
        new LauncherIcon(R.drawable.ic_reminder, "Przypomnienie"),
        new LauncherIcon(R.drawable.ic_record, "Dodaj Zwrot")
    };
    
    public ImageAdapter(Context c) {
        mContext = c;
    }

	@Override
    public int getCount() {
        return ICONS.length;
    }

    @Override
    public LauncherIcon getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public ImageView icon;
        public TextView text;
    }

    static class LauncherIcon {
        final String text;
        final int imgId;
 
        public LauncherIcon(int imgId, String text) {
            super();
            this.imgId = imgId;
            this.text = text;
        }
 
    }
    
    // Create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

            v = vi.inflate(R.layout.dashboard_icon, null);
            holder = new ViewHolder();
            holder.text = (TextView) v.findViewById(R.id.dashboard_icon_text);
            holder.icon = (ImageView) v.findViewById(R.id.dashboard_icon_img);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.icon.setImageResource(ICONS[position].imgId);
        holder.text.setText(ICONS[position].text);

        return v;
    }
}
