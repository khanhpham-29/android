package phamquoc.khanh.news;


import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    GridView grid_news;
    List<NewsItems> ListNewsItems;

    private void createNewsItemsList() {
        int n = 3;
        String newsTitles[] = {
                "Thanh niên",
                "VNExpress",
                "Dân Trí"
        };
        int Logo[] = {
                R.mipmap.logo_thanhnien,
                R.mipmap.logo_vnexpress,
                R.mipmap.logo_dantri

        };
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
                {"https://feeds.npr.org/510309/podcast.xml", "Society & culture"}
        };

        List<String[][]> UrlCaptionsMenu = new ArrayList<>();
        UrlCaptionsMenu.add(0,ThanhNienUrlCaptionMenu);
        UrlCaptionsMenu.add(1,VNExpressUrlCaptionMenu);
        UrlCaptionsMenu.add(2,DanTriUrlCaptionMenu);

        ListNewsItems = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            NewsItems newsItem = new NewsItems(newsTitles[i], Logo[i], UrlCaptionsMenu.get(i));
            ListNewsItems.add(newsItem);
        }

    }

    ArrayAdapter<String> aa;
    NewsItemsAdapter na;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("News app");
        createNewsItemsList();

        grid_news = (GridView) findViewById(R.id.grid_news);
        na = new NewsItemsAdapter(ListNewsItems, this);
        grid_news.setAdapter(na);

//        aa = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                newsTitles);
//        grid_news.setAdapter(aa);

//        context = getApplicationContext();
//
//        grid_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String news = newsTitles[i];
//                Intent callShowChannels = new Intent(MainActivity.this, ShowChannels.class);
//                Bundle data = new Bundle();
//                data.putString("newsTitle", news);
//                callShowChannels.putExtras(data);
//                startActivity(callShowChannels);
//            }
//        });

    }//onCreate
}