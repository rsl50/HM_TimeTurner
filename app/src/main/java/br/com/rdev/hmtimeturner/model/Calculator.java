package br.com.rdev.hmtimeturner.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.util.Preferences;

import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SPECIAL_MULTIPLE;
import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.SPECIAL_SELECTED;

public class Calculator {

    private static final int[][] PATTERN_1 = new int[][]{
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 1},
    };

    private static final int[][] PATTERN_2 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 1, 1},
            {0, 1, 0, 1},
            {1, 0, 0, 1},
    };

    private static final int[][] PATTERN_3 = new int[][]{
            {1, 1, 0, 0},
            {1, 1, 1, 1},
            {0, 1, 1, 0},
            {0, 1, 0, 1},
    };

    private static final int[][] PATTERN_4 = new int[][]{
            {0, 0, 1, 1},
            {1, 1, 1, 1},
            {0, 1, 1, 0},
            {1, 0, 1, 0},
    };

    private static final int[][] PATTERN_5 = new int[][]{
            {0, 1, 0, 1},
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
    };

    private static final int[][] PATTERN_6 = new int[][]{
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 1},
    };

    private static final int[][] PATTERN_7 = new int[][]{
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {0, 0, 1, 1},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_8 = new int[][]{
            {1, 0, 0, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 0},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_9 = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
    };

    private static final int[][] PATTERN_10 = new int[][]{
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
    };

    private static final int[][] PATTERN_11 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
    };

    private static final int[][] PATTERN_12 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_13 = new int[][]{
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_14 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
    };

    private static final int[][] PATTERN_15 = new int[][]{
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
    };

    private static final int[][] PATTERN_16 = new int[][]{
            {0, 1, 0, 0},
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
    };

    private static final int[][] PATTERN_17 = new int[][]{
            {0, 0, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
    };

    private static final int[][] PATTERN_18 = new int[][]{
            {0, 0, 0, 1},
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_19 = new int[][]{
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_20 = new int[][]{
            {0, 0, 0, 1},
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
    };

    private static final int[][] PATTERN_21 = new int[][]{
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 0},
    };

    private static final int[][] PATTERN_22 = new int[][]{
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1},
            {0, 1, 0, 0},
    };

    private static final int[][] PATTERN_23 = new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0},
    };

    private static final int[][] PATTERN_24 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {1, 1, 1, 1},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_25 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 0},
    };

    private static final int[][] PATTERN_26 = new int[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_27 = new int[][]{
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_28 = new int[][]{
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_29 = new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_30 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_31 = new int[][]{
            {1, 0, 0, 0},
            {1, 1, 0, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 1},
    };

    private static final int[][] PATTERN_32 = new int[][]{
            {1, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 1},
    };

    private static final int[][] PATTERN_33 = new int[][]{
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 1},
    };

    private static final int[][] PATTERN_34 = new int[][]{
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {0, 0, 1, 1},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_35 = new int[][]{
            {1, 0, 0, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 0},
            {1, 0, 0, 0},
    };

    private static final int[][] PATTERN_36 = new int[][]{
            {0, 1, 0, 1},
            {0, 1, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 0, 0},
    };

    private static final int[][] PATTERN_37 = new int[][]{
            {0, 0, 1, 1},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {1, 0, 1, 0},
    };

    private static final int[][] PATTERN_38 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 1},
            {0, 1, 0, 1},
            {1, 0, 0, 1},
    };

    private static final int[][] PATTERN_39 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_40 = new int[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_41 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    };

    private static final int[][] PATTERN_42 = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    };

    private static final int[][] PATTERN_43 = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
    };

    private static final int[][] PATTERN_44 = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
    };

    private static final int[][] PATTERN_45 = new int[][]{
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
    };

    private static final int[][] PATTERN_46 = new int[][]{
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
    };

    private static final int[][] PATTERN_47 = new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
    };

    private static final int[][] PATTERN_48 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_49 = new int[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
    };

    private static final int[][] PATTERN_50 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
    };

    //ACCIO
    private static final int[][] PATTERN_51 = new int[][]{
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
    };

    //COLLOPORTUS
    private static final int[][] PATTERN_52 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 1, 1, 1},
            {0, 1, 0, 0},
    };

    //EPISKEY
    private static final int[][] PATTERN_53 = new int[][]{
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {0, 1, 1, 0},
    };

    //LOCOMOTOR
    private static final int[][] PATTERN_54 = new int[][]{
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0},
    };

    //PETRIFICUS TOTALUS
    private static final int[][] PATTERN_55 = new int[][]{
            {1, 1, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 1, 1, 0},
    };

    //REVELIO
    private static final int[][] PATTERN_56 = new int[][]{
            {1, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 1, 0},
            {1, 0, 0, 1},
    };

    private static final int PATTERN_QTY = 56;

    private static final int SINGLES_MULTIPLIER = 60;
    private static final int DOUBLES_MULTIPLIER = 140;
    private static final int TRIPLES_MULTIPLIER = 250;
    private int SPECIAL_MULTIPLIER;
    private int CURRENT_SPECIAL_PATTERN;

    private static final int START_TIPLES = 1;
    private static final int END_TIPLES = 8;
    private static final int START_DOUBLES = 9;
    private static final int END_DOUBLES = 40;
    private static final int START_SINGLES = 41;
    private static final int END_SINGLES = 50;
    public static final int START_SPECIALS = 51;
    private static final int END_SPECIALS = PATTERN_QTY;

    private static final int RESULT_TOP4 = 1;
    private static final int RESULT_REGULAR = 0;

    private Context context;

    private ArrayList<int[][]> patternList = new ArrayList<>();

    public Calculator(Context context) {
        this.context = context;
        populateList();

        Preferences preferences = new Preferences();

        if (preferences.contains(SPECIAL_SELECTED, context)) {
            setSelectedSpecialPattern(Integer.parseInt(preferences.getPrefs(SPECIAL_SELECTED, context)));
        } else {
            setSelectedSpecialPattern(0);
        }

        if (preferences.contains(SPECIAL_MULTIPLE, context)) {
            setSelectedSpecialPatternMultiplier(Integer.parseInt(preferences.getPrefs(SPECIAL_MULTIPLE, context)));
        } else {
            setSelectedSpecialPatternMultiplier(220);
        }
    }

    private double calculateArraysWithPattern(int[][] pattern, double[][] classesTimes, double[][] nonClassesTimes, double[][] pointsClasses, double[][] pointsNonClasses, double[][] classesTimesInPattern, double[][] nonClassesTimesOutPattern, double[][] pointsInPattern, double[][] pointsOutPattern) {
        // Set time arrays using a given pattern
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (pattern[row][column] != 0) {
                    classesTimesInPattern[row][column] = classesTimes[row][column];
                    nonClassesTimesOutPattern[row][column] = nonClassesTimes[row][column];
                }
            }
        }

        // Calculate the required time for a given pattern, using the sum of all required classes times plus the highest time value of a non class activity
        double timeRequired = Math.max(sumArrayValues(classesTimesInPattern), getBiggestValue(nonClassesTimesOutPattern));

        // Set points arrays according to a given pattern
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (pattern[row][column] != 0) {
                    pointsInPattern[row][column] = pointsClasses[row][column];
                } else {
                    if (nonClassesTimes[row][column] <= timeRequired) {
                        pointsOutPattern[row][column] = pointsNonClasses[row][column];
                    }
                }
            }
        }
        return timeRequired;
    }

    private Double sumArrayValues(double[][] array) {
        double sum = 0;

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                sum += array[row][column];
            }
        }

        return sum;
    }

    private Double getBiggestValue(double[][] array) {
        double value = 0;

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (array[row][column] > value) {
                    value = array[row][column];
                }
            }
        }

        return value;
    }

    public void setSelectedSpecialPattern(int value) {
        this.CURRENT_SPECIAL_PATTERN = value + START_SPECIALS;
    }

    public int getSpecialPattern() {
        return CURRENT_SPECIAL_PATTERN;
    }

    public void setSelectedSpecialPatternMultiplier(int value) {
        this.SPECIAL_MULTIPLIER = value;
    }

    public int getSpecialPatternMultiplier () {
        return SPECIAL_MULTIPLIER;
    }

    private void clearArray(double[][] array){
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                array[row][column] = 0;
            }
        }
    }

    private double getPatternMultiplicator(int patternNumber) {
        double multiplier = 0;

        if (patternNumber >= START_TIPLES && patternNumber <= END_TIPLES) {
            multiplier = TRIPLES_MULTIPLIER;
        } else if (patternNumber >= START_DOUBLES && patternNumber <= END_DOUBLES) {
            multiplier = DOUBLES_MULTIPLIER;
        } else if (patternNumber >= START_SINGLES && patternNumber <= END_SINGLES) {
            multiplier = SINGLES_MULTIPLIER;
        } else if (patternNumber >= START_SPECIALS && patternNumber <= END_SPECIALS) {
            multiplier = getSpecialPatternMultiplier ();
        }

        return multiplier;
    }

    private String getPatternType(int patternNumber) {
        String patternType = null;

        if (patternNumber >= START_TIPLES && patternNumber <= END_TIPLES) {
            patternType = context.getString(R.string.name_triple);
        } else if (patternNumber >= START_DOUBLES && patternNumber <= END_DOUBLES) {
            patternType = context.getString(R.string.name_double);
        } else if (patternNumber >= START_SINGLES && patternNumber <= END_SINGLES) {
            patternType = context.getString(R.string.name_simple);
        } else if (patternNumber >= START_SPECIALS && patternNumber <= END_SPECIALS) {
            patternType = context.getString(R.string.name_special);
        }

        return patternType;
    }

    private void populateList(){
        this.patternList.add(PATTERN_1);
        this.patternList.add(PATTERN_2);
        this.patternList.add(PATTERN_3);
        this.patternList.add(PATTERN_4);
        this.patternList.add(PATTERN_5);
        this.patternList.add(PATTERN_6);
        this.patternList.add(PATTERN_7);
        this.patternList.add(PATTERN_8);
        this.patternList.add(PATTERN_9);
        this.patternList.add(PATTERN_10);
        this.patternList.add(PATTERN_11);
        this.patternList.add(PATTERN_12);
        this.patternList.add(PATTERN_13);
        this.patternList.add(PATTERN_14);
        this.patternList.add(PATTERN_15);
        this.patternList.add(PATTERN_16);
        this.patternList.add(PATTERN_17);
        this.patternList.add(PATTERN_18);
        this.patternList.add(PATTERN_19);
        this.patternList.add(PATTERN_20);
        this.patternList.add(PATTERN_21);
        this.patternList.add(PATTERN_22);
        this.patternList.add(PATTERN_23);
        this.patternList.add(PATTERN_24);
        this.patternList.add(PATTERN_25);
        this.patternList.add(PATTERN_26);
        this.patternList.add(PATTERN_27);
        this.patternList.add(PATTERN_28);
        this.patternList.add(PATTERN_29);
        this.patternList.add(PATTERN_30);
        this.patternList.add(PATTERN_31);
        this.patternList.add(PATTERN_32);
        this.patternList.add(PATTERN_33);
        this.patternList.add(PATTERN_34);
        this.patternList.add(PATTERN_35);
        this.patternList.add(PATTERN_36);
        this.patternList.add(PATTERN_37);
        this.patternList.add(PATTERN_38);
        this.patternList.add(PATTERN_39);
        this.patternList.add(PATTERN_40);
        this.patternList.add(PATTERN_41);
        this.patternList.add(PATTERN_42);
        this.patternList.add(PATTERN_43);
        this.patternList.add(PATTERN_44);
        this.patternList.add(PATTERN_45);
        this.patternList.add(PATTERN_46);
        this.patternList.add(PATTERN_47);
        this.patternList.add(PATTERN_48);
        this.patternList.add(PATTERN_49);
        this.patternList.add(PATTERN_50);
        this.patternList.add(PATTERN_51);
        this.patternList.add(PATTERN_52);
        this.patternList.add(PATTERN_53);
        this.patternList.add(PATTERN_54);
        this.patternList.add(PATTERN_55);
        this.patternList.add(PATTERN_56);
    }

    private int[][] getTestPattern(int patternNumber) {
        return patternList.get(patternNumber - 1);
    }

    public ArrayList<String> runCalculations (String patternType, double[][] classesTimes, double[][] nonClassesTimes, double[][] pointsClasses, double[][] pointsNonClasses) {
        // Pattern dependent arrays
        double[][] classesTimesInPattern = new double[4][4];
        double[][] nonClassesTimesOutPattern = new double[4][4];
        double[][] pointsInPattern = new double[4][4];
        double[][] pointsOutPattern = new double[4][4];

        // Calculation variables
        double timeRequired;
        double minPoints;
        double maxPoints;
        double minPointsPerHour;
        double expectedPointsPerHour;

        ArrayList<String> resultList = new ArrayList<>();
        String calculationResult;

        int startPattern = 1;
        int endPattern = PATTERN_QTY - 4;

        switch (patternType) {
            case "Triples": {
                startPattern = START_TIPLES;
                endPattern = END_TIPLES;
                break;
            }

            case "Doubles": {
                startPattern = START_DOUBLES;
                endPattern = END_DOUBLES;
                break;
            }

            case "Singles": {
                startPattern = START_SINGLES;
                endPattern = END_SINGLES;
                break;
            }

            case "Specials": {
                startPattern = getSpecialPattern();
                endPattern = getSpecialPattern();
                break;
            }
        }


        // Run pattern calculation
        for (int i = startPattern; i <= endPattern; i++) {
            clearArray(classesTimesInPattern);
            clearArray(nonClassesTimesOutPattern);
            clearArray(pointsInPattern);
            clearArray(pointsOutPattern);

            timeRequired = calculateArraysWithPattern(getTestPattern(i),
                    classesTimes,
                    nonClassesTimes,
                    pointsClasses,
                    pointsNonClasses,
                    classesTimesInPattern,
                    nonClassesTimesOutPattern,
                    pointsInPattern,
                    pointsOutPattern);

            minPoints = sumArrayValues(pointsInPattern) + getPatternMultiplicator(i);
            maxPoints = sumArrayValues(pointsInPattern) + sumArrayValues(pointsOutPattern) + getPatternMultiplicator(i);
            minPointsPerHour = minPoints / timeRequired;
            expectedPointsPerHour = maxPoints / timeRequired;

            calculationResult = expectedPointsPerHour + ";" + i + ";" + timeRequired + ";" + minPoints + ";" + maxPoints + ";" + minPointsPerHour;

            //Display arrays
//            Log.d("HP", "TESTE PADRÃO " + i + "-" + calculator.getPatternType(i));
//            logArray(classesTimes, "Horas Aulas");
//            logArray(nonClassesTimes, "Horas Não Aula");
//            logArray(pointsClasses, "Pontos");
//            logArray(pointsNonClasses, "Pontos Não Aulas");
//            logArray(classesTimesInPattern, "Horas Aulas Requeridas");
//            logArray(nonClassesTimesOutPattern, "Horas NÃO Aulas Requeridas");
//            logArray(pointsInPattern, "Mímino Pontos no Padrão");
//            logArray(pointsOutPattern, "Pontos Fora no Padrão");
//            Log.d("HP","Horas Necessárias:" + timeRequired);
//            Log.d("HP","Pontos Mínimos:" + minPoints);
//            Log.d("HP","Pontos Máximos:" + maxPoints);
//            Log.d("HP","Pontos Mínimos/Hora:" + minPointsPerHour);
//            Log.d("HP","Pontos Esperados/Hora:" + expectedPointsPerHour);
//            Log.d("HP", "=============");

            resultList.add(calculationResult);
        }

        return resultList;
    }

    private void addPatternsToArray(ArrayList<Pattern> destination, ArrayList<String> origin, int isTop) {
        String resultPatternString;
        String[] resultItemValues;

        for (int i = 0; i < origin.size(); i++) {
            resultPatternString = origin.get(i);
            resultItemValues = resultPatternString.split(";");

            destination.add(new Pattern(
                    getPatternType(Integer.parseInt(resultItemValues[1])),
                    "pattern_"+resultItemValues[1],
                    Double.parseDouble(resultItemValues[0]),
                    Double.parseDouble(resultItemValues[3]),
                    Double.parseDouble(resultItemValues[5]),
                    Double.parseDouble(resultItemValues[4]),
                    Double.parseDouble(resultItemValues[2]), isTop));
        }

    }

    private void sortList (ArrayList<String> arrayToSort) {

        Collections.sort(arrayToSort, new Comparator<String>() {
            @Override
            public int compare(String c1, String c2) {

                String[] val1 = c1.split(";");
                String[] val2 = c2.split(";");
                double doubleVal1 = Double.parseDouble(val1[0]);
                double doubleVal2 = Double.parseDouble(val2[0]);

                return Double.compare(doubleVal2, doubleVal1);
            }
        });
    }

    public void sortAllLists(ArrayList<String> bestTripleList, ArrayList<String> bestDoubleList, ArrayList<String> bestSingleList) {
        sortList(bestTripleList);
        sortList(bestDoubleList);
        sortList(bestSingleList);

//        logList(bestTripleList, "Listagem Ordenada Triple");
//        logList(bestDoubleList, "Listagem Ordenada Double");
//        logList(bestSingleList, "Listagem Ordenada Single");
    }

    private void addToArray(ArrayList<String> destination, ArrayList<String> origin) {

        for (int i = 1; i < origin.size(); i++) {
            destination.add(origin.get(i));
        }
    }

    public void calculateRegularResults(ArrayList<String> bestTripleList, ArrayList<String> bestDoubleList, ArrayList<String> bestSingleList, ArrayList<Pattern> destinationArray, ArrayList<String> originArray) {
        originArray.clear();
        addToArray(originArray, bestTripleList);
        addToArray(originArray, bestDoubleList);
        addToArray(originArray, bestSingleList);

        sortList(originArray);

        // Add sorted results to Results Array
        addPatternsToArray(destinationArray, originArray, RESULT_REGULAR);
    }

    public void calculateTop4Results(ArrayList<String> bestTripleList, ArrayList<String> bestDoubleList, ArrayList<String> bestSingleList, ArrayList<String> bestSpecialList, ArrayList<Pattern> destinationArray, ArrayList<String> originArray) {
        originArray.clear();
        originArray.add(bestTripleList.get(0));
        originArray.add(bestDoubleList.get(0));
        originArray.add(bestSingleList.get(0));
        originArray.add(bestSpecialList.get(0));
        sortList(originArray);

        // Add TOP4 results to Results Array
        addPatternsToArray(destinationArray, originArray, RESULT_TOP4);
    }

    private void logList(ArrayList<String> bestTripleList, String s) {
        Log.d("HP", s);
        for (String results : bestTripleList) {
            Log.d("HP", results);
        }
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
}
