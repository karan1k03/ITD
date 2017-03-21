package raj.mharo.mharorajasthan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Payment extends AppCompatActivity {
    Intent intent;
    Button wallet,card;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        wallet= (Button) findViewById(R.id.button7);
        card= (Button) findViewById(R.id.button8);



        intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.itsohkay.in/mharo_rajasthan_api/api_test.php?username=Karan&amount=45"));

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  startActivity(intent);
            }
        });
    }
}
