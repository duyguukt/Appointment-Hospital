package tr.com.example.dd201635014;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE = "hospital";
    public static final String TABLE = "users";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "FIRSTNAME";
    public static final String COL_3 = "LASTNAME";
    public static final String COL_4 = "USERNAME";
    public static final String COL_5 = "PASSWORD";
    public static final String COL_6 = "GENDER";
    public static final String COL_7 = "CITY";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE + "(" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT, " +
                COL_5 + " TEXT, " +
                COL_6 + " TEXT, " +
                COL_7 + " TEXT)";
        Log.i("sql", sql);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String FirstName, String LastName, String Username, String Password,
                              String Gender, String City ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, FirstName);
        values.put(COL_3, LastName);
        values.put(COL_4, Username);
        values.put(COL_5, Password);
        values.put(COL_6, Gender);
        values.put(COL_7, City);


        return (db.insert(TABLE, null, values) != -1);

    }

    public boolean duplicate_user(String username) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(
                TABLE,
                new String[]{COL_4},
                "Username=?",
                new String[]{username},
                null,
                null,
                null
        );
        return ((cursor != null && cursor.getCount() > 0) ? true : false);
    }

    public boolean checkUser(String Username, String Password) {

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[]{COL_4, COL_5},
                "Username=? and Password=?",
                new String[]{Username, Password},
                null,
                null,
                null
        );
        return ((cursor != null && cursor.getCount() > 0) ? true : false);
    }

    public ArrayList<String> getUserList() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(
                TABLE,
                new String[]{COL_4},
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<String> list = new ArrayList<String>();

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Cursor getSingleUserDetail(String userdata) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(
                TABLE,
                null,
                "Username=?",
                new String[]{userdata},
                null,
                null,
                null
        );
        return cursor;
    }

}
