package x.mobileapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class AddHobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hobby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addHobby);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView listView = (ListView) findViewById(R.id.listView);
                EditText name = (EditText) findViewById(R.id.editText);
                EditText description = (EditText) findViewById(R.id.editText3);
                EditText nrHours = (EditText) findViewById(R.id.editText4);
                List.hobbyCtrl.addHobby(name.getText().toString(),
                                        description.getText().toString(),
                                        Integer.valueOf(nrHours.getText().toString()));
                Log.v("ListActivity", "" + List.hobbyCtrl.getHobbies().size());
                finish();
            }
        });
    }

}
