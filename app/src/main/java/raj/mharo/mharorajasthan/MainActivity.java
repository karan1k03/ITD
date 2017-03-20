package raj.mharo.mharorajasthan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button userLogin;
    Button merchantLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLogin= (Button) findViewById(R.id.button);
        merchantLogin= (Button) findViewById(R.id.button2);

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginU.class);
                startActivity(i);
                finish();
            }
        });

        merchantLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginM.class);
                startActivity(i);
                finish();
            }
        });
    }



}
