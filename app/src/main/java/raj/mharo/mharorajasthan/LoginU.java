package raj.mharo.mharorajasthan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginU extends AppCompatActivity {

    EditText email;
    EditText password;
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        email= (EditText) findViewById(R.id.input_email);
        password= (EditText) findViewById(R.id.input_password);
        Login= (Button) findViewById(R.id.btn_login);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        welcomeU.class);
                startActivity(i);
                finish();
            }
        });
    }
}
