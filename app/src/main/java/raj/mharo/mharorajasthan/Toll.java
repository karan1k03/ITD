package raj.mharo.mharorajasthan;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
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
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(Toll.this)
                                .setSmallIcon(R.drawable.toll)
                                .setContentTitle("Toll booth ahead! 2 kms left")
                                .setContentText("Click to pay");

                Intent resultIntent = new Intent(Toll.this, Payment.class);

// Because clicking the notification opens a new ("special") activity, there's
// no need to create an artificial back stack.
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                Toll.this,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

//ts an ID for the notification
                int mNotificationId = 001;
// Gets an instance of the NotificationManager service
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
        });

    }


}
