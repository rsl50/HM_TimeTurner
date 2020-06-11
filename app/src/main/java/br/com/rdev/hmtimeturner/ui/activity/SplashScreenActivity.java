package br.com.rdev.hmtimeturner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.rdev.hmtimeturner.BuildConfig;
import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.util.Preferences;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "HM Full Marks Time Turner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setTitle(TITULO_APPBAR);

        Preferences preferences = new Preferences();

        TextView labelVersion = findViewById(R.id.label_version);
        labelVersion.setText(BuildConfig.VERSION_NAME);

        if (preferences.contains("ja_abriu_app", this)) {
            showSplashScreen();
            //showMainScreen();
        } else {
            preferences.setPrefs("ja_abriu_app", true, this);
            showSplashScreen();
        }
    }

    private void showSplashScreen() {
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMainScreen();
            }
        }, 2000);
    }

    private void showMainScreen() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
