package raj.mharo.mharorajasthan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Toll extends AppCompatActivity {

    EditText ssoid;
    EditText ln;
    EditText vehicle;
    Button changes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toll);

        ssoid= (EditText) findViewById(R.id.editText);
        ln= (EditText) findViewById(R.id.editText2);
        vehicle= (EditText) findViewById(R.id.editText3);
        changes= (Button) findViewById(R.id.button6);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
