package raj.mharo.mharorajasthan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeU extends AppCompatActivity {

    Button TOLL;
    Button BILL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TOLL= (Button) findViewById(R.id.button4);
        BILL= (Button) findViewById(R.id.button5);

        TOLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),
                        Toll.class);
                startActivity(i);
                finish();
            }
        });
        BILL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
