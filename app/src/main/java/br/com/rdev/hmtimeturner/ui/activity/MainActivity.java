package br.com.rdev.hmtimeturner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.model.Calculator;
import br.com.rdev.hmtimeturner.model.Pattern;
import br.com.rdev.hmtimeturner.ui.adapter.CustomSpinnerAdapter;
import br.com.rdev.hmtimeturner.ui.tutorial.Tutorial;
import br.com.rdev.hmtimeturner.util.Preferences;

import static br.com.rdev.hmtimeturner.model.Calculator.START_SPECIALS;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.CLASSES_TIMES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.DOUBLES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.KEY_RESULTS;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.KEY_TUTORIAL;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.NON_CLASSES_TIMES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.POINTS_CLASSES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SINGLES;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SPECIALS;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SPECIAL_MULTIPLE;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SPECIAL_SELECTED;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.TRIPLES;
import static java.util.Locale.US;


public class MainActivity extends AppCompatActivity {

    private EditText pointsSpecialPattern;
    private int specialPatternSlected;

    private EditText[][] classes;
    private EditText[][] nonClasses;
    private EditText[][] pointsForClasses;
    private Calculator calculator;
    private  Preferences preferences;

    private Button btnRun;
    private Button btnClearClasses;
    private Button btnClearNonClasses;
    private Button btnClearPoints;
    private Button btnClearAll;
    private Button btnTutorial;

    private Spinner spinnerSpecialPattern;

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

        checkBeforeShowTutorial();
    }

    private void checkBeforeShowTutorial() {
        Intent receivedData = getIntent();
        if (receivedData.hasExtra(KEY_TUTORIAL) && receivedData.getBooleanExtra(KEY_TUTORIAL, false)) {
            showTutorialScreen();
        }
    }

    private void showTutorialScreen() {
        List<View> views = new ArrayList<View>();
        views.add(classes[1][1]);
        views.add(nonClasses[1][1]);
        views.add(pointsForClasses[1][1]);
        views.add(spinnerSpecialPattern);
        views.add(pointsSpecialPattern);
        views.add(btnRun);
        new Tutorial(this, views).runTutorial();

    }

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
        btnRun = findViewById(R.id.button_run);
        btnClearClasses = findViewById(R.id.button_clear_classes);
        btnClearNonClasses = findViewById(R.id.button_clear_nonclasses);
        btnClearPoints = findViewById(R.id.button_clear_points);
        btnClearAll = findViewById(R.id.button_clear_all);
        btnTutorial = findViewById(R.id.button_tutorial);

        btnRun.setOnClickListener(new View.OnClickListener() {
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

        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTutorialScreen();
            }
        });
    }

    private void configureSpinnerSpecial() {
        pointsSpecialPattern = findViewById(R.id.points_special_pattern);
        pointsSpecialPattern.setText(String.format(US, "%d", calculator.getSpecialPatternMultiplier()));

        String[] patternNames = getResources().getStringArray(R.array.special_patterns_names);
        int[] patternImages = {R.drawable.pattern_51, R.drawable.pattern_52, R.drawable.pattern_53, R.drawable.pattern_54, R.drawable.pattern_55, R.drawable.pattern_56};

        spinnerSpecialPattern = findViewById(R.id.spinner_special_pattern);

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
    }
}
