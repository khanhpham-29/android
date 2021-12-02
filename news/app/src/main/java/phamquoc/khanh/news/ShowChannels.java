package phamquoc.khanh.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ShowChannels extends AppCompatActivity {
    // Main GUI - A NEWS application based on National Public Radio RSS material
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    ImageView img_logo_channels;
    Context context;
    String[][] selectedNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_channels);

        Intent callingIntent = getIntent();
        NewsItems newsItem = (NewsItems) callingIntent.getSerializableExtra("newsItem");

        this.setTitle("Channels in " + newsItem.getName());

        img_logo_channels = (ImageView) findViewById(R.id.img_logo_channels);
        img_logo_channels.setImageResource(newsItem.getLogo());

        //define convenient URL and CAPTIONs arrays
        int Length = newsItem.getUrlCaptionMenu().length;
        String[] myUrlCaption = new String[Length];
        String[] myUrlAddress = new String[Length];

        selectedNews = newsItem.getUrlCaptionMenu();
        for (int i = 0; i < myUrlAddress.length; i++) {
            myUrlAddress[i] = selectedNews[i][0];
            myUrlCaption[i] = selectedNews[i][1];
        }
        context = getApplicationContext();
        // user will tap on a ListView’s row to request category’s headlines
        myMainListView = (ListView) this.findViewById(R.id.myListView);

        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String urlAddress = myUrlAddress[position];
                String urlCaption = myUrlCaption[position];
                Intent callShowHeadLines = new Intent(ShowChannels.this, ShowHeadlines.class);
                Bundle myData = new Bundle();
                myData.putString("newsTitle", newsItem.getName());
                myData.putString("urlAddress", urlAddress);
                myData.putString("urlCaption", urlCaption);
                myData.putInt("logo", newsItem.getLogo());
                callShowHeadLines.putExtras(myData);
                startActivity(callShowHeadLines);
            }
        });

        // fill up the Main-GUI’s ListView with main news categories
        adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myUrlCaption);
        myMainListView.setAdapter(adapterMainSubjects);

    }//onCreate
}