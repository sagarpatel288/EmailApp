package com.example.android.emailapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.android.emailapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity  {


    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "Outlook",
            "Gmail", "Yahoo",
    };


    int[] listviewImage = new int[]{
            R.drawable.ic_outlook_start_page,
              R.drawable.icons_gmail_start_page, R.drawable.icons_yahoo_start_page,
    };

    String[] listviewShortDescription = new String[]{
            "hotmail, live and msn ", "Google mail ", "Yahoo mail",

    };


  //  private static Resources mResources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 3; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);


        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(),  com.example.android.emailapp.login.MainActivity.class );
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"outlook",Toast.LENGTH_LONG).show();

                }
                else if (position == 1) {
                   Intent intent = new Intent(getApplicationContext(), com.livemymail.android.mailboxapp.activities.MainActivity.class);
                   startActivity(intent);
                    Toast.makeText(getApplicationContext(),"gmail",Toast.LENGTH_LONG).show();
                }
                else if (position == 2) {
                //    Intent intent = new Intent(this, FriendList.class);
                  //  startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Yahoo",Toast.LENGTH_LONG).show();
                }
            }

        });

    /*    //Dbflow
        mResources = getResources();

        // DBFlow init
        FlowManager.init(new FlowConfig.Builder(this)
                .addDatabaseConfig(
                        new DatabaseConfig.Builder(AppDatabase.class)
                                .build())
                .build());*/

    }


   /* public static Resources getAppResources() {
        return mResources;
    } */
}
