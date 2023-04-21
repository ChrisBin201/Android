package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlite.model.Category;
import com.example.sqlite.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "nhom7.db";
    private final static int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang categories
        String sql = "create table categories (" +
                "id integer primary key autoincrement, " +
                "name text)";
        db.execSQL(sql);
        //tao items
        sql = "create table items(" +
                "id integer primary key autoincrement, " +
                "name text, " +
                "cid integer, " +
                "price real, " +
                "dateUpdate text, " +
                "foreign key(cid) references categories(id) )";
        db.execSQL(sql);
        //cac nang nua neu co

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    insert categories
    public void insertCate(Category c){
        String sql = "insert into categories(name) " +
                "values(?)";
        String[] args = {c.getName()};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
        st.close();
    }
//    insert items
    public long insertItem(Item item){
        ContentValues values = new ContentValues();
        values.put("name",item.getName());
        values.put("cid",item.getCategory().getId());
        values.put("price",item.getPrice());
        values.put("dateUpdate",item.getDateUpdate());
        SQLiteDatabase st = getWritableDatabase();
        return  st.insert("items", null, values);

    }
//    lay ca bang categories
    public List<Category> getCates() {
        List<Category> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("categories",null, null, null,
                null,null, null);
        while (rs!= null && rs.moveToNext()){
            list.add(new Category(rs.getInt(0),rs.getString(1)));
        }
        rs.close();
        return list;

    }

    public List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        String sql = "select t.id, t.name, t.price, t.dateUpdate,c.id, c.name " +
                "from categories c inner join items t on (c.id = t.cid) " +
                "order by dateUpdate desc";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.rawQuery(sql, null);
        while (rs!=null && rs.moveToNext()){
            Category c = new Category(rs.getInt(4), rs.getString(5));
            Item t = new Item(rs.getInt(0), rs.getString(1),
                    rs.getDouble(2), rs.getString(3),c);
            list.add(t);
        }
        rs.close();
        return list;
    }
    public Category getCategoryById (int id) {
        String where = "id=?";
        String [] agrs ={Integer.toString(id)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("categories",null,where,agrs,null,null,null);
        if(rs!=null && rs.moveToNext())
            return new Category(rs.getInt(0),rs.getString(1));
        return null;
    }
    public Item getItemById (int id) {

        String where = "id=?";
        String [] agrs ={Integer.toString(id)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",null,where,agrs,null,null,null);
        if(rs!=null && rs.moveToNext())
            return new Item(rs.getInt(0),rs.getString(1),rs.getDouble(3),
                    rs.getString(4), new Category(rs.getInt(2),""));
        return null;
    }
    //update
    public int update(Item item){
        ContentValues values = new ContentValues();
        values.put("name",item.getName());
        values.put("cid",item.getCategory().getId());
        values.put("price",item.getPrice());
        values.put("dateUpdate",item.getDateUpdate());
        String where = "id=?";
        String[] agrs = {Integer.toString(item.getId())};
        SQLiteDatabase st = getWritableDatabase();
        return st.update("items",values,where,agrs);
    }
    //delete
    public void delete(int[] id) {
//        String where = "id=?";
//        String[] agrs = {Integer.toString(id)};
//        SQLiteDatabase st = getWritableDatabase();
//        return st.delete("items",where,agrs);
        String sql = "delete from items where id in ( ";
        for(int i=0;i<id.length-1;i++){
            sql+=i+", ";
        }
        sql+=id[id.length-1] +")";
//        String[] agrs = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,null);
    }
    //tim kiem by key
    public List<Item> searchByKey(String key) {
        List<Item> list = new ArrayList<>();
        String sql = "select t.id, t.name, t.price, t.dateUpdate,c.id, c.name " +
                "from categories c inner join items t on (c.id = t.cid) " +
                "where t.name like ? or c.name like ? order by dateUpdate desc";
        String[] args = {"%"+key+"%","%"+key+"%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.rawQuery(sql, args);
        while (rs!=null && rs.moveToNext()){
            Category c = new Category(rs.getInt(4), rs.getString(5));
            Item t = new Item(rs.getInt(0), rs.getString(1),
                    rs.getDouble(2), rs.getString(3),c);
            list.add(t);
        }
        rs.close();
        return list;
    }

    //tim kiem gia tri so

    //tim kiem the from.. to...
    public List<Item> searchByPriceFromTo (double from, double to) {
        List<Item> list = new ArrayList<>();
        String where = "between ? and ?";
        String [] agrs ={Double.toString(from),Double.toString(to)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",null,where,agrs,null,null,null);
        while (rs!=null && rs.moveToNext()) {
            list.add(new Item(rs.getInt(0), rs.getString(1), rs.getDouble(3),
                    rs.getString(4), new Category(rs.getInt(2), "")));
        }
        return list;
    }
    //thong ke

}
