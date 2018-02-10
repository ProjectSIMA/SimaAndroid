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

            public void onClick(View arg0) {

                EditText edit_fieldname = findViewById(R.id.edit_fieldname);
                Bundle bundle = new Bundle();
                bundle.putString("fieldname", edit_fieldname.getText().toString());
                LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);
                String switchname = edit_fieldname.getText().toString();
                Switch stcTag = new Switch(arg0.getContext());
                stcTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                stcTag.setText(switchname);
                layout.addView(stcTag);
                // aggiungere lo switch(Da vedere come fare ancora)
            }
        });
    }
}

