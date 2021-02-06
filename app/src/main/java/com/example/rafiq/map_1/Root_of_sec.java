package com.example.rafiq.map_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Rafiq on 14-Jan-16.
 */
public class Root_of_sec  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_of_sec);
        String[] root_name_Array={"১) কদমতলী ->হুমায়ুন চত্বর->উপশহর-> শিবগঞ্জ -> টিলাগড়->টিলাগড় ঈদগাহ-> ক্যাম্পাস","২) কদমতলী ->হুমায়ুন চত্বর-> সোবানীঘাট-> নাওরপুল-> শাহী ঈদগাহ -> টিলাগড় ঈদগাহ ->ক্যাম্পাস",
                "৩) কদমতলী -> কিন ব্রীজ->বন্দর ->আম্বরখানা->শাহী ঈদগাহ -> টিলাগড় ঈদগাহ ->ক্যাম্পাস","৪) কদমতলী -> কিন ব্রীজ->বন্দর ->নাওরপুল->শিবগঞ্জ -> টিলাগড়->টিলাগড় ঈদগাহ-> ক্যাম্পাস"};
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,root_name_Array);
        ListView myListView=(ListView)findViewById(R.id.listView);
        myListView.setAdapter(myAdapter);
    }

}
