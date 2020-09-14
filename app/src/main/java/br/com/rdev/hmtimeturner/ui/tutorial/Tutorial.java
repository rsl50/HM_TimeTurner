package br.com.rdev.hmtimeturner.ui.tutorial;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.rdev.hmtimeturner.R;
import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.listener.OnCompleteListener;
import me.toptas.fancyshowcase.listener.OnViewInflateListener;

import static br.com.rdev.hmtimeturner.util.ResourceUtil.devolveDrawable;

public class Tutorial  {

    private List<View> views;
    private Activity activity;
    private Context context;
    private View.OnClickListener mClickListener;
    private FancyShowCaseQueue queue;

    private final int SCREEN_LAYOUT_CENTRE = R.layout.tutorial_screen_1;
    private final int SCREEN_LAYOUT_TOP = R.layout.tutorial_screen_2;

    public Tutorial(Activity activity, List<View> views) {
        this.activity = activity;
        this.views = views;

        context = this.activity.getBaseContext();

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TUTORIAL", "onClick: ");
                queue.getCurrent().hide();
            }
        };
    }

    private FancyShowCaseView createTutorialScreen(final int screenLayout, final String title, final String description, final Drawable drawable, View targetView, Double focusCircleRadiusFactor, final String buttonText  ) {

        final FancyShowCaseView.Builder builder;

        builder = new FancyShowCaseView.Builder(this.activity)
                .title(title)
                .titleStyle(R.style.TutorialTextStyle, Gravity.CENTER)
                .focusCircleRadiusFactor(focusCircleRadiusFactor)
                .titleGravity(Gravity.CENTER);

        if (targetView != null) {
            builder.focusOn(targetView);
        }

        builder.customView(screenLayout, new OnViewInflateListener() {
            @Override
            public void onViewInflated(View view) {
                TextView tutorialTitle = view.findViewById(R.id.tutorial_title);
                tutorialTitle.setText(title);

                TextView tutorialDescription = view.findViewById(R.id.tutorial_description);
                tutorialDescription.setText(description);

                ImageView tutorialImage = view.findViewById(R.id.tutorial_imageview);
                if (drawable != null) {
                    tutorialImage.setImageDrawable(drawable);
                } else {
                    tutorialImage.setVisibility(View.INVISIBLE);
                }

                Button tutorialActionButton = view.findViewById(R.id.tutorial_button_action);
                tutorialActionButton.setText(buttonText);
                tutorialActionButton.setOnClickListener(mClickListener);
            }
        });

        FancyShowCaseView tutorialScreen = builder.build();

        return tutorialScreen;
    }

    private FancyShowCaseQueue setScreenSequence() {
        FancyShowCaseQueue queue = new FancyShowCaseQueue();

        queue.add(createTutorialScreen(SCREEN_LAYOUT_CENTRE, activity.getString(R.string.tutorial_title), activity.getString(R.string.tutorial_message_welcome), devolveDrawable(context, activity.getString(R.string.drawble_time_turner)), null, 0.0, activity.getString(R.string.button_next)));
        queue.add(createTutorialScreen(SCREEN_LAYOUT_CENTRE, activity.getString(R.string.tutorial_title), activity.getString(R.string.tutorial_message_define_hours), devolveDrawable(context, activity.getString(R.string.drawable_board_example)), null, 0.0, activity.getString(R.string.button_next)));
        queue.add(createTutorialScreen(SCREEN_LAYOUT_CENTRE, activity.getString(R.string.tutorial_title), activity.getString(R.string.tutorial_message_class_board), devolveDrawable(context, activity.getString(R.string.drawable_class_board)), views.get(0), 4.0,  activity.getString(R.string.button_next)));
        queue.add(createTutorialScreen(SCREEN_LAYOUT_CENTRE, activity.getString(R.string.tutorial_title), activity.getString(R.string.tutorial_message_nonclass_board), devolveDrawable(context, activity.getString(R.string.drawable_nonclass_board)), views.get(1), 4.0, activity.getString(R.string.button_next)));
        queue.add(createTutorialScreen(SCREEN_LAYOUT_TOP, activity.getString(R.string.tutorial_title), activity.getString(R.string.tutorial_message_points_board), devolveDrawable(context, activity.getString(R.string.drawable_points_board)), views.get(2), 4.0, activity.getString(R.string.button_next)));
        queue.add(createTutorialScreen(SCREEN_LAYOUT_TOP, activity.getString(R.string.tutorial_title), "Escolha o padrão especial mostrado no jogo", null, views.get(3), 1.0, activity.getString(R.string.button_next)));
        queue.add(createTutorialScreen(SCREEN_LAYOUT_CENTRE, activity.getString(R.string.tutorial_title), "Preencha o valor de pontos do padrão especial mostrado no jogo", null, views.get(4), 2.0, activity.getString(R.string.button_next)));
        queue.add(createTutorialScreen(SCREEN_LAYOUT_CENTRE, activity.getString(R.string.tutorial_title), "Pressione o botão para acionar o feitiço!", null, views.get(5), 0.7, activity.getString(R.string.button_next)));
        queue.add(createTutorialScreen(SCREEN_LAYOUT_CENTRE, activity.getString(R.string.tutorial_title), "Será exibida a tela com resultados ordenados por Pontos/Hora\nAs quatro posições em destaque mostram os padrões que darão a você mais Pontos/Hora\nVocê verá a melhor pontuação de cada padrão ordenada para escolher a sua estratégia", devolveDrawable(context, activity.getString(R.string.drawable_results)), null, 0.0, "Fechar"));

        return queue;
    }

    public void runTutorial() {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        queue = setScreenSequence();

        queue.setCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }
        });

        queue.show();
    }
}
