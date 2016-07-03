package com.ozcaan11.l50.tvdenevar.Fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ozcaan11.l50.tvdenevar.Adapters.NowInTvAdapter;
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
public class NowInTvFragment extends Fragment {
    public static String URL = "http://www.tvyayinakisi.com/yayinda-olanlar";
    public static String URL_HOME = "http://www.tvyayinakisi.com/";

    ArrayList<Channel> channelArrayList = new ArrayList<>();
    Channel channel;
    ProgressDialog dialog;
    ListView listView;

    public NowInTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_in_tv, container, false);

        listView = (ListView) view.findViewById(R.id.listView_now_in_tv);

        try {
            new BringWhatsInTvNow().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private class BringWhatsInTvNow extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(getContext());
            dialog.setTitle("Please wait");
            dialog.setMessage("Loading ..");
            dialog.setCancelable(false);
            dialog.setIndeterminate(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(URL).get();
                Elements elements = document.select("div[class^=three columns]");

                for (Element e : elements) {

                    channel = new Channel();
                    channel.setProgram(e.select("a").text());
                    channel.setChannelUrl(e.select("a").attr("href"));
                    channel.setIconUrl(URL_HOME + e.select("a").select("img").attr("src"));
                    channel.setProgramTime(e.select("span").text());

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

            channelArrayList.remove(0);

            NowInTvAdapter adapter = new NowInTvAdapter(getContext(), channelArrayList);
            listView.setAdapter(adapter);

            dialog.dismiss();
        }

    }

}
