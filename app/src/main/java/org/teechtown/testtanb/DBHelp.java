package org.teechtown.testtanb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.teechtown.testtanb.Gerlick.Menu_list;

import java.util.ArrayList;

public class DBHelp extends SQLiteOpenHelper {

    public static final String TableName = "TORDER_TEST";
    public static final String DBName = "TORDER_TEST.db";
    private static final int DB_VER = 1;

    public static final String CategoryId = "categoryId";
    public static  final String CategoryName = "categoryName" ;
    public static final String ItemId = "itemId";
    public static final String ItemName = "itemName";
    public static final String ItemPrice = "itemPrice";
    public static final String ItemSoldOutFlag = "itemSoldOutFlag";
    public static final String ItemImageUrl = "itemImageUrl";
    public static final String DB_create = "CREATE TABLE if not exists " +TableName +
            " ( " + CategoryId + " INTEGER, "
            + CategoryName +" TEXT , "+
            ItemId + " INTEGER UNIQUE , "+
            ItemName + " TEXT , "
            + ItemPrice +" INTEGER DEFAULT 0 , "
            +ItemSoldOutFlag +" TEXT DEFAULT 'false' , "
            + ItemImageUrl + " TEXT );";

    public static final String DB_create1 = "CREATE TABLE if not exists " +TableName +
            " ( " + CategoryId + " INTEGER NOT NULL, "
            + CategoryName +" TEXT NOT NULL, "+
            ItemId + " INTEGER NOT NULL UNIQUE, "+
            ItemName + " TEXT NOT NULL, "
            + ItemPrice +" INTEGER NOT NULL DEFAULT 0, "
            +ItemSoldOutFlag +" TEXT NOT NULL DEFAULT 'false', "
            + ItemImageUrl + " TEXT);";

    public SQLiteDatabase mDB;
    public SQLiteDatabase dbR = this.getReadableDatabase();
    public SQLiteDatabase dbW = this.getWritableDatabase();


    public DBHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TableName );
        onCreate(db);
    }
    //menulist.get(dbinsert).getCategoryId(), menulist.get(dbinsert).getCategoryName(), menulist.get(dbinsert).getItemId(), menulist.get(dbinsert).getItemName(), menulist.get(dbinsert).getItemPrice(), menulist.get(dbinsert).getItemSoldOutFlag(), menulist.get(dbinsert).getItemImageUrl()
    public void Menulist_inssert(int  Categoryid_in , String CategoryName_in, int ItemId_in, String ItemName_in, int ItemPrice_in, String ItemSoldOutFlag_in, String ItemImageUrl_in){
        ContentValues contentValues = new ContentValues();
        contentValues.put( "categoryId", Categoryid_in );
        contentValues.put( "categoryName", CategoryName_in );
        contentValues.put( "itemId", ItemId_in );
        contentValues.put( "itemName", ItemName_in );
        contentValues.put( "itemPrice", ItemPrice_in );
        contentValues.put( "itemSoldOutFlag", ItemSoldOutFlag_in );// sqlite 에서는 bool 값이 없으니 텍스트로
        contentValues.put( "itemImageUrl", ItemImageUrl_in );
        dbW.insert(TableName,null, contentValues);

    }

    public ArrayList<String> CategoryName(){
        ArrayList<String> Categruylist = new ArrayList(  );
        Cursor res = dbR.rawQuery( "select DISTINCT "+ CategoryId+", " + CategoryName +" from " + TableName +" ORDER BY categoryId ASC;",null );
        // 지금은 전체 메뉴에 대한 검색 기능 추가가 없으므로 pass
        while (res.moveToNext() ){
            Categruylist.add(res.getString( res.getColumnIndex( CategoryName ) ));
        }
        return Categruylist;
    }

    public ArrayList<Menu_list> Menulist_select(int categoryId){
        ArrayList<Menu_list> menulist = new ArrayList(  );
        int COl_no = categoryId+1;
        Boolean flag = false;
        Cursor res = dbR.rawQuery( "select * from " + TableName + " WHERE categoryId = " + COl_no + ";",null );
        while (res.moveToNext() ){
            //HashMap<String,String> item_list = new HashMap<>();
            Menu_list item_list = new Menu_list();
            item_list.setItemId(res.getInt( res.getColumnIndex( ItemId) ));
            item_list.setItemName(res.getString( res.getColumnIndex( ItemName) ));
            item_list.setItemPrice(res.getInt( res.getColumnIndex( ItemPrice) ));
            item_list.setItemSoldOutFlag( res.getString(res.getColumnIndex(ItemSoldOutFlag)));
            item_list.setItemImageUrl(res.getString( res.getColumnIndex( ItemImageUrl) ));
            menulist.add(item_list);
        }

        return menulist;
    }
}