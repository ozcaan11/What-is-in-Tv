package com.ozcaan11.l50.tvdenevar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ozcaan11.l50.tvdenevar.Classes.Broadcast;
import com.ozcaan11.l50.tvdenevar.R;

import java.util.ArrayList;

/**
 * Author : l50 - Özcan YARIMDÜNYA (@ozcaan11)
 * Date   : 02.07.2016 - 21:44
 */

public class BroadcastAdapter extends ArrayAdapter<Broadcast> {
    public BroadcastAdapter(Context context, ArrayList<Broadcast> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Broadcast broadcast = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_broadcast, parent, false);
        }

        TextView time = (TextView) convertView.findViewById(R.id.textView_bTime);
        TextView title = (TextView) convertView.findViewById(R.id.textView_bTitle);

        time.setText(broadcast.getbTime());
        title.setText(broadcast.getbTitle());

        return convertView;
    }
}
