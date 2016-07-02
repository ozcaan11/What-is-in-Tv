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
 * Date   : 02.07.2016 - 20:05
 */

public class NowInTvAdapter extends ArrayAdapter<Channel> {

    public NowInTvAdapter(Context context, ArrayList<Channel> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Channel channel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_now_in_tv, parent, false);
        }

        ImageView channelIcon = (ImageView) convertView.findViewById(R.id.channelIcon);
        TextView program = (TextView) convertView.findViewById(R.id.textView_program);
        TextView programTime = (TextView) convertView.findViewById(R.id.textView_programTime);

        program.setText(channel.getProgram());
        programTime.setText(channel.getProgramTime());
        Picasso.with(getContext()).load(channel.getIconUrl()).into(channelIcon);

        return convertView;
    }
}
