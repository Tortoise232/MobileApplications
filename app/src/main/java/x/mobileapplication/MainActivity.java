package x.mobileapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public DbHelper dbHelper = new DbHelper(this);
    public static HobbyController  hobbyCtrl = new HobbyController();
    public AlertDialog.Builder saveDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button saveToDB = (Button) findViewById(R.id.saveToDB);
        saveToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDB();
            }
        });

        Button listButton = (Button) findViewById(R.id.listButton);
        final Intent  listIntent = new Intent(this, List.class);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataFromDB();
                startActivity(listIntent);
            }
        });

        Button addButton = (Button) findViewById(R.id.addButton);
        final Intent  addIntent = new Intent(this, AddHobby.class);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addIntent);
            }
        });

        Button timeButton = (Button) findViewById(R.id.timeHobby);
        final Intent  editIntent = new Intent(this, EditHobby.class);
        timeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(editIntent);
                }
            });

        loadDataFromDB();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        saveDataToDB();
    }

    public void saveDataToDB(){
        System.out.println("SAVE DATA START");

        dbHelper.saveData(hobbyCtrl.repo.getHobbies(), this);
        System.out.println("SAVE DATA DONE");
    }

    public void loadDataFromDB(){
        System.out.println("LOAD DATA START");
        try {
            hobbyCtrl.repo.localHobbies = dbHelper.loadData(this);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("LOAD DATA DONE:" + hobbyCtrl.repo.getHobbies().size());
    }

    public void initSaveDialog(){
        saveDialog =  new AlertDialog.Builder(this);

    }
}
