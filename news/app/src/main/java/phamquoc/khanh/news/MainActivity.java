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
                {"https://thanhnien.vn/rss/thoi-su/chinh-tri-227.rss", "Chính trị"},
                {"https://thanhnien.vn/rss/video/phong-su-349.rss", "Phóng sự"},
                {"https://thanhnien.vn/rss/thoi-su/chong-tin-gia-304.rss", "Chống tin giả"},
                {"https://thanhnien.vn/rss/thoi-su/quoc-phong-64.rss", "Quốc phòng"},
                {"https://thanhnien.vn/rss/the-gioi/goc-nhin-129.rss", "Góc nhìn"},
                {"https://thanhnien.vn/rss/the-gioi/nguoi-viet-nam-chau-48.rss", "Người Việt năm châu"},
                {"https://thanhnien.vn/rss/the-gioi/chuyen-la-269.rss", "Chuyện lạ 269"},
        };
        String[][] VNExpressUrlCaptionMenu = {
                {"https://vnexpress.net/rss/the-gioi.rss", "Thế giới"},
                {"https://vnexpress.net/rss/thoi-su.rss", "Thời sự"},
                {"https://vnexpress.net/rss/kinh-doanh.rss", "Kinh doanh"},
                {"https://vnexpress.net/rss/the-thao.rss", "Thể thao"},
                {"https://vnexpress.net/rss/giai-tri.rss", "Giải trí"},
                {"https://vnexpress.net/rss/giao-duc.rss", "Giáo dục"},
                {"https://vnexpress.net/rss/gia-dinh.rss", "Gia đình"},
        };
        String[][] DanTriUrlCaptionMenu = {
                {"https://dantri.com.vn/trangchu.rss", "Trang chủ"},
                {"https://dantri.com.vn/suc-khoe/ung-thu.rss", "Ung thư"},
                {"https://dantri.com.vn/suc-khoe/kien-thuc-gioi-tinh.rss", "Kiến thức giới tính"},
                {"https://dantri.com.vn/xa-hoi/moi-truong.rss", "Môi trường"},
                {"https://dantri.com.vn/giai-tri/thoi-trang.rss", "Thời trang"},
                {"https://dantri.com.vn/giao-duc-khuyen-hoc/guong-sang.rss", "Gương sáng"},
                {"https://dantri.com.vn/the-thao/bong-da-anh.rss", "Bóng đá Anh"},
        };

        List<String[][]> UrlCaptionsMenu = new ArrayList<>();
        UrlCaptionsMenu.add(0,ThanhNienUrlCaptionMenu);
        UrlCaptionsMenu.add(1,VNExpressUrlCaptionMenu);
        UrlCaptionsMenu.add(2,DanTriUrlCaptionMenu);

        ListNewsItems = new ArrayList<>();
        for (int i = 0; i < newsTitles.length; i++) {
            NewsItems newsItem = new NewsItems(newsTitles[i], Logo[i], UrlCaptionsMenu.get(i));
            ListNewsItems.add(newsItem);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("News app");

        createNewsItemsList();

        grid_news = (GridView) findViewById(R.id.grid_news);
        NewsItemsAdapter na = new NewsItemsAdapter(ListNewsItems, this);
        grid_news.setAdapter(na);

        grid_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItems NewsItem = ListNewsItems.get(position);
                Intent callShowChannels = new Intent(MainActivity.this, ShowChannels.class);
                callShowChannels.putExtra("newsItem", NewsItem);
                startActivity(callShowChannels);
            }
        });

    }//onCreate
}