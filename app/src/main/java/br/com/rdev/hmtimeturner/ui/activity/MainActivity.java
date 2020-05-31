package br.com.rdev.hmtimeturner.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.model.Calculator;

import static br.com.rdev.hmtimeturner.model.Calculator.PATTERN_1;
import static br.com.rdev.hmtimeturner.model.Calculator.TRIPLES;

public class MainActivity extends AppCompatActivity {

    private Button btnMagick;
    private Button btnClearClasses;
    private Button btnClearNonClasses;
    private Button btnClearPoints;
    private Button btnClearAll;

    private TextView minPointsValue;
    private TextView maxPointsValue;
    private TextView hoursRequiredValue;
    private TextView minPointsPerHourValue;
    private TextView expectedPointsPerHourValue;

    private EditText[][] classes;
    private EditText[][] nonClasses;
    private EditText[][] pointsForClasses;
    private Calculator calculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minPointsValue = findViewById(R.id.value_minPoints);
        maxPointsValue = findViewById(R.id.value_maxPoints);
        hoursRequiredValue = findViewById(R.id.value_hours);
        minPointsPerHourValue = findViewById(R.id.value_minPointsPerHour);
        expectedPointsPerHourValue = findViewById(R.id.value_expectedPointsPerHour);

        calculator = new Calculator();

        initializeEditTexts();
        configureButtons();
        setTestValues();
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
                // Base Array used for the calculations
                double[][] classesTimes = new double[4][4];
                double[][] nonClassesTimes = new double[4][4];
                double[][] pointsClasses =  new double[4][4];
                double[][] pointsNonClasses =  new double[4][4];

                // Pattern dependent arrays
                double[][] classesTimesInPattern = new double[4][4];
                double[][] nonClassesTimesOutPattern = new double[4][4];
                double[][] pointsInPattern = new double[4][4];
                double[][] pointsOutPattern = new double[4][4];

                // Set arrays using user input values
                setBaseArraysValues(classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);
                double timeRequired = calculator.calculateArraysWithPattern(calculator.getTestPattern(2), classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses, classesTimesInPattern, nonClassesTimesOutPattern, pointsInPattern, pointsOutPattern);

                //Display arrays
                logArray(classesTimes, "Horas Aulas");
                logArray(nonClassesTimes, "Horas Não Aula");
                logArray(pointsClasses, "Pontos");
                logArray(pointsNonClasses, "Pontos Não Aulas");
                logArray(classesTimesInPattern, "Horas Aulas Requeridas");
                logArray(nonClassesTimesOutPattern, "Horas NÃO Aulas Requeridas");
                logArray(pointsInPattern, "Mímino Pontos no Padrão");
                logArray(pointsOutPattern, "Pontos Fora no Padrão");

                double minPoints = calculator.sumArrayValues(pointsInPattern) + TRIPLES;
                double maxPoints = calculator.sumArrayValues(pointsInPattern) + calculator.sumArrayValues(pointsOutPattern) + TRIPLES;
                double minPointsPerHour = minPoints/timeRequired;
                double expectedPointsPerHour = maxPoints/timeRequired;

                Log.d("HP","Horas Necessárias:" + timeRequired);
                Log.d("HP","Pontos Mínimos:" + minPoints);
                Log.d("HP","Pontos Máximos:" + maxPoints);
                Log.d("HP","Pontos Mínimos/Hora:" + minPointsPerHour);
                Log.d("HP","Pontos Esperados/Hora:" + expectedPointsPerHour);

                minPointsValue.setText(String.format("%.0f", minPoints));
                maxPointsValue.setText(String.format("%.0f", maxPoints));
                hoursRequiredValue.setText(String.format("%.2f", timeRequired));
                minPointsPerHourValue.setText(String.format("%.2f", minPointsPerHour));
                expectedPointsPerHourValue.setText(String.format("%.2f", expectedPointsPerHour));
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



    private void logArray (double[][] array, String mensagem) {
        Log.d("HP",mensagem);
        for (int row = 0; row < 4; row++) {
            Log.d("HP",row + "|[" + String.format("%.2f", array[row][0]) + "]" +
                    "[" + String.format("%.2f", array[row][1]) + "]" +
                    "[" + String.format("%.2f", array[row][2]) + "]" +
                    "[" + String.format("%.2f", array[row][3]) + "]");
        }
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


    private double getEditTextValue (int row, int column, EditText[][] editTextArray) {
        double value = 0;

        try {
            value = Double.parseDouble(editTextArray[row][column].getText().toString());
            Log.d("HP", String.format("%.2f", value));
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse:" + nfe);
        }

        return value;
    }

    private void setTestValues() {
        classes[0][0].setText("13");
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
        pointsForClasses[3][3].setText("20");
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
                classes[row][column] = ((EditText) findViewById(resourceIdClass));

                //Log.d("initEditText", nameEditTextNonClasses);
                int resourceIdNonClass = getResources().getIdentifier(nameEditTextNonClasses, "id", getPackageName());
                nonClasses[row][column] = ((EditText) findViewById(resourceIdNonClass));

                //Log.d("initEditText", nameEditTextPoints);
                int resourceIdPoints = getResources().getIdentifier(nameEditTextPoints, "id", getPackageName());
                pointsForClasses[row][column] = ((EditText) findViewById(resourceIdPoints));
            }
        }
    }

    private void clearEditTextArray(EditText[][] editTexts){
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                editTexts[row][column].setText(null);
            }
        }
    }
}
