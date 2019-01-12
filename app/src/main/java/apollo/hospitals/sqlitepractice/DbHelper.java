package apollo.hospitals.sqlitepractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper
{
    SQLiteDatabase sqLiteDatabase;

    public DbHelper(Context context )
    {
        super(context, "MY_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        sqLiteDatabase  = db;
        try {
            db.execSQL("CREATE table IF NOT exists TABLE_NAME(U_ID integer primary KEY autoincrement, USER_NAME VARCHAR(30),PASS_WORD VARCHAR(10));");
        }
        catch (Exception e)
        {
            Log.e("ExceptionIs",""+e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
