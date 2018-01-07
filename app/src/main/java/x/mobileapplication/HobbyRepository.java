package x.mobileapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Petean Mihai on 1/7/2018.
 */

public class HobbyRepository {
    SQLiteDatabase db;
    public ArrayList<Hobby> localHobbies = new ArrayList<>();
    public void isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Log.d("APPSTORAGE", "isExternalStorageWritable: true");
        }
        Log.d("APPSTORAGE", "isExternalStorageWritable: false");

    }

    /* Checks if external storage is available to at least read */
    public void isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Log.d("APPSTORAGE", "isExternalStorageReadable: true");
        }
        Log.d("APPSTORAGE", "isExternalStorageReadable: false");
    }



    public HobbyRepository() throws IOException {
        //db = SQLiteDatabase.openOrCreateDatabase(filePath,null);
        isExternalStorageReadable();
        isExternalStorageWritable();
        initDb();
    }

    public HobbyRepository(String filePath){
        db = SQLiteDatabase.openOrCreateDatabase(filePath,null);

        initDb();
    }

    public void initDb(){
        //db.execSQL("DROP TABLE IF EXISTS Hobby");
        //db.execSQL("CREATE TABLE IF NOT EXISTS Hobby(hid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, desc VARCHAR, hours INTEGER, type VARCHAR)");
    }

    public void addHobby(Hobby h){
        localHobbies.add(h);
        //db.execSQL("INSERT INTO Hobby VALUES('" + h.getDescription() + "'," + h.getHoursInvested() + ",'TEST');");
    }

    public ArrayList<Hobby> getHobbies(){
        return localHobbies;
        /*
        ArrayList<Hobby> result = new ArrayList<Hobby>();

        Cursor resultSet = db.rawQuery("SELECT * FROM Hobby", null);
        resultSet.moveToFirst();
        while(!resultSet.isLast()){
            String name = resultSet.getString(1);
            String desc = resultSet.getString(2);
            int hours = resultSet.getInt(3);
            String type = resultSet.getString(4);
            result.add(new Hobby(hours, name, desc));
        }
        return result;
        */
    }

    public void removeHobby(Hobby hobby) {
        for(Hobby h: localHobbies){
            if(h.getName().equals(hobby.getName())) {
                localHobbies.remove(h);
                break;
            }
        }
    }
}
