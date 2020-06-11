package br.com.rdev.hmtimeturner.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import br.com.rdev.hmtimeturner.R;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import static android.content.Context.MODE_PRIVATE;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "HM Full Marks Time Turner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setTitle(TITULO_APPBAR);

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);

        if (preferences.contains("ja_abriu_app")) {
            mostraListaPacotes();
        } else {
            adicionarPreferenceJaAbriu(preferences);
            mostraSplashScreen();
        }
    }

    private void adicionarPreferenceJaAbriu(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ja_abriu_app", true);
        editor.commit();
    }

    private void mostraSplashScreen() {
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostraListaPacotes();
            }
        }, 2000);
    }

    private void mostraListaPacotes() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
