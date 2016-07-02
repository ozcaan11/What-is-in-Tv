package com.ozcaan11.l50.tvdenevar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ozcaan11.l50.tvdenevar.Classes.Channel;
import com.ozcaan11.l50.tvdenevar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Author : l50 - Özcan YARIMDÜNYA (@ozcaan11)
 * Date   : 02.07.2016 - 20:30
 */

public class AllChannelAdapter extends ArrayAdapter<Channel> {
    public AllChannelAdapter(Context context, ArrayList<Channel> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Channel channel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_all_channels, parent, false);
        }

        ImageView channelIcon = (ImageView) convertView.findViewById(R.id.imageView_channelIcon);
        TextView channelName = (TextView) convertView.findViewById(R.id.textView_channelName);

        channelName.setText(channel.getName());
        Picasso.with(getContext()).load(channel.getIconUrl()).into(channelIcon);

        return convertView;
    }
}
