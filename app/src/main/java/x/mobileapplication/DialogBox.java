package x.mobileapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Button;

/**
 * Created by Petean Mihai on 1/7/2018.
 */

public class DialogBox extends DialogFragment {
    private Button btn;
    public DialogBox(Button b){
        super();
        btn = b;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("PICK A COLOR!")
                .setItems(R.array.pick_color, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        btn.setBackgroundColor(Color.parseColor(getResources().getStringArray(R.array.pick_color)[which]));
                    }
                });
        return builder.create();
    }

}
