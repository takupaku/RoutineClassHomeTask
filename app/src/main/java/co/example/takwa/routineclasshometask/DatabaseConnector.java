package co.example.takwa.routineclasshometask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseConnector extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Table_schedule";
    private static final String COL_1 = "Day";
    private static final String COL_2 = "Algorithm";
    private static final String COL_3 = "Graphics";
    private static final String COL_4 = "Compiler";
    private Context context;


    public DatabaseConnector(Context context) {
        super(context, "database.db", null, 5);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " (" + COL_1 + " text primary key, " + COL_2 + " text, " + COL_3 + " text, " + COL_4 + " text )";
        db.execSQL(sql);    //create table TableName (id integer primary key , name text, address text);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//change version if the table(column number/type of element needs to change)
//this method works if a new value of VERSION is altered/changed from constructor(version value can't be downgraded!!)
        try {  String sql = "drop table if exists " + TABLE_NAME;
             db.execSQL(sql);//dropping table each time the version upgrading...
            onCreate(db);
           // String sql = "alter table " + TABLE_NAME + " add column subject text";
           // db.execSQL(sql);  //alter table TableName add column subject text;
            Toast.makeText(context, "your table upgraded!!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "OOps!! upgrading failed!!", Toast.LENGTH_SHORT).show();


        }

    }

    public boolean insertData(String day, String algorithm, String graphics, String compiler) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, day);
        values.put(COL_2, algorithm);
        values.put(COL_3, graphics);
        values.put(COL_4, compiler);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, values);
        if (result == -1) {      //long coz, if true than this returns a long number
            return false;
        } else {
            return true;


        }
    }

    public int updateData(String day, String algorithm, String graphics, String compiler) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, algorithm);
        contentValues.put(COL_3, graphics);
        contentValues.put(COL_4, compiler);

        return sqLiteDatabase.update(TABLE_NAME, contentValues, " "+COL_1+" "+"=?", new String[]{day});

    }

    public Cursor viewData() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "select * from " + TABLE_NAME;
        //select * from TableName;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        return cursor;
    }


    public Cursor searchRow(String day) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where Day = " + day;
        //select * from TableName where id =?;
      //  Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        return sqLiteDatabase.rawQuery(sql,null);
    }

    public int deleteData(String day) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "Day =?", new String[]{day});

    }


}
