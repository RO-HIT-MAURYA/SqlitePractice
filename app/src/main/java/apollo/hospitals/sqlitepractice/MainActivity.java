package apollo.hospitals.sqlitepractice;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.view).setOnClickListener(this);
        dbHelper = new DbHelper(this);

    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() ==R.id.add)
        {
            String name = ((EditText)findViewById(R.id.name)).getText().toString().trim();
            String pass = ((EditText)findViewById(R.id.pass)).getText().toString().trim();

            long id = enterData(name,pass);
            if (id<=0)
                Toast.makeText(MainActivity.this,"Data is not inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Data is  inserted",Toast.LENGTH_LONG).show();

        }
        else
        {
            String string= showData();
            Toast.makeText(MainActivity.this,string,Toast.LENGTH_LONG).show();
        }

    }

    private String showData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {"U_ID","USER_NAME","PASS_WORD"};

        Cursor cursor =db.query("TABLE_NAME",columns,null,null,null,null,null);

        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex("U_ID"));
            String name =cursor.getString(cursor.getColumnIndex("USER_NAME"));
            String  password =cursor.getString(cursor.getColumnIndex("PASS_WORD"));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        sqLiteDatabase.close();

        return buffer.toString();
    }

    private long enterData(String name, String pass)
    {
        long id = 0;
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_NAME", name);
        contentValues.put("PASS_WORD", pass);//
        try {
            id = sqLiteDatabase.insert("TABLE_NAME", null , contentValues);
            sqLiteDatabase.close();
        }
        catch (Exception e)
        {
            Log.e("exceptionIs",""+e);
        }
        return id;
    }
}
