package x.mobileapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddHobby extends AppCompatActivity {
    Button pickColor;
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

        FloatingActionButton addFab = (FloatingActionButton) findViewById(R.id.addHobby);
        FloatingActionButton mailFab = (FloatingActionButton) findViewById(R.id.mailHobby);
        mailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView listView = (ListView) findViewById(R.id.listView);
                EditText name = (EditText) findViewById(R.id.editText);
                EditText description = (EditText) findViewById(R.id.editText3);
                EditText nrHours = (EditText) findViewById(R.id.editText4);
                String parsedName = name.getText().toString();
                String parsedDescription = description.getText().toString();
                Integer parsedHours = Integer.valueOf(nrHours.getText().toString());
                MainActivity.hobbyCtrl.addHobby(parsedName, parsedDescription, parsedHours);
                Log.v("ListActivity", "" + MainActivity.hobbyCtrl.getHobbies().size());
                startActivity(Intent.createChooser(prepEmail(parsedName, parsedDescription, parsedHours), "Send Email"));
                finish();
            }
        });
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView listView = (ListView) findViewById(R.id.listView);
                EditText name = (EditText) findViewById(R.id.editText);
                EditText description = (EditText) findViewById(R.id.editText3);
                EditText nrHours = (EditText) findViewById(R.id.editText4);
                String parsedName = name.getText().toString();
                String parsedDescription = description.getText().toString();
                Integer parsedHours = Integer.valueOf(nrHours.getText().toString());
                MainActivity.hobbyCtrl.addHobby(parsedName, parsedDescription, parsedHours);
                Log.v("ListActivity", "" + MainActivity.hobbyCtrl.getHobbies().size());
                finish();
            }
        });

        pickColor = (Button) findViewById(R.id.colorPicker);
        pickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChart();
                pickColorDialog();
            }
        });
    }

        public void pickColorDialog() {
            DialogBox dbx = new DialogBox(pickColor);
            dbx.show(getFragmentManager(), "dbxshow");
        }

        public void showChart(){
            final Intent intent = new Intent(this, PieChartActivity.class);
            startActivity(intent);
        }

}
