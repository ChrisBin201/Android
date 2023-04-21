package com.example.ontapth.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ontapth.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "TH.db";
    private final static int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang tasks
        String sql = "create table tasks (" +
                "id integer primary key autoincrement, " +
                "name text, description text, date text, status text, collab integer)";
        db.execSQL(sql);
        //cac nang nua neu co

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    insert categories
    public void insertTaskWithQuery(Task t){
        String sql = "insert into tasks(name, description, date, status, collab) " +
                "values(?,?,?,?,?)";
        String[] args = {t.getName(), t.getDescription(), t.getDate(), t.getStatus(), t.isCollab() ? "1" : "0"};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
        st.close();
    }
//    insert items
    public long insertTask(Task task){
        ContentValues values = new ContentValues();
        values.put("name",task.getName());
        values.put("description",task.getDescription());


        try {
            String originalDateString = task.getDate();
            SimpleDateFormat originalDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date originalDate = null;
            originalDate = originalDateFormat.parse(originalDateString);
            SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String targetDateString = targetDateFormat.format(originalDate);

            values.put("date",targetDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        values.put("status",task.getStatus());
        values.put("collab",task.isCollab());
        SQLiteDatabase st = getWritableDatabase();
        return  st.insert("tasks", null, values);

    }
//    lay ca bang tasks
    public List<Task> getAllTasks() {
        List<Task> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("tasks",null, null, null,
                null,null, null);
        while (rs!= null && rs.moveToNext()){
            list.add(new Task(rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getInt(5) == 0? false : true));
        }
        rs.close();
        return list;

    }

//    public List<Item> getItems() {
//        List<Item> list = new ArrayList<>();
//        String sql = "select t.id, t.name, t.price, t.dateUpdate,c.id, c.name " +
//                "from categories c inner join items t on (c.id = t.cid) " +
//                "order by dateUpdate desc";
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.rawQuery(sql, null);
//        while (rs!=null && rs.moveToNext()){
//            Category c = new Category(rs.getInt(4), rs.getString(5));
//            Item t = new Item(rs.getInt(0), rs.getString(1),
//                    rs.getDouble(2), rs.getString(3),c);
//            list.add(t);
//        }
//        rs.close();
//        return list;
//    }
//    public Category getCategoryById (int id) {
//        String where = "id=?";
//        String [] agrs ={Integer.toString(id)};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("categories",null,where,agrs,null,null,null);
//        if(rs!=null && rs.moveToNext())
//            return new Category(rs.getInt(0),rs.getString(1));
//        return null;
//    }
//    public Item getItemById (int id) {
//
//        String where = "id=?";
//        String [] agrs ={Integer.toString(id)};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items",null,where,agrs,null,null,null);
//        if(rs!=null && rs.moveToNext())
//            return new Item(rs.getInt(0),rs.getString(1),rs.getDouble(3),
//                    rs.getString(4), new Category(rs.getInt(2),""));
//        return null;
//    }
    //update
    public int update(Task task){
        ContentValues values = new ContentValues();
        values.put("name",task.getName());
        values.put("description",task.getDescription());
        values.put("date",task.getDate());
        values.put("status",task.getStatus());
        values.put("collab",task.isCollab());
        String where = "id=?";
        String[] agrs = {Integer.toString(task.getId())};
        SQLiteDatabase st = getWritableDatabase();
        return st.update("tasks",values,where,agrs);
    }
    //delete
    public int delete(int id) {
        String where = "id=?";
        String[] agrs = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("tasks",where,agrs);
//        String sql = "delete from tasks where id in ( ";
//        for(int i=0;i<id.length-1;i++){
//            sql+=i+", ";
//        }
//        sql+=id[id.length-1] +")";
////        String[] agrs = {Integer.toString(id)};
//        SQLiteDatabase st = getWritableDatabase();
//        st.execSQL(sql,null);
    }
    //tim kiem by key
//    public List<Item> searchByKey(String key) {
//        List<Item> list = new ArrayList<>();
//        String sql = "select t.id, t.name, t.price, t.dateUpdate,c.id, c.name " +
//                "from categories c inner join items t on (c.id = t.cid) " +
//                "where t.name like ? or c.name like ? order by dateUpdate desc";
//        String[] args = {"%"+key+"%","%"+key+"%"};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.rawQuery(sql, args);
//        while (rs!=null && rs.moveToNext()){
//            Category c = new Category(rs.getInt(4), rs.getString(5));
//            Item t = new Item(rs.getInt(0), rs.getString(1),
//                    rs.getDouble(2), rs.getString(3),c);
//            list.add(t);
//        }
//        rs.close();
//        return list;
//    }

    public List<Task> searchByNameOrDes(String key) {
        List<Task> list = new ArrayList<>();
        String whereClause = "name like ? or description like ?";
        String[] whereArgs = {"%" + key + "%", "%" + key + "%"};
        String orderBy = "date(date)";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor rs = sqLiteDatabase.query("tasks",
                null, whereClause, whereArgs,
                null, null, orderBy);
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String des = rs.getString(2);
            String date = rs.getString(3);
            String status = rs.getString(4);
            boolean collab = rs.getInt(5) ==0 ? false : true;
            list.add(new Task(id,name,des,date,status, collab));
        }
        return list;
    }

    //tim kiem gia tri so

    //tim kiem the from.. to...
//    public List<Item> searchByPriceFromTo (double from, double to) {
//        List<Item> list = new ArrayList<>();
//        String where = "between ? and ?";
//        String [] agrs ={Double.toString(from),Double.toString(to)};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items",null,where,agrs,null,null,null);
//        while (rs!=null && rs.moveToNext()) {
//            list.add(new Item(rs.getInt(0), rs.getString(1), rs.getDouble(3),
//                    rs.getString(4), new Category(rs.getInt(2), "")));
//        }
//        return list;
//    }
//    public List<Item> getByDateFromTo(String from, String to) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date BETWEEN ? AND ?";
//        String[] whereArgs = {from.trim(), to.trim()};
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            String scope = rs.getString(5);
//            int rate = rs.getInt(6);
//            list.add(new Item(id, title, category, price, date, scope, rate));
//        }
//        return list;
//    }
    //thong ke

}
