package org.teechtown.testtanb;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.teechtown.testtanb.Gerlick.Menu_list;

import java.util.ArrayList;

public class Json_parson {    ArrayList<Menu_list> menulist;
    Menu_list menu_list_data;
    JSONArray Category, CatItem;
    JSONObject CatObject = null,CatObject1 = null,ItmeObject =null,tes =null;
    private DBHelp DBHelper;
    Context mcontext;

    public void MenuList_json(String MenuJson){

        try{
            CatObject = new JSONObject(MenuJson);
            Category = CatObject.getJSONArray("categories");
            // catid, catname 2번 받고 // 나머지 리스트 받아서
            //2중 포문으로 catid,catname 1번 // 리스트 각각
            menulist = new ArrayList<>();
            for(int i =0; i<Category.length(); i++){
                JSONObject jsonObject = Category.getJSONObject(i);
                CatItem = jsonObject.getJSONArray("categoryItems");
                for(int j =0; j<CatItem.length();j++){
                    menu_list_data = new Menu_list();
                    menu_list_data.setCategoryId(jsonObject.getInt("categoryId"));
                    menu_list_data.setCategoryName(jsonObject.getString("categoryName"));
                    JSONObject jsonObject1 = CatItem.getJSONObject(j);
                    ItmeObject = CatItem.getJSONObject(j);
                    menu_list_data.setItemId(jsonObject1.getInt("itemId"));
                    menu_list_data.setItemName(jsonObject1.getString("itemName"));
                    menu_list_data.setItemPrice(jsonObject1.getInt("itemPrice"));
                    //menu_list_data.setItemSoldOutFlag((jsonObject1.getString("itemSoldOutFlag").toLowerCase()));
                    menu_list_data.setItemSoldOutFlag("false");
                    menu_list_data.setItemImageUrl(jsonObject1.getString("itemImageUrl"));
                    menulist.add(menu_list_data);
                }
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        int dbinsert =0;
        Menu_lists.Menu_lists.addAll(menulist);

    }



}