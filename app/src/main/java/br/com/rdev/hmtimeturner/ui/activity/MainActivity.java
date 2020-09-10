package br.com.rdev.hmtimeturner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.elconfidencial.bubbleshowcase.BubbleShowCase;

import java.util.ArrayList;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.model.Calculator;
import br.com.rdev.hmtimeturner.model.Pattern;
import br.com.rdev.hmtimeturner.ui.adapter.CustomSpinnerAdapter;
import br.com.rdev.hmtimeturner.util.Preferences;
//import smartdevelop.ir.eram.showcaseviewlib.GuideView;
//import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
//import smartdevelop.ir.eram.showcaseviewlib.config.Gravity;

import static br.com.rdev.hmtimeturner.model.Calculator.START_SPECIALS;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.CLASSES_TIMES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.DOUBLES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.KEY_RESULTS;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.NON_CLASSES_TIMES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.POINTS_CLASSES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SINGLES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SPECIALS;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SPECIAL_MULTIPLE;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SPECIAL_SELECTED;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.TRIPLES;
import static java.util.Locale.US;

//import com.github.amlcurran.showcaseview.ShowcaseView;
//import com.github.amlcurran.showcaseview.targets.Target;
//import com.github.amlcurran.showcaseview.targets.ViewTarget;

import com.elconfidencial.bubbleshowcase.*;

public class MainActivity extends AppCompatActivity {

    private EditText pointsSpecialPattern;
    private int specialPatternSlected;

    private EditText[][] classes;
    private EditText[][] nonClasses;
    private EditText[][] pointsForClasses;
    private Calculator calculator;
    private  Preferences preferences;

    //private ShowcaseView showcaseView;
    private int counter = 0;

    Button btnMagick;
    Button btnClearClasses;
    Button btnClearNonClasses;
    Button btnClearPoints;
    Button btnClearAll;

    public static final String TITLE_APPBAR = "HM Full Marks Time Turner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITLE_APPBAR);

        calculator = new Calculator(this);
        preferences = new Preferences();

        initializeEditTexts();
        configureButtons();
        configureSpinnerSpecial();
        setTestValues();


        BubbleShowCaseBuilder first = new BubbleShowCaseBuilder(this) //Activity instance
                .title("Título") //Any title for the bubble view
                .description("Descrição") //More detailed description
                .arrowPosition(BubbleShowCase.ArrowPosition.TOP) //You can force the position of the arrow to change the location of the bubble.
                .backgroundColor(Color.BLACK) //Bubble background color
                .textColor(Color.WHITE) //Bubble Text color
                .titleTextSize(17) //Title text size in SP (default value 16sp)
                .descriptionTextSize(15) //Subtitle text size in SP (default value 14sp)
                .image(getResources().getDrawable(R.drawable.app_icon)) //Bubble main image
                .highlightMode(BubbleShowCase.HighlightMode.VIEW_LAYOUT)
                .targetView(btnMagick);

        BubbleShowCaseBuilder second = new BubbleShowCaseBuilder(this) //Activity instance
                .title("Título") //Any title for the bubble view
                .description("Descrição") //More detailed description
                .arrowPosition(BubbleShowCase.ArrowPosition.TOP) //You can force the position of the arrow to change the location of the bubble.
                .backgroundColor(Color.BLACK) //Bubble background color
                .textColor(Color.WHITE) //Bubble Text color
                .titleTextSize(17) //Title text size in SP (default value 16sp)
                .descriptionTextSize(15) //Subtitle text size in SP (default value 14sp)
                .image(getResources().getDrawable(R.drawable.app_icon)) //Bubble main image
                .highlightMode(BubbleShowCase.HighlightMode.VIEW_LAYOUT)
                .targetView(btnClearClasses);

        BubbleShowCaseBuilder third = new BubbleShowCaseBuilder(this) //Activity instance
                .title("Título") //Any title for the bubble view
                .description("Descrição") //More detailed description
                .arrowPosition(BubbleShowCase.ArrowPosition.TOP) //You can force the position of the arrow to change the location of the bubble.
                .backgroundColor(Color.BLACK) //Bubble background color
                .textColor(Color.WHITE) //Bubble Text color
                .titleTextSize(17) //Title text size in SP (default value 16sp)
                .descriptionTextSize(15) //Subtitle text size in SP (default value 14sp)
                .image(getResources().getDrawable(R.drawable.app_icon)) //Bubble main image
                .highlightMode(BubbleShowCase.HighlightMode.VIEW_LAYOUT)
                .targetView(btnClearAll);

//        new BubbleShowCaseSequence().addShowCase(first)
//                                    .addShowCase(second)
//                                    .addShowCase(third)
//                                    .show();


//        new GuideView.Builder(this)
//                .setTitle("Tutorial")
//                .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
//                .setGravity(Gravity.auto) //optional
//                .setDismissType(DismissType.anywhere) //optional - default DismissType.targetView
//                .setTargetView(btnMagick)
//                .setCircleIndicatorSize(100)
//                .setContentSpan((Spannable) Html.fromHtml("<font color='red'>testing spannable</p>"))
//                .setContentTextSize(12)//optional
//                .setTitleTextSize(14)//optional
//                .build()
//                .show();

//        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
//        lps.setMargins(margin, margin, margin, margin);

//        showcaseView = new ShowcaseView.Builder(this)
//                //.withMaterialShowcase()
//                .withHoloShowcase()
//                //.setTarget(new ViewTarget(findViewById(R.id.class_21)))
//                .setShowcaseDrawer()
//                .setTarget(Target.NONE)
//                .setContentTitle("TUTORIAL")
//                .setContentText("Bem vindo!")
//                .setStyle(R.style.CustomShowcaseTheme2)
//                .setOnClickListener(clickFunction)
//                //.replaceEndButton(R.layout.view_custom_button)
//                .build();
//        showcaseView.setButtonText(getString(R.string.next));
//        showcaseView.setButtonPosition(lps);
    }

//    private void setAlpha(float alpha, View... views) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            for (View view : views) {
//                view.setAlpha(alpha);
//            }
//        }
//    }
//
//    private View.OnClickListener clickFunction = new OnClickClass();
//
//    private class OnClickClass implements View.OnClickListener {
//        public void onClick(View v){
//            switch (counter) {
//                case 0:
//                    showcaseView.setShowcase(new ViewTarget(btnMagick), true);
//                    break;
//
//                case 1:
//                    showcaseView.setShowcase(new ViewTarget(btnClearPoints), true);
//                    break;
//
//                case 2:
//                    showcaseView.setTarget(Target.NONE);
//                    showcaseView.setContentTitle("Check it out");
//                    showcaseView.setContentText("You don't always need a target to showcase");
//                    showcaseView.setButtonText(getString(R.string.close));
//                    setAlpha(0.4f, btnMagick, btnClearPoints, btnClearAll);
//                    break;
//
//                case 3:
//                    showcaseView.hide();
//                    setAlpha(1.0f, btnMagick, btnClearPoints, btnClearAll);
//                    break;
//            }
//            counter++;
//        }
//    }



    private void initializeEditTexts() {
        classes = new EditText[4][4];
        nonClasses = new EditText[4][4];
        pointsForClasses = new EditText[4][4];

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                String nameEditTextClasses = "class_" + (row + 1) + (column + 1);
                String nameEditTextNonClasses = "nonclass_" + (row + 1) + (column + 1);
                String nameEditTextPoints = "points_" + (row + 1) + (column + 1);

                //Log.d("initEditText", nameEditTextClasses);
                int resourceIdClass = getResources().getIdentifier(nameEditTextClasses, "id", getPackageName());
                classes[row][column] = findViewById(resourceIdClass);

                //Log.d("initEditText", nameEditTextNonClasses);
                int resourceIdNonClass = getResources().getIdentifier(nameEditTextNonClasses, "id", getPackageName());
                nonClasses[row][column] = findViewById(resourceIdNonClass);

                //Log.d("initEditText", nameEditTextPoints);
                int resourceIdPoints = getResources().getIdentifier(nameEditTextPoints, "id", getPackageName());
                pointsForClasses[row][column] = findViewById(resourceIdPoints);
            }
        }
    }

    private double getEditTextValue (int row, int column, EditText[][] editTextArray) {
        double value = 0;

        try {
            value = Double.parseDouble(editTextArray[row][column].getText().toString());
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse:" + nfe);
        }

        return value;
    }

    private void clearEditTextArray(EditText[][] editTexts){
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                editTexts[row][column].setText(null);
            }
        }
    }

    private void configureButtons() {
        btnMagick = findViewById(R.id.button_run);
        btnClearClasses = findViewById(R.id.button_clear_classes);
        btnClearNonClasses = findViewById(R.id.button_clear_nonclasses);
        btnClearPoints = findViewById(R.id.button_clear_points);
        btnClearAll = findViewById(R.id.button_clear_all);

        btnMagick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capture Special Pattern set from Interface
                captureSelectedSpecialPatternData();
                ArrayList<Pattern> resultPatternsArray = runCalculations();

                goToResults(resultPatternsArray);
            }
        });

        btnClearClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditTextArray(classes);
            }
        });

        btnClearNonClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditTextArray(nonClasses);
            }
        });

        btnClearPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditTextArray(pointsForClasses);
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditTextArray(classes);
                clearEditTextArray(nonClasses);
                clearEditTextArray(pointsForClasses);
            }
        });
    }

    private void configureSpinnerSpecial() {
        pointsSpecialPattern = findViewById(R.id.points_special_pattern);
        pointsSpecialPattern.setText(String.format(US, "%d", calculator.getSpecialPatternMultiplier()));

        String[] patternNames = getResources().getStringArray(R.array.special_patterns_names);
        int[] patternImages = {R.drawable.pattern_51, R.drawable.pattern_52, R.drawable.pattern_53, R.drawable.pattern_54, R.drawable.pattern_55};

        Spinner spinnerSpecialPattern = findViewById(R.id.spinner_special_pattern);

        CustomSpinnerAdapter customAdapter = new CustomSpinnerAdapter(getApplicationContext(), patternImages, patternNames);
        spinnerSpecialPattern.setAdapter(customAdapter);

        spinnerSpecialPattern.setSelection(calculator.getSpecialPattern() - START_SPECIALS);

        spinnerSpecialPattern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specialPatternSlected = parent.getSelectedItemPosition();
                preferences.setPrefs(SPECIAL_SELECTED, String.format(US, "%d", specialPatternSlected), MainActivity.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void captureSelectedSpecialPatternData() {
        calculator.setSelectedSpecialPattern(specialPatternSlected);
        calculator.setSelectedSpecialPatternMultiplier(Integer.parseInt(pointsSpecialPattern.getText().toString()));

        preferences.setPrefs(SPECIAL_SELECTED, String.format(US, "%d", specialPatternSlected), this);
        preferences.setPrefs(SPECIAL_MULTIPLE, pointsSpecialPattern.getText().toString(), this);
    }

    private void setBaseArraysValues(double[][] classesTimes, double[][] nonClassesTimes, double[][] pointsClasses, double[][] pointsNonClasses) {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                classesTimes[row][column] = getEditTextValue(row, column, classes);
                nonClassesTimes[row][column] = getEditTextValue(row, column, nonClasses);
                pointsClasses[row][column] = getEditTextValue(row, column, pointsForClasses);

                if (classesTimes[row][column] == 0) {
                    pointsNonClasses[row][column] = pointsClasses[row][column];
                }
            }
        }
    }

    private ArrayList<Pattern> runCalculations() {
        // Base Array used for the calculations
        double[][] classesTimes = new double[4][4];
        double[][] nonClassesTimes = new double[4][4];
        double[][] pointsClasses =  new double[4][4];
        double[][] pointsNonClasses =  new double[4][4];


        // Set arrays using user input values
        setBaseArraysValues(classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);

        preferences.saveArrayToPreferences(CLASSES_TIMES, classesTimes, this);
        preferences.saveArrayToPreferences(NON_CLASSES_TIMES, nonClassesTimes, this);
        preferences.saveArrayToPreferences(POINTS_CLASSES, pointsClasses, this);

        // Generate lists with calculation results
        ArrayList<String> bestTripleList = calculator.runCalculations(TRIPLES, classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);
        ArrayList<String> bestDoubleList = calculator.runCalculations(DOUBLES, classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);
        ArrayList<String> bestSingleList = calculator.runCalculations(SINGLES, classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);
        ArrayList<String> bestSpecialList = calculator.runCalculations(SPECIALS, classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);

        // Log results lists
//                logList(bestTripleList, "Listagem Triples");
//                logList(bestDoubleList, "Listagem Doubles");
//                logList(bestSingleList, "Listagem Singles");
//                logList(bestSpecialList, "Listagem Specials");

        // Sort all lists
        calculator.sortAllLists(bestTripleList, bestDoubleList, bestSingleList);

        ArrayList<Pattern> resultPatternsArray = new ArrayList<>();
        ArrayList<String> resultsStringsArray = new ArrayList<>();

        // Get top results among all patterns and sort them out
        calculator.calculateTop4Results(bestTripleList, bestDoubleList, bestSingleList, bestSpecialList, resultPatternsArray, resultsStringsArray);

        // Compare all result patterns (excluding TOP4) and sort them out
        calculator.calculateRegularResults(bestTripleList, bestDoubleList, bestSingleList, resultPatternsArray, resultsStringsArray);
        return resultPatternsArray;
    }

    private void goToResults(ArrayList<Pattern> results) {
        Intent intent = new Intent(MainActivity.this, ListResultsActivity.class);
        intent.putParcelableArrayListExtra(KEY_RESULTS, results);
        startActivity(intent);
    }



    private void setTestValues() {
        preferences.getArrayFromPreferences(CLASSES_TIMES, classes, this);
        preferences.getArrayFromPreferences(NON_CLASSES_TIMES, nonClasses, this);
        preferences.getArrayFromPreferences(POINTS_CLASSES, pointsForClasses, this);

        /*
        classes[0][0].setText("17");
        classes[0][1].setText("0");
        classes[0][2].setText("0");
        classes[0][3].setText("15");

        classes[1][0].setText("0");
        classes[1][1].setText("0");
        classes[1][2].setText("0");
        classes[1][3].setText("0");

        classes[2][0].setText("0");
        classes[2][1].setText("0");
        classes[2][2].setText("0");
        classes[2][3].setText("0");

        classes[3][0].setText("0");
        classes[3][1].setText("10");
        classes[3][2].setText("0");
        classes[3][3].setText("17");


        nonClasses[0][0].setText("0");
        nonClasses[0][1].setText("1");
        nonClasses[0][2].setText("5");
        nonClasses[0][3].setText("0");

        nonClasses[1][0].setText("6");
        nonClasses[1][1].setText("16");
        nonClasses[1][2].setText("10");
        nonClasses[1][3].setText("10");

        nonClasses[2][0].setText("8");
        nonClasses[2][1].setText("9");
        nonClasses[2][2].setText("10");
        nonClasses[2][3].setText("1");

        nonClasses[3][0].setText("13");
        nonClasses[3][1].setText("0");
        nonClasses[3][2].setText("7");
        nonClasses[3][3].setText("0");


        pointsForClasses[0][0].setText("40");
        pointsForClasses[0][1].setText("10");
        pointsForClasses[0][2].setText("10");
        pointsForClasses[0][3].setText("40");

        pointsForClasses[1][0].setText("10");
        pointsForClasses[1][1].setText("20");
        pointsForClasses[1][2].setText("20");
        pointsForClasses[1][3].setText("10");

        pointsForClasses[2][0].setText("10");
        pointsForClasses[2][1].setText("20");
        pointsForClasses[2][2].setText("20");
        pointsForClasses[2][3].setText("10");

        pointsForClasses[3][0].setText("30");
        pointsForClasses[3][1].setText("10");
        pointsForClasses[3][2].setText("10");
        pointsForClasses[3][3].setText("40");
        */

        /*
        classes[0][0].setText("0");
        classes[0][1].setText("0");
        classes[0][2].setText("7.0");
        classes[0][3].setText("0");

        classes[1][0].setText("17");
        classes[1][1].setText("0");
        classes[1][2].setText("0");
        classes[1][3].setText("0");

        classes[2][0].setText("15");
        classes[2][1].setText("0");
        classes[2][2].setText("9.0");
        classes[2][3].setText("0");

        classes[3][0].setText("0");
        classes[3][1].setText("11");
        classes[3][2].setText("0");
        classes[3][3].setText("14");


        nonClasses[0][0].setText("10");
        nonClasses[0][1].setText("10");
        nonClasses[0][2].setText("0");
        nonClasses[0][3].setText("7");

        nonClasses[1][0].setText("0");
        nonClasses[1][1].setText("4");
        nonClasses[1][2].setText("7");
        nonClasses[1][3].setText("1");

        nonClasses[2][0].setText("0");
        nonClasses[2][1].setText("6");
        nonClasses[2][2].setText("0");
        nonClasses[2][3].setText("18");

        nonClasses[3][0].setText("6");
        nonClasses[3][1].setText("0");
        nonClasses[3][2].setText("1000");
        nonClasses[3][3].setText("0");


        pointsForClasses[0][0].setText("20");
        pointsForClasses[0][1].setText("30");
        pointsForClasses[0][2].setText("40");
        pointsForClasses[0][3].setText("20");

        pointsForClasses[1][0].setText("30");
        pointsForClasses[1][1].setText("10");
        pointsForClasses[1][2].setText("10");
        pointsForClasses[1][3].setText("30");

        pointsForClasses[2][0].setText("40");
        pointsForClasses[2][1].setText("10");
        pointsForClasses[2][2].setText("10");
        pointsForClasses[2][3].setText("30");

        pointsForClasses[3][0].setText("20");
        pointsForClasses[3][1].setText("40");
        pointsForClasses[3][2].setText("30");
        pointsForClasses[3][3].setText("20");
        */
        /*
        classes[0][0].setText("0");
        classes[0][1].setText("0");
        classes[0][2].setText("0");
        classes[0][3].setText("0");

        classes[1][0].setText("12");
        classes[1][1].setText("0");
        classes[1][2].setText("0");
        classes[1][3].setText("11");

        classes[2][0].setText("14");
        classes[2][1].setText("0");
        classes[2][2].setText("0");
        classes[2][3].setText("0");

        classes[3][0].setText("0");
        classes[3][1].setText("9");
        classes[3][2].setText("0");
        classes[3][3].setText("0");


        nonClasses[0][0].setText("1");
        nonClasses[0][1].setText("8");
        nonClasses[0][2].setText("5");
        nonClasses[0][3].setText("7");

        nonClasses[1][0].setText("0");
        nonClasses[1][1].setText("9");
        nonClasses[1][2].setText("14");
        nonClasses[1][3].setText("0");

        nonClasses[2][0].setText("0");
        nonClasses[2][1].setText("9");
        nonClasses[2][2].setText("1");
        nonClasses[2][3].setText("2");

        nonClasses[3][0].setText("1");
        nonClasses[3][1].setText("0");
        nonClasses[3][2].setText("1");
        nonClasses[3][3].setText("1");


        pointsForClasses[0][0].setText("10");
        pointsForClasses[0][1].setText("20");
        pointsForClasses[0][2].setText("20");
        pointsForClasses[0][3].setText("10");

        pointsForClasses[1][0].setText("20");
        pointsForClasses[1][1].setText("30");
        pointsForClasses[1][2].setText("30");
        pointsForClasses[1][3].setText("20");

        pointsForClasses[2][0].setText("20");
        pointsForClasses[2][1].setText("30");
        pointsForClasses[2][2].setText("30");
        pointsForClasses[2][3].setText("20");

        pointsForClasses[3][0].setText("10");
        pointsForClasses[3][1].setText("20");
        pointsForClasses[3][2].setText("20");
        pointsForClasses[3][3].setText("10");
        */

        /*classes[0][0].setText("13");
        classes[1][0].setText("16");
        classes[3][0].setText("8");
        classes[2][1].setText("6");
        classes[0][3].setText("11");

        nonClasses[2][0].setText("4.814814815");
        nonClasses[0][1].setText("16");
        nonClasses[1][1].setText("3");
        nonClasses[0][2].setText("12");
        nonClasses[1][2].setText("5.5");
        nonClasses[2][2].setText("7");
        nonClasses[3][2].setText("16");
        nonClasses[1][3].setText("10");
        nonClasses[2][3].setText("24");

        pointsForClasses[0][0].setText("20");
        pointsForClasses[1][0].setText("30");
        pointsForClasses[2][0].setText("30");
        pointsForClasses[3][0].setText("20");
        pointsForClasses[0][1].setText("30");
        pointsForClasses[1][1].setText("10");
        pointsForClasses[2][1].setText("10");
        pointsForClasses[3][1].setText("30");
        pointsForClasses[0][2].setText("30");
        pointsForClasses[1][2].setText("10");
        pointsForClasses[2][2].setText("10");
        pointsForClasses[3][2].setText("30");
        pointsForClasses[0][3].setText("20");
        pointsForClasses[1][3].setText("30");
        pointsForClasses[2][3].setText("30");
        pointsForClasses[3][3].setText("20");*/
    }




}
