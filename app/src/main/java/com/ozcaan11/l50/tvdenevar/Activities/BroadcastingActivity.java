package com.ozcaan11.l50.tvdenevar.Activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ozcaan11.l50.tvdenevar.Adapters.BroadcastAdapter;
import com.ozcaan11.l50.tvdenevar.Classes.Broadcast;
import com.ozcaan11.l50.tvdenevar.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class BroadcastingActivity extends AppCompatActivity {

    public static String URL_HOME = "http://www.tvyayinakisi.com/";

    ListView listView;
    TextView title;
    ImageView logo;
    ProgressDialog dialog;

    ArrayList<Broadcast> broadcastArrayList = new ArrayList<>();
    Broadcast broadcast;
    String icon_e;
    String title_e;
    String iUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcasting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView_bChannelBroadcast);
        title = (TextView) findViewById(R.id.textView_bChannelName);
        logo = (ImageView) findViewById(R.id.imageView_bChannelIcon);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        iUrl = getIntent().getStringExtra("ch_url");
        try {
            new BringBroadCast().execute(iUrl);
        } catch (Exception e) {
            Toast.makeText(BroadcastingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            try {
                new BringBroadCast().execute(iUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private class BringBroadCast extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(BroadcastingActivity.this);
            dialog.setTitle("Loading");
            dialog.setMessage("Please wait ..");
            dialog.setCancelable(false);
            dialog.setIndeterminate(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                Document document = Jsoup.connect(URL_HOME + params[0]).get();
                Elements elements = document.select("div[class^=row]");
                Elements icon_and_title = document.select("div[class=seven columns]").select("img");
                icon_e = icon_and_title.attr("src");
                title_e = icon_and_title.attr("title");

                // This is for broadcasting
                for (Element e : elements) {
                    if (!e.select("div[class=two columns time]").text().equalsIgnoreCase("") || !e.select("div[class=ten columns]").text().equalsIgnoreCase("")) {

                        broadcast = new Broadcast();
                        broadcast.setbTime(e.select("div[class=two columns time]").text());
                        broadcast.setbTitle(e.select("div[class=ten columns]").text());
                        if (e.select("div[class=two columns time]").text().length() <= 10)
                            broadcastArrayList.add(broadcast);
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            BroadcastAdapter adapter = new BroadcastAdapter(getApplicationContext(), broadcastArrayList);
            listView.setAdapter(adapter);

            Picasso.with(getApplicationContext()).load(URL_HOME + icon_e).into(logo);
            title.setText(title_e);

            dialog.dismiss();
        }
    }

}
