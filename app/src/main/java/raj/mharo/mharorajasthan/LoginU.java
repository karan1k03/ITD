package raj.mharo.mharorajasthan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginU extends AppCompatActivity {
    JSONObject jsonObject;
    EditText email;
    EditText password;
    Button Login;
    String pass,username;
    HttpURLConnection connection;
    BufferedReader reader=null;
    static JSONObject jObj = null;
    static String json = "";
    StringBuffer buffer=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        email= (EditText) findViewById(R.id.input_email);
        password= (EditText) findViewById(R.id.input_password);
        Login= (Button) findViewById(R.id.btn_login);

        email.setText("prakash123");
        password.setText("prakash123");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginRequest1().execute();
                username=email.getText().toString();
                pass=password.getText().toString();
            }
        });
    }
    public class LoginRequest1 extends AsyncTask<String,String,String>
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

            try {
                URL loginUrl=new URL("http://www.itsohkay.in/mharo_rajasthan_api/customer_login.php?ssoid=prakash123&pass=prakash123");
                connection=(HttpURLConnection)loginUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                /*jsonObject=new JSONObject();
                jsonObject.put("ssoid",email);
                jsonObject.put("pass",pass);
                OutputStreamWriter details=new OutputStreamWriter(connection.getOutputStream());
                details.write(jsonObject.toString());
                details.flush();*/

                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                buffer=new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                json=buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(connection!=null)
                {
                    connection.disconnect();
                }
            }
            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           /* if(buffer.toString().equals()
            pDialog.setMessage("Loading User Space");
            pDialog.setTitle("Getting Data");
            Intent intent = new Intent(getApplicationContext(), welcomeU.class);
            intent.putExtra("JsonResponse", s);
            startActivity(intent);
            */
           if(buffer.toString().equals("1")){
               Intent intent = new Intent(getApplicationContext(), welcomeU.class);
               startActivity(intent);
               finish();
           }
           else
           {
               Toast toast= Toast.makeText(LoginU.this,"Wrong username or password",Toast.LENGTH_LONG);
               toast.show();
           }
        }
    }
}
