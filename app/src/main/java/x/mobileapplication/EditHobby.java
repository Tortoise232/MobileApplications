package x.mobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class EditHobby extends AppCompatActivity {
    Button pickColor;
    Hobby theHobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hobby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.addHobby);

        //get clicked hobby
        String hobbyStr = (String) this.getIntent().getExtras().get("hobbyStr");
        Hobby editedHobby = new Hobby(hobbyStr);
        EditText name = (EditText) findViewById(R.id.editText);
        EditText description = (EditText) findViewById(R.id.editText3);
        EditText nrHours = (EditText) findViewById(R.id.editText4);
        name.setText(editedHobby.getName());
        description.setText(editedHobby.getDescription());
        nrHours.setText(editedHobby.getHoursInvested());
        ArrayList<Hobby> hobbies = MainActivity.hobbyCtrl.repo.getHobbies();
        for(Hobby h : hobbies){
            if(h.toString() == editedHobby.getName())
                editedHobby = h;
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("ListActivity", "" + MainActivity.hobbyCtrl.getHobbies().size());
                finish();
            }
        });

        pickColor = (Button) findViewById(R.id.colorPicker);
        pickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickColorDialog();
            }
        });
    }
    public void pickColorDialog() {
        DialogBox dbx = new DialogBox(pickColor);
        dbx.show(getFragmentManager(), "dbxshow");
    }

    public void update(){

    }

}