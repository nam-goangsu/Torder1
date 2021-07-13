package org.teechtown.testtanb.recyclerview;

import android.os.Bundle;


import org.teechtown.testtanb.Menu_lists;
import org.teechtown.testtanb.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;


public class Menu_activity extends AppCompatActivity {
    RecyclerView menu_list_recycler;
    Menulist_adpter menulist_adpter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        menu_list_recycler = (RecyclerView)findViewById(R.id.recycler);
        menulist_adpter = new Menulist_adpter(this,Menu_lists.Menu_lists);

        menu_list_recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));

        RecyclerView.ItemAnimator animator2 = menu_list_recycler.getItemAnimator();
        if (animator2 instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator2).setSupportsChangeAnimations(false);
        }




        menu_list_recycler.setAdapter(menulist_adpter);
        menulist_adpter.notifyDataSetChanged();
    }
}
