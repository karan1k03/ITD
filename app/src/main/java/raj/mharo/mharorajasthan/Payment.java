package raj.mharo.mharorajasthan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Payment extends AppCompatActivity {

    Intent intent;
    RadioButton wallet,card;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.itsohkay.in/mharo_rajasthan_api/api_test.php?username=Karan&amount=15 0"));
        wallet= (RadioButton) findViewById(R.id.radioButton);
        card= (RadioButton) findViewById(R.id.radioButton2);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  startActivity(intent);
            }
        });
    }

}
