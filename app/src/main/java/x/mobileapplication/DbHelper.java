package x.mobileapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Petean Mihai on 1/7/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String dName = "hobby.db";
    public static final int dVer = 2;
    public static final String table = "Hobby";

    public DbHelper(Context context) {
        super(context, dName, null, dVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS Hobby(hid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, desc VARCHAR, hours INTEGER, type VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table);
        onCreate(db);
    }

    public void saveData(ArrayList<Hobby> list, Context context){
        DbHelper dbh = new DbHelper(context);
        SQLiteDatabase db =  dbh.getWritableDatabase();
        db.execSQL("DELETE FROM Hobby");
        for(int i = 0; i < list.size(); i ++)
            db.execSQL("INSERT INTO Hobby VALUES(" + i + ",'" + list.get(i).getName() + "','" + list.get(i).getDescription() + "'," + list.get(i).getHoursInvested() + ",'TEST');");

    }

    public ArrayList<Hobby> loadData(Context context){
        ArrayList<Hobby> result = new ArrayList<>();
        //db setup
        DbHelper dbh = new DbHelper(context);
        SQLiteDatabase db =  dbh.getReadableDatabase();

        //get data
        Cursor resultSet = db.rawQuery("SELECT * FROM Hobby",null);
        resultSet.moveToFirst();
        while(!resultSet.isNull(0)) {
            //make new hobby and add it
            String name = resultSet.getString(1);
            String desc = resultSet.getString(2);
            int hours = resultSet.getInt(3);
            result.add(new Hobby(hours, name, desc));
            if(resultSet.isLast())
                break;
            else
                resultSet.moveToNext();
        }
        resultSet.close();
        return result;
    }

}