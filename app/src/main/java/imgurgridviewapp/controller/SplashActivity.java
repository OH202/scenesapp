package imgurgridviewapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.owner.scenesapp.R;
import com.example.owner.scenesapp.NavDrawerActivity;

/**
 * Created by Owner on 12/14/2014.
 */
public class SplashActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openMainActivity = new Intent(SplashActivity.this, NavDrawerActivity.class);
                startActivity(openMainActivity);
                finish();

            }
        }, 5000);
    }
}
