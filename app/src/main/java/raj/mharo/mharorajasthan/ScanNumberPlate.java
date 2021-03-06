package raj.mharo.mharorajasthan;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ScanNumberPlate extends AppCompatActivity {
    public JSONObject jsonObject;
    private SurfaceView mCameraView;
    private TextView mTextView;

    private CameraSource mCameraSource;
    Button button3;
    public HttpURLConnection connection;
    public BufferedReader reader=null;
    public static JSONObject jObj = null;
    public static String json = "";
    public StringBuffer buffer=null;
    String str=" ";

    private final int REQUESTCAMERA = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_number_plate2);
        button3= (Button) findViewById(R.id.button3);
        init();


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 str= new String(mTextView.getText().toString().trim());
                new MaterialDialog.Builder(ScanNumberPlate.this)
                        .title("Search for this License number")
                        .content(str)
                        .positiveText("Accept")
                        .negativeText("Decline")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                // License plate number send to the server in the form of JSON
                                new LoginRequest().execute();

                            }
                        }) // request to server

                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }


        });
    }
    private void init() {
        mCameraView = (SurfaceView) findViewById(R.id.sv_surface);
        mTextView = (TextView) findViewById(R.id.tv_result);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(this).build();

        if (!textRecognizer.isOperational()) {
            Log.d("RAVI", "NOT OPERATIONAL");
            Toast.makeText(ScanNumberPlate.this, "All dependencies not installed ", Toast.LENGTH_SHORT).show();
        } else {
            mCameraSource = new CameraSource.Builder(ScanNumberPlate.this, textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();

            mCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    try {

                        if (ActivityCompat.checkSelfPermission(ScanNumberPlate.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ScanNumberPlate.this, new String[]{Manifest.permission.CAMERA}, REQUESTCAMERA);
                            return;
                        }
                        mCameraSource.start(mCameraView.getHolder());
                    }catch(Exception e){
                        Log.d("RAVI", e.toString());
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    mCameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>(){

                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if(items.size() != 0){
                        mTextView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for(int i = 0; i < items.size(); i++){
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());

                                }
                                mTextView.setText(stringBuilder.toString());
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUESTCAMERA:
            {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    try {
                        mCameraSource.start(mCameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public class LoginRequest extends AsyncTask<String,String,String>
    {
        private ProgressDialog pDialog;
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings)
        {
            try {
                URL loginUrl=new URL("http://www.itsohkay.in/mharo_rajasthan_api/merchant_toll.php?mt_id=1&plate_number=1234567");
                connection=(HttpURLConnection)loginUrl.openConnection();
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                   connection.setRequestMethod("GET");
                connection.connect();
                /*jsonObject=new JSONObject();
                jsonObject.put("mt_id","1");
                jsonObject.put("plate_number","1234567");

                 OutputStreamWriter details=new OutputStreamWriter(connection.getOutputStream());
                details.write(jsonObject.toString());
                details.flush();*/
                InputStream stream=connection.getInputStream();
                reader=new BufferedReader(new InputStreamReader(stream));
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
            return  null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(buffer.toString().equals("1")){
                Toast toast= Toast.makeText(ScanNumberPlate.this,"Payment Successful for "+ str,Toast.LENGTH_LONG);
                toast.show();
            }

        }
    }

}
