package daita.daita;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @reference
 * https://www.youtube.com/watch?v=kp_Msx1sPs8
 * numerous changes such as intent getting duration from a different activity
 * @author Sean Barrett x15561177

 */

public class SplashScreen extends AppCompatActivity {

    private int duration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent in = getIntent();
        duration = in.getIntExtra("duration",0);
        if(duration==0)duration=1500;
        Handler splashHandler = new Handler();
        splashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },duration);

    }
}
