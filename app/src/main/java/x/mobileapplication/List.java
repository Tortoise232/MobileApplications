package x.mobileapplication;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class List extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ListActivity", "Creating List activity");
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ListView hobbyView = (ListView) findViewById(R.id.listView);
        Log.e("ListActivity", "got ListView");
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.hobbyCtrl.getHobbies());
        Log.e("ListActivity", "adapter set up");

        hobbyView.setAdapter(listAdapter);
        final Intent intent = new Intent(this,AddHobby.class);
        hobbyView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                MainActivity.hobbyCtrl.repo.removeHobby(new Hobby(item));
                startActivity(intent);
            }

        });
        hobbyView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                MainActivity.hobbyCtrl.repo.removeHobby(new Hobby(item));
                deleteDialog();
                return true;
            }

        });


    }

    public void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Hobby was deleted")
                .setTitle("Notice");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ListView hobbyView = (ListView) findViewById(R.id.listView);
        Log.e("ListActivity", "got ListView");
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.hobbyCtrl.getHobbies());
        Log.e("ListActivity", "adapter set up");

        hobbyView.setAdapter(listAdapter);
    }

}
