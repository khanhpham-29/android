package phamquoc.khanh.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    Context context;
    SingleItem selectedNewsItem;
    Map<String, String[][]> newsItems;
    String[][] selectedNews;

    // hard-coding main NEWS categories (TODO: use a resource file)
    String[][] ThanhNienUrlCaptionMenu = {
            {"https://feeds.npr.org/510289/podcast.xml", "Business"},
            {"https://feeds.npr.org/344098539/podcast.xml", "Comedy"},
            {"https://feeds.npr.org/510308/podcast.xml", "Science"},
            {"https://feeds.npr.org/510298/podcast.xml", "Technology"},
            {"https://feeds.npr.org/510306/podcast.xml", "Music"},
            {"https://feeds.npr.org/510354/podcast.xml", "Kid & family"},
            {"https://feeds.npr.org/510309/podcast.xml", "Society & culture"}
    };

    String[][] VNExpressUrlCaptionMenu = {
            {"https://feeds.npr.org/510289/podcast.xml", "Business"},
            {"https://feeds.npr.org/344098539/podcast.xml", "Comedy"},
            {"https://feeds.npr.org/510308/podcast.xml", "Science"},
            {"https://feeds.npr.org/510298/podcast.xml", "Technology"},
            {"https://feeds.npr.org/510306/podcast.xml", "Music"},
            {"https://feeds.npr.org/510354/podcast.xml", "Kid & family"},
            {"https://feeds.npr.org/510309/podcast.xml", "Society & culture"}
    };

    String[][] DanTriUrlCaptionMenu = {
            {"https://feeds.npr.org/510289/podcast.xml", "Business"},
            {"https://feeds.npr.org/344098539/podcast.xml", "Comedy"},
            {"https://feeds.npr.org/510308/podcast.xml", "Science"},
            {"https://feeds.npr.org/510298/podcast.xml", "Technology"},
            {"https://feeds.npr.org/510306/podcast.xml", "Music"},
            {"https://feeds.npr.org/510354/podcast.xml", "Kid & family"},
            {"https://feeds.npr.org/510309/podcast.xml", "Society & culture"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_channels);

        newsItems = new HashMap<>();
        newsItems.put("Thanh niên", ThanhNienUrlCaptionMenu);
        newsItems.put("VNExpress", VNExpressUrlCaptionMenu);
        newsItems.put("Dân Trí", DanTriUrlCaptionMenu);

        Intent callingIntent = getIntent();
        Bundle sentData = callingIntent.getExtras();
        String newsTitle = sentData.getString("newsTitle");

        this.setTitle("Channels in " + newsTitle);
        selectedNews = newsItems.get(newsTitle);

        //define convenient URL and CAPTIONs arrays
        String[] myUrlCaption = new String[selectedNews.length];
        String[] myUrlAddress = new String[selectedNews.length];


        for (int i = 0; i < myUrlAddress.length; i++) {
            myUrlAddress[i] = selectedNews[i][0];
            myUrlCaption[i] = selectedNews[i][1];
        }
        context = getApplicationContext();
        // user will tap on a ListView’s row to request category’s headlines
        myMainListView = (ListView) this.findViewById(R.id.myListView);
        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
                String urlAddress = myUrlAddress[_index], urlCaption = myUrlCaption[_index];
                //create an Intent to talk to activity: ShowHeadlines
                Intent callShowHeadlines = new Intent(ShowChannels.this, ShowHeadlines.class);
                //prepare a Bundle and add the input arguments: url & caption
                Bundle myData = new Bundle();
                myData.putString("newsTitle", newsTitle);
                myData.putString("urlAddress", urlAddress);
                myData.putString("urlCaption", urlCaption);

                Toast toast = Toast.makeText(context, newsTitle + "  " + urlAddress, Toast.LENGTH_LONG);
                toast.show();

                callShowHeadlines.putExtras(myData);
                startActivity(callShowHeadlines);
            }
        });
// fill up the Main-GUI’s ListView with main news categories
        adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myUrlCaption);
        myMainListView.setAdapter(adapterMainSubjects);

    }//onCreate
}