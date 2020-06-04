package br.com.rdev.hmtimeturner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.model.Calculator;
import br.com.rdev.hmtimeturner.model.Pattern;
import br.com.rdev.hmtimeturner.ui.adapter.CustomSpinnerAdapter;

import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstantes.KEY_RESULTS;
import static java.util.Locale.US;

public class MainActivity extends AppCompatActivity {


    private TextView pointsSpecialPattern;
    private int specialPatternSlected;

    private EditText[][] classes;
    private EditText[][] nonClasses;
    private EditText[][] pointsForClasses;
    private Calculator calculator;

    public static final String TITLE_APPBAR = "HM Full Marks Time Turner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITLE_APPBAR);

        calculator = new Calculator();

        initializeEditTexts();
        configureButtons();
        configureSpinnerSpecial();
        setTestValues();
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
        Button btnMagick = findViewById(R.id.button_run);
        Button btnClearClasses = findViewById(R.id.button_clear_classes);
        Button btnClearNonClasses = findViewById(R.id.button_clear_nonclasses);
        Button btnClearPoints = findViewById(R.id.button_clear_points);
        Button btnClearAll = findViewById(R.id.button_clear_all);

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

        int[] patternImages = {R.drawable.pattern_51, R.drawable.pattern_52, R.drawable.pattern_53, R.drawable.pattern_54};

        Spinner spinnerSpecialPattern = findViewById(R.id.spinner_special_pattern);
        // Create an ArrayAdapter using the string array and a default spinner layout
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                //R.array.special_patterns_names, android.R.layout.simple_spinner_item);
                //R.array.special_patterns_names, R.layout.custom_spinner_items);
        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        //spinnerSpecialPattern.setAdapter(adapter);

        CustomSpinnerAdapter customAdapter=new CustomSpinnerAdapter(getApplicationContext(), patternImages, patternNames);
        spinnerSpecialPattern.setAdapter(customAdapter);

        spinnerSpecialPattern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specialPatternSlected = parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void captureSelectedSpecialPatternData() {
        calculator.setSelectedSpecialPattern(specialPatternSlected);
        calculator.setSelectedSpecialPatternMultiplier(Integer.parseInt(pointsSpecialPattern.getText().toString()));
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

        // Generate lists with calculation results
        ArrayList<String> bestTripleList = calculator.runCalculations("Triples", classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);
        ArrayList<String> bestDoubleList = calculator.runCalculations("Doubles", classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);
        ArrayList<String> bestSingleList = calculator.runCalculations("Singles", classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);
        ArrayList<String> bestSpecialList = calculator.runCalculations("Specials", classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);

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
