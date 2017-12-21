package x.mobileapplication;

import android.content.Intent;
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
    public Intent prepEmail(String title, String description, int nrHours){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        String[] emails = {"petean.mihai232@yahoo.com"};
        String emailTitle = "Added new hobby";
        String emailContent = "Hello, person!\n You added the hobby of:\n" + title + " [" + description + "] " + nrHours;
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailTitle);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailContent);
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
        return emailIntent;
    }
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
                String parsedName = name.getText().toString();
                String parsedDescription = description.getText().toString();
                Integer parsedHours = Integer.valueOf(nrHours.getText().toString());
                List.hobbyCtrl.addHobby(parsedName, parsedDescription, parsedHours);
                Log.v("ListActivity", "" + List.hobbyCtrl.getHobbies().size());
                startActivity(Intent.createChooser(prepEmail(parsedName, parsedDescription, parsedHours), "Send Email"));
                finish();
            }
        });
    }

}
