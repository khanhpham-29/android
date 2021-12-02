package phamquoc.khanh.news;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowHeadlines extends AppCompatActivity {
    // Main category has already been selected by user: ‘World News’, Business’, ...
    // ["urlCaption", "urlAddress"] comes in a bundle sent by main thread
    // here we access RSS-feed and show corresponding headlines
    ArrayList<SingleItem> newsList = new ArrayList<SingleItem>();
    ListView myListView;
    ImageView img_logo_channels;
    String newTitle, urlAddress, urlCaption;
    int logo;
    SingleItem selectedNewsItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_channels);

        Intent callingIntent = getIntent();
        Bundle sentData = callingIntent.getExtras();
        newTitle = sentData.getString("newsTitle");
        urlAddress = sentData.getString("urlAddress");
        urlCaption = sentData.getString("urlCaption");
        logo = sentData.getInt("logo");

        this.setTitle("Items in " + urlCaption + " - " + newTitle);

        img_logo_channels = (ImageView) findViewById(R.id.img_logo_channels);
        img_logo_channels.setImageResource(logo);

        // find out which intent is calling us & grab data bundle holding selected url & caption sent to us
        // update app’s top ‘TitleBar’ (eg. ‘NPR - Business Wed April 09, 2014’)
        myListView = (ListView) this.findViewById(R.id.myListView);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int index, long id) {
                selectedNewsItem = newsList.get(index);
                showNiceDialogBox(selectedNewsItem, getApplicationContext());
            }
        });
        // get stories for the selected news option
        DownloadRssFeed downloader = new DownloadRssFeed(ShowHeadlines.this);
        downloader.execute(urlAddress, urlCaption);
    }//onCreate

    // make a nice-looking dialog box (story summary, btnClose, btnMore)
    public void showNiceDialogBox(SingleItem selectedStoryItem, Context context) {
        // CAUTION: (check)on occasions title and description are the same!
        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.toLowerCase().equals(description.toLowerCase())) {
            description = "";
        }
        try {
            //CAUTION: sometimes TITLE and DESCRIPTION include HTML markers
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            myBuilder.setIcon(logo)
                    .setTitle(Html.fromHtml(urlCaption))
                    .setMessage(title + "\n\n" + Html.fromHtml(description) + "\n")
                    .setPositiveButton("Close", null)
                    .setNegativeButton("More", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichOne) {
                            Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
                            startActivity(browser);
                        }
                    }) //setNegativeButton
                    .show();
        } catch (Exception e) {
            Log.e("Error DialogBox", e.getMessage());
        }
    }//showNiceDialogBox
}//ShowHeadlines