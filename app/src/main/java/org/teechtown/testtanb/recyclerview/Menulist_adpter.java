package org.teechtown.testtanb.recyclerview;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.teechtown.testtanb.Gerlick.Menu_list;
import org.teechtown.testtanb.Menu_lists;
import org.teechtown.testtanb.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Menulist_adpter extends RecyclerView.Adapter<Menulist_adpter.ViewHolder> {

    Context context;
    Menu_list menu_list_data;
    ArrayList<Menu_list> menu_lists;

    public Menulist_adpter(Context context, ArrayList<Menu_list> menu_lists) {
        super();
        this.context = context;
        this.menu_lists = menu_lists;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView itemimage;
        private ImageView soldout;
        private TextView prob_name;
        private TextView price_won;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemimage = (ImageView) itemView.findViewById(R.id.itemImage);
            soldout = (ImageView)itemView.findViewById(R.id.soldout_img);
            prob_name = (TextView)itemView.findViewById(R.id.itemName);
            price_won = (TextView)itemView.findViewById(R.id.itemPrice);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.menu,parent,false);
        return new ViewHolder(view);
        //return null ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            menu_list_data = Menu_lists.Menu_lists.get(position);
                Glide.with(context)
                .load(Menu_lists.Menu_lists.get(position).getItemImageUrl())
                .override(700,700) // SIZE 지정
                .thumbnail(0.1f) // LOAD 시 일부 단위위
                .into(holder.itemimage);

                holder.soldout.setVisibility(Integer.parseInt(String.valueOf(menu_list_data.getItemSoldOutFlag().equals("true"))));
                holder.prob_name.setText(menu_list_data.getItemName());
                holder.price_won.setText(menu_list_data.getItemPrice());
    }

    @Override
    public int getItemCount() {
        return Menu_lists.Menu_lists.size();
    }



}
