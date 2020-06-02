package br.com.rdev.hmtimeturner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.model.Calculator;
import br.com.rdev.hmtimeturner.model.Pattern;

import static br.com.rdev.hmtimeturner.model.Calculator.DOUBLES;
import static br.com.rdev.hmtimeturner.model.Calculator.PATTERN_QTY;
import static br.com.rdev.hmtimeturner.model.Calculator.SINGLES;
import static br.com.rdev.hmtimeturner.model.Calculator.TRIPLES;

public class MainActivity extends AppCompatActivity {

    private Button btnMagick;
    private Button btnClearClasses;
    private Button btnClearNonClasses;
    private Button btnClearPoints;
    private Button btnClearAll;

    private Spinner spinnerSpecialPattern;
    private TextView pointsSpecialPattern;
    private int specialPatternSlected = 51;

    private EditText[][] classes;
    private EditText[][] nonClasses;
    private EditText[][] pointsForClasses;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

        initializeEditTexts();
        configureButtons();
        configureSpinnerSpecial();
        setTestValues();
    }

    private void configureSpinnerSpecial() {
        pointsSpecialPattern = findViewById(R.id.points_special_pattern);
        pointsSpecialPattern.setText(String.format("%d", calculator.getSpecialPatternValue()));

        spinnerSpecialPattern = findViewById(R.id.spinner_special_pattern);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.special_patterns, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerSpecialPattern.setAdapter(adapter);

        spinnerSpecialPattern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specialPatternSlected = parent.getSelectedItemPosition() + 51;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

                // Calculation variables
                double timeRequired = 0;
                double minPoints = 0;
                double maxPoints = 0;
                double minPointsPerHour = 0;
                double expectedPointsPerHour = 0;

                // Set arrays using user input values
                setBaseArraysValues(classesTimes, nonClassesTimes, pointsClasses, pointsNonClasses);

                ArrayList<String> bestSingleList = new ArrayList<>();
                ArrayList<String> bestDoubleList = new ArrayList<>();
                ArrayList<String> bestTripleList = new ArrayList<>();
                ArrayList<String> bestSpecialList = new ArrayList<>();
                String calculationResult = "";

                calculator.setSpecialPatternValue(Integer.parseInt(pointsSpecialPattern.getText().toString()));

                // Run pattern calculation
                for (int i = 1; i <= PATTERN_QTY - 4; i++) {
                    calculator.clearArray(classesTimesInPattern);
                    calculator.clearArray(nonClassesTimesOutPattern);
                    calculator.clearArray(pointsInPattern);
                    calculator.clearArray(pointsOutPattern);

                    timeRequired = calculator.calculateArraysWithPattern(calculator.getTestPattern(i),
                            classesTimes,
                            nonClassesTimes,
                            pointsClasses,
                            pointsNonClasses,
                            classesTimesInPattern,
                            nonClassesTimesOutPattern,
                            pointsInPattern,
                            pointsOutPattern);

                    minPoints = calculator.sumArrayValues(pointsInPattern) + calculator.getMultiplicator(i);
                    maxPoints = calculator.sumArrayValues(pointsInPattern) + calculator.sumArrayValues(pointsOutPattern) + calculator.getMultiplicator(i);
                    minPointsPerHour = minPoints/timeRequired;
                    expectedPointsPerHour = maxPoints/timeRequired;

                    calculationResult = expectedPointsPerHour + ";" + i + ";" + timeRequired + ";" + minPoints + ";" + maxPoints + ";" + minPointsPerHour;

                    if (calculator.getMultiplicator(i) == TRIPLES) {
                        bestTripleList.add(calculationResult);
                    } else if (calculator.getMultiplicator(i) == DOUBLES) {
                        bestDoubleList.add(calculationResult);
                    } else if (calculator.getMultiplicator(i) == SINGLES) {
                        bestSingleList.add(calculationResult);
                    } //else {
                        //bestSpecialList.add(calculationResult);
                    //}

                    //Display arrays
                    Log.d("HP", "TESTE PADRÃO " + i + "-" + calculator.getPatternType(i));
                    logArray(classesTimes, "Horas Aulas");
                    logArray(nonClassesTimes, "Horas Não Aula");
                    logArray(pointsClasses, "Pontos");
                    logArray(pointsNonClasses, "Pontos Não Aulas");
                    logArray(classesTimesInPattern, "Horas Aulas Requeridas");
                    logArray(nonClassesTimesOutPattern, "Horas NÃO Aulas Requeridas");
                    logArray(pointsInPattern, "Mímino Pontos no Padrão");
                    logArray(pointsOutPattern, "Pontos Fora no Padrão");
                    Log.d("HP","Horas Necessárias:" + timeRequired);
                    Log.d("HP","Pontos Mínimos:" + minPoints);
                    Log.d("HP","Pontos Máximos:" + maxPoints);
                    Log.d("HP","Pontos Mínimos/Hora:" + minPointsPerHour);
                    Log.d("HP","Pontos Esperados/Hora:" + expectedPointsPerHour);
                    Log.d("HP", "=============");
                }

                // Calculate for the Special Pattern Selected
                calculator.clearArray(classesTimesInPattern);
                calculator.clearArray(nonClassesTimesOutPattern);
                calculator.clearArray(pointsInPattern);
                calculator.clearArray(pointsOutPattern);

                timeRequired = calculator.calculateArraysWithPattern(calculator.getTestPattern(specialPatternSlected),
                        classesTimes,
                        nonClassesTimes,
                        pointsClasses,
                        pointsNonClasses,
                        classesTimesInPattern,
                        nonClassesTimesOutPattern,
                        pointsInPattern,
                        pointsOutPattern);

                minPoints = calculator.sumArrayValues(pointsInPattern) + calculator.getMultiplicator(specialPatternSlected);
                maxPoints = calculator.sumArrayValues(pointsInPattern) + calculator.sumArrayValues(pointsOutPattern) + calculator.getSpecialPatternValue();
                minPointsPerHour = minPoints/timeRequired;
                expectedPointsPerHour = maxPoints/timeRequired;

                calculationResult = expectedPointsPerHour + ";" + specialPatternSlected + ";" + timeRequired + ";" + minPoints + ";" + maxPoints + ";" + minPointsPerHour;

                bestSpecialList.add(calculationResult);

                //Display arrays
                Log.d("HP", "TESTE PADRÃO " + specialPatternSlected + "-" + calculator.getPatternType(specialPatternSlected));
                logArray(classesTimes, "Horas Aulas");
                logArray(nonClassesTimes, "Horas Não Aula");
                logArray(pointsClasses, "Pontos");
                logArray(pointsNonClasses, "Pontos Não Aulas");
                logArray(classesTimesInPattern, "Horas Aulas Requeridas");
                logArray(nonClassesTimesOutPattern, "Horas NÃO Aulas Requeridas");
                logArray(pointsInPattern, "Mímino Pontos no Padrão");
                logArray(pointsOutPattern, "Pontos Fora no Padrão");
                Log.d("HP","Horas Necessárias:" + timeRequired);
                Log.d("HP","Pontos Mínimos:" + minPoints);
                Log.d("HP","Pontos Máximos:" + maxPoints);
                Log.d("HP","Pontos Mínimos/Hora:" + minPointsPerHour);
                Log.d("HP","Pontos Esperados/Hora:" + expectedPointsPerHour);
                Log.d("HP", "=============");


                //
                bestTripleList = sortResultsList(bestTripleList);
                bestDoubleList = sortResultsList(bestDoubleList);
                bestSingleList = sortResultsList(bestSingleList);
                bestSpecialList = sortResultsList(bestSpecialList);


//                Log.d("HP", "Listagem Triple");
//                for (String results : bestTripleList) {
//                    Log.d("HP", results);
//                }

//                Log.d("HP", "Listagem Double");
//                for (String results : bestDoubleList) {
//                    Log.d("HP", results);
//                }
//
//                Log.d("HP", "Listagem Single");
//                for (String results : bestSingleList) {
//                    Log.d("HP", results);
//                }
//
//                Log.d("HP", "Listagem Special");
//                for (String results : bestSpecialList) {
//                    Log.d("HP", results);
//                }

//                Log.d("HP", "=================");
//                String texto = bestTripleList.get(0);
//                String[] partes = texto.split(";");
//                for (int i = 0; i < partes.length; i++){
//                    Log.d("HP", i+":"+partes[i]);
//                }


                ArrayList<Pattern> results = new ArrayList<>();
                String resultPattern = null;
                String[] resultParts;

                // Get top results among all patterns
                ArrayList<String> resultsTop = new ArrayList<>();
                resultsTop.add(bestTripleList.get(0));
                resultsTop.add(bestDoubleList.get(0));
                resultsTop.add(bestSingleList.get(0));
                resultsTop.add(bestSpecialList.get(0));
                resultsTop = sortResultsList(resultsTop);

                for (int i = 0; i < resultsTop.size(); i++) {
                    resultPattern = resultsTop.get(i);
                    resultParts = resultPattern.split(";");

                    results.add(new Pattern(
                            calculator.getPatternType(Integer.parseInt(resultParts[1])),
                            "pattern_"+resultParts[1],
                            Double.parseDouble(resultParts[0]),
                            Double.parseDouble(resultParts[3]),
                            Double.parseDouble(resultParts[5]),
                            Double.parseDouble(resultParts[4]),
                            Double.parseDouble(resultParts[2]), 1));
                }

                ArrayList<String> allResults = new ArrayList<>();
                for (int i = 1; i < bestTripleList.size(); i++) {
                    allResults.add(bestTripleList.get(i));
                }

                for (int i = 1; i < bestDoubleList.size(); i++) {
                    allResults.add(bestDoubleList.get(i));
                }

                for (int i = 1; i < bestSingleList.size(); i++) {
                    allResults.add(bestSingleList.get(i));
                }

                for (int i = 1; i < bestSpecialList.size(); i++) {
                    allResults.add(bestSpecialList.get(i));
                }

                for (int i = 0; i < allResults.size(); i++) {
                    resultPattern = allResults.get(i);
                    resultParts = resultPattern.split(";");

                    results.add(new Pattern(
                            calculator.getPatternType(Integer.parseInt(resultParts[1])),
                            "pattern_"+resultParts[1],
                            Double.parseDouble(resultParts[0]),
                            Double.parseDouble(resultParts[3]),
                            Double.parseDouble(resultParts[5]),
                            Double.parseDouble(resultParts[4]),
                            Double.parseDouble(resultParts[2]), 0));
                }

//                for (int i = 1; i < bestTripleList.size(); i++) {
//                    resultPattern = bestTripleList.get(i);
//                    resultParts = resultPattern.split(";");
//
//                    results.add(new Pattern(
//                            calculator.getPatternType(Integer.parseInt(resultParts[1])),
//                            "pattern_"+resultParts[1],
//                            Double.parseDouble(resultParts[0]),
//                            Double.parseDouble(resultParts[3]),
//                            Double.parseDouble(resultParts[5]),
//                            Double.parseDouble(resultParts[4]),
//                            Double.parseDouble(resultParts[2])));
//                }



                Intent intent = new Intent(MainActivity.this, ListResultsActivity.class);
                intent.putParcelableArrayListExtra("result", results);
                startActivity(intent);
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


    private ArrayList<String> sortResultsList(ArrayList<String> arrayToSort) {
        ArrayList<String> arrayListSorted = arrayToSort;

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String value2, String value1)
            {
                String[] partsValue1 = value1.split(";");
                String[] partsValue2 = value2.split(";");

                return  partsValue1[0].compareTo(partsValue2[0]);
            }
        };

        Collections.sort(arrayListSorted, comparator);

        return arrayListSorted;
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
            //Log.d("HP", String.format("%.2f", value));
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse:" + nfe);
        }

        return value;
    }

    private void setTestValues() {

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
