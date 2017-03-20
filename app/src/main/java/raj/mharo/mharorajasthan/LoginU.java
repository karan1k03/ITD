package raj.mharo.mharorajasthan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginU extends AppCompatActivity {

    EditText email;
    EditText password;
    Button Login;
    String pass,username;
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
                new LoginRequest().execute();
            }
        });
    }
    public class LoginRequest extends AsyncTask<String,String,String>
    {
        private ProgressDialog pDialog;
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginU.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Logging in ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            //onDestroy();
        }
        @Override
        protected String doInBackground(String... strings)
        {
            username=email.getText().toString();
            pass=password.getText().toString();
            UserFunctions userFunction = new UserFunctions();
            JSONObject json = null;
            try {
                json = userFunction.loginUser(username,"Test1234_");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject parentObject= null;
            try {
                parentObject = new JSONObject(json.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {

                int Status=parentObject.getInt("Status");
                String ErrorMessage=parentObject.getString("ErrorMessage");
                String ExtendedErrorMessage=parentObject.getString("ExtendedErrorMessage");
                if(Status==200&&(ErrorMessage!=null&&ErrorMessage.isEmpty())&&(ExtendedErrorMessage!=null&&ExtendedErrorMessage.isEmpty()))
                {
                    session.createLoginSession(username,password);
                    return json.toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.setMessage("Loading User Space");
            pDialog.setTitle("Getting Data");
            Intent intent = new Intent(getApplicationContext(), AfterLogin.class);
            intent.putExtra("JsonResponse", s);
            startActivity(intent);

        }
    }
}
