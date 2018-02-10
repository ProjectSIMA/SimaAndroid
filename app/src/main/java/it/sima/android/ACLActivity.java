package it.sima.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

/**
 * Created by user on 05/02/2018.
 */

public class ACLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acl);

        Button addswitch = findViewById(R.id.button1); //dato nome a pulsante
        addswitch.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                        EditText nameText = findViewById(R.id.edit_fieldname);
                        /*Bundle bundle = new Bundle();
                        bundle.putString("fieldname", edit_fieldname.getText().toString());*/
                        LinearLayout layout = findViewById(R.id.switch_container);
                        Switch layoutSwitch = new Switch(v.getContext());
                        layoutSwitch.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        layoutSwitch.setText(nameText.getText());
                        layout.addView(layoutSwitch);
                    // aggiunto lo switch e nominato
                }
        });
    }
}

