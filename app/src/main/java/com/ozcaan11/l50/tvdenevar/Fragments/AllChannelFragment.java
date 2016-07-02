package com.ozcaan11.l50.tvdenevar.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ozcaan11.l50.tvdenevar.Adapters.AllChannelAdapter;
import com.ozcaan11.l50.tvdenevar.Activities.BroadcastingActivity;
import com.ozcaan11.l50.tvdenevar.Classes.Channel;
import com.ozcaan11.l50.tvdenevar.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllChannelFragment extends Fragment {

    public static String URL_HOME = "http://www.tvyayinakisi.com/";
    public static String URL_ALL_CHANNEL = "http://www.tvyayinakisi.com/yayin-akislari";

    ListView listView;

    ArrayList<Channel> channelArrayList = new ArrayList<>();
    ProgressDialog dialog;
    Channel channel;


    public AllChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_channel, container, false);

        listView = (ListView) view.findViewById(R.id.listView_all_channels);

        new BringAllChannel().execute();

        return view;
    }

    private class BringAllChannel extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(getContext());
            dialog.setTitle("Loading");
            dialog.setMessage("Please wait ..");
            dialog.setCancelable(false);
            dialog.setIndeterminate(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                Document document = Jsoup.connect(URL_ALL_CHANNEL).get();
                Elements elements = document.select("div[class^=two columns]").select("a");

                for (Element e : elements) {
                    channel = new Channel();
                    channel.setName(e.text());
                    channel.setChannelUrl(e.attr("href"));
                    channel.setIconUrl(URL_HOME+e.select("img").attr("src"));
                    channelArrayList.add(channel);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            AllChannelAdapter adapter = new AllChannelAdapter(getContext(), channelArrayList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(getContext(),URL_HOME+ channelArrayList.get(position).getChannelUrl(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(), BroadcastingActivity.class);
                    intent.putExtra("ch_url",channelArrayList.get(position).getChannelUrl());
                    startActivity(intent);
                }
            });

            dialog.dismiss();
        }
    }

}
