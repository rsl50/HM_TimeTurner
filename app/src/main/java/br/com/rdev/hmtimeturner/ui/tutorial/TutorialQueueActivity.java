package br.com.rdev.hmtimeturner.ui.tutorial;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.rdev.hmtimeturner.R;
import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.listener.DismissListener;

public class TutorialQueueActivity extends AppCompatActivity {

    private FancyShowCaseQueue queue;
    private OnClickListener mClickListener;
    private DismissListener dismissListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        initListeners();


    }

    private void initListeners() {
        mClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TUTORIAL", "onClick: ");
                queue.getCurrent().hide();
            }
        };

        dismissListener = new DismissListener() {
            @Override
            public void onDismiss(String s) {
                Log.v("TUTORIAL","dismiss");
            }

            @Override
            public void onSkipped(String s) {
                Log.v("TUTORIAL","skipped");
            }
        };

    }

    private FancyShowCaseView createTutorialScreen(String title, View view) {

        FancyShowCaseView builder;

        if (view != null) {
            builder = new FancyShowCaseView.Builder(this)
                    .title(title)
                    .titleStyle(R.style.TutorialTextStyle, Gravity.CENTER)
                    .titleGravity(Gravity.CENTER)
                    .focusOn(view)
                    .build();
        } else {
            builder = new FancyShowCaseView.Builder(this)
                    .title(title)
                    .titleGravity(Gravity.CENTER)
                    .titleStyle(R.style.TutorialTextStyle, Gravity.CENTER)
                    .build();
        }

        return builder;
    }
}
