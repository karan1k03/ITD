package raj.mharo.mharorajasthan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginM extends AppCompatActivity {

    EditText emailm;
    EditText passwordm;
    Button Loginm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_m);

     emailm= (EditText) findViewById(R.id.input_email2);
        passwordm= (EditText) findViewById(R.id.input_password2);
        Loginm= (Button) findViewById(R.id.btn_login2);

        Loginm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ScanNumberPlate.class);
                startActivity(i);
                finish();
            }
        });

    }
}
