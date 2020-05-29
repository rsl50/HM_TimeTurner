package br.com.rdev.hmtimeturner.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.rdev.hmtimeturner.R;

public class MainActivity extends AppCompatActivity {

    private Button btnMagick;
    private EditText[][] classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMagick = findViewById(R.id.button_run);
        final EditText[][] classes = new EditText[4][4];

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                String viewclass = "class_" + (row + 1) + (column + 1);
                System.out.println(viewclass);
                int resIDclass = getResources().getIdentifier(viewclass, "id", getPackageName());
                classes[row][column] = ((EditText) findViewById(resIDclass));
            }
        }


        btnMagick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "valor:"+classes[3][2].getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
