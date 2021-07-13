package org.teechtown.testtanb;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import org.teechtown.testtanb.Gerlick.Menu_list;
import org.teechtown.testtanb.ui.main.SectionsPagerAdapter;

import java.io.File;
import java.util.ArrayList;

import static org.teechtown.testtanb.R.string.tab_text_2;

public class MainActivity extends AppCompatActivity {


    private DBHelp DBHelper;
    ArrayList<String> Categry_list;
    ArrayList<Menu_list> menu_lists;
    SQLiteDatabase db;
    String FilePath = "/data/data/org.teechtown.testtanb/databases/" + DBHelper.DBName;
    public String url = "https://gist.githubusercontent.com/torder-dev/6736a8e0459d3f8e234038af45608f25/raw/2fefdc4b4a587e5c9b66bafe9048f50afa4d6315/mock.json";
    ServerContect serverContect = new ServerContect(url);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        test();
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }



    private void test(){
        DBHelper = new DBHelp(MainActivity.this, DBHelper.DBName, null, 1);


        if (!new File(FilePath).exists()) { // Sqlite 파일 여부 확인

            db = DBHelper.getWritableDatabase();
            DBHelper.onCreate(db); // sqlite 생성

            serverContect.start(); // 서버연결 및 json 내역 get 방식으로 리스트 받음
            try {
                serverContect.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Json_parson jsonparson = new Json_parson(); // json 파싱 진행
            jsonparson.MenuList_json(serverContect.getResult().trim());


            if (Menu_lists.Menu_lists.size() > 0) {
                int dbinsert = 0; // db insert
                while (dbinsert != Menu_lists.Menu_lists.size()) {
                    DBHelper.Menulist_inssert(Menu_lists.Menu_lists.get(dbinsert).getCategoryId(), Menu_lists.Menu_lists.get(dbinsert).getCategoryName(), Menu_lists.Menu_lists.get(dbinsert).getItemId(), Menu_lists.Menu_lists.get(dbinsert).getItemName(), Menu_lists.Menu_lists.get(dbinsert).getItemPrice(), Menu_lists.Menu_lists.get(dbinsert).getItemSoldOutFlag(), Menu_lists.Menu_lists.get(dbinsert).getItemImageUrl());

                    dbinsert++;
                }
                Toast.makeText(getApplicationContext(), "Updatae complete", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "server data null", Toast.LENGTH_SHORT).show();
            }

        } else {
            Categry_list = new ArrayList<>();
            //menu_lists = new ArrayList<>();
            Categry_list = DBHelper.CategoryName();
            Menu_lists.Categry_lists.clear();
            Menu_lists.Categry_lists.addAll(Categry_list);
            //int[] TAB_TITLES = new int[]{Menu_lists.Categry_lists.get(0).};
            menu_lists = DBHelper.Menulist_select(0);
            Menu_lists.Menu_lists.clear();
            Menu_lists.Menu_lists.addAll(menu_lists);
            if (menu_lists.size() > 0) {
                Log.i("test", "onResume: " + menu_lists.toString());
            }
        }

    }

}