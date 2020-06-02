package br.com.rdev.hmtimeturner.model;

import android.widget.EditText;

public class Calculator {

    public static final int[][] PATTERN_1 = new int[][]{
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 1},
    };

    public static final int[][] PATTERN_2 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 1, 1},
            {0, 1, 0, 1},
            {1, 0, 0, 1},
    };

    public static final int[][] PATTERN_3 = new int[][]{
            {1, 1, 0, 0},
            {1, 1, 1, 1},
            {0, 1, 1, 0},
            {0, 1, 0, 1},
    };

    public static final int[][] PATTERN_4 = new int[][]{
            {0, 0, 1, 1},
            {1, 1, 1, 1},
            {0, 1, 1, 0},
            {1, 0, 1, 0},
    };

    public static final int[][] PATTERN_5 = new int[][]{
            {0, 1, 0, 1},
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
    };

    public static final int[][] PATTERN_6 = new int[][]{
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 1},
    };

    public static final int[][] PATTERN_7 = new int[][]{
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {0, 0, 1, 1},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_8 = new int[][]{
            {1, 0, 0, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 0},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_9 = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
    };

    public static final int[][] PATTERN_10 = new int[][]{
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
    };

    public static final int[][] PATTERN_11 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
    };

    public static final int[][] PATTERN_12 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_13 = new int[][]{
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_14 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
    };

    public static final int[][] PATTERN_15 = new int[][]{
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
    };

    public static final int[][] PATTERN_16 = new int[][]{
            {0, 1, 0, 0},
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
    };

    public static final int[][] PATTERN_17 = new int[][]{
            {0, 0, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
    };

    public static final int[][] PATTERN_18 = new int[][]{
            {0, 0, 0, 1},
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_19 = new int[][]{
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_20 = new int[][]{
            {0, 0, 0, 1},
            {1, 1, 1, 1},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
    };

    public static final int[][] PATTERN_21 = new int[][]{
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 0},
    };

    public static final int[][] PATTERN_22 = new int[][]{
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1},
            {0, 1, 0, 0},
    };

    public static final int[][] PATTERN_23 = new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0},
    };

    public static final int[][] PATTERN_24 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {1, 1, 1, 1},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_25 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 0},
    };

    public static final int[][] PATTERN_26 = new int[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_27 = new int[][]{
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_28 = new int[][]{
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_29 = new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_30 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_31 = new int[][]{
            {1, 0, 0, 0},
            {1, 1, 0, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 1},
    };

    public static final int[][] PATTERN_32 = new int[][]{
            {1, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 1},
    };

    public static final int[][] PATTERN_33 = new int[][]{
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 1},
    };

    public static final int[][] PATTERN_34 = new int[][]{
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {0, 0, 1, 1},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_35 = new int[][]{
            {1, 0, 0, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 0},
            {1, 0, 0, 0},
    };

    public static final int[][] PATTERN_36 = new int[][]{
            {0, 1, 0, 1},
            {0, 1, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 0, 0},
    };

    public static final int[][] PATTERN_37 = new int[][]{
            {0, 0, 1, 1},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {1, 0, 1, 0},
    };

    public static final int[][] PATTERN_38 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 1},
            {0, 1, 0, 1},
            {1, 0, 0, 1},
    };

    public static final int[][] PATTERN_39 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_40 = new int[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_41 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    };

    public static final int[][] PATTERN_42 = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    };

    public static final int[][] PATTERN_43 = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
    };

    public static final int[][] PATTERN_44 = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
    };

    public static final int[][] PATTERN_45 = new int[][]{
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
    };

    public static final int[][] PATTERN_46 = new int[][]{
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
    };

    public static final int[][] PATTERN_47 = new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
    };

    public static final int[][] PATTERN_48 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_49 = new int[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
    };

    public static final int[][] PATTERN_50 = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
    };

    //ACCIO
    public static final int[][] PATTERN_51 = new int[][]{
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
    };

    //COLLOPORTUS
    public static final int[][] PATTERN_52 = new int[][]{
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 1, 1, 1},
            {0, 1, 0, 0},
    };

    //EPISKEY
    public static final int[][] PATTERN_53 = new int[][]{
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {0, 1, 1, 0},
    };

    //LOCOMOTOR
    public static final int[][] PATTERN_54 = new int[][]{
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0},
    };

    public static final int PATTERN_QTY = 54;

    public static final int SINGLES = 60;
    public static final int DOUBLES = 140;
    private int SPECIAL = 220;
    public static final int TRIPLES = 250;

    private static final int START_TIPLES = 1;
    private static final int END_TIPLES = 8;
    private static final int START_DOUBLES = 9;
    private static final int END_DOUBLES = 40;
    private static final int START_SINGLES = 41;
    private static final int END_SINGLES = 50;
    private static final int START_SPECIALS = 51;
    private static final int END_SPECIALS = PATTERN_QTY;


    public Calculator() {
    }

    public double calculateArraysWithPattern(int[][] pattern, double[][] classesTimes, double[][] nonClassesTimes, double[][] pointsClasses, double[][] pointsNonClasses, double[][] classesTimesInPattern, double[][] nonClassesTimesOutPattern, double[][] pointsInPattern, double[][] pointsOutPattern) {
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

    public Double sumArrayValues(double[][] array) {
        double sum = 0;

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                sum += array[row][column];
            }
        }

        return sum;
    }

    public Double getBiggestValue(double[][] array) {
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

    public void setSpecialPatternValue (int value) {
        this.SPECIAL = value;
    }

    public int getSpecialPatternValue () {
        return SPECIAL;
    }

    private void setArrayValues(double[][] classTimeRequired, int row, int column, EditText[][] editTextArray, String s) {
        try {
            double classesHoursValue = Double.parseDouble(editTextArray[row][column].getText().toString());
            //Log.d("HP", s + String.format("%.2f", classesHoursValue));
            classTimeRequired[row][column] = classesHoursValue;
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
    }

    public void clearArray(double[][] array){
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                array[row][column] = 0;
            }
        }
    }

    public double getMultiplicator (int patternNumber) {
        double multiplier = 0;

        if (patternNumber >= START_TIPLES && patternNumber <= END_TIPLES) {
            multiplier = TRIPLES;
        } else if (patternNumber >= START_DOUBLES && patternNumber <= END_DOUBLES) {
            multiplier = DOUBLES;
        } else if (patternNumber >= START_SINGLES && patternNumber <= END_SINGLES) {
            multiplier = SINGLES;
        } else if (patternNumber >= START_SPECIALS && patternNumber <= END_SPECIALS) {
            multiplier = SPECIAL;
        }

        return multiplier;
    }

    public String getPatternType (int patternNumber) {
        String patternType = null;

        if (patternNumber >= START_TIPLES && patternNumber <= END_TIPLES) {
            patternType = "Triple";
        } else if (patternNumber >= START_DOUBLES && patternNumber <= END_DOUBLES) {
            patternType = "Double";
        } else if (patternNumber >= START_SINGLES && patternNumber <= END_SINGLES) {
            patternType = "Single";
        } else if (patternNumber >= START_SPECIALS && patternNumber <= END_SPECIALS) {
            patternType = "Special";
        }

        return patternType;
    }

    public int[][] getTestPattern (int patternNumber) {
        switch (patternNumber) {
            case 1: return PATTERN_1;
            case 2: return PATTERN_2;
            case 3: return PATTERN_3;
            case 4: return PATTERN_4;
            case 5: return PATTERN_5;
            case 6: return PATTERN_6;
            case 7: return PATTERN_7;
            case 8: return PATTERN_8;
            case 9: return PATTERN_9;
            case 10: return PATTERN_10;
            case 11: return PATTERN_11;
            case 12: return PATTERN_12;
            case 13: return PATTERN_13;
            case 14: return PATTERN_14;
            case 15: return PATTERN_15;
            case 16: return PATTERN_16;
            case 17: return PATTERN_17;
            case 18: return PATTERN_18;
            case 19: return PATTERN_19;
            case 20: return PATTERN_20;
            case 21: return PATTERN_21;
            case 22: return PATTERN_22;
            case 23: return PATTERN_23;
            case 24: return PATTERN_24;
            case 25: return PATTERN_25;
            case 26: return PATTERN_26;
            case 27: return PATTERN_27;
            case 28: return PATTERN_28;
            case 29: return PATTERN_29;
            case 30: return PATTERN_30;
            case 31: return PATTERN_31;
            case 32: return PATTERN_32;
            case 33: return PATTERN_33;
            case 34: return PATTERN_34;
            case 35: return PATTERN_35;
            case 36: return PATTERN_36;
            case 37: return PATTERN_37;
            case 38: return PATTERN_38;
            case 39: return PATTERN_39;
            case 40: return PATTERN_40;
            case 41: return PATTERN_41;
            case 42: return PATTERN_42;
            case 43: return PATTERN_43;
            case 44: return PATTERN_44;
            case 45: return PATTERN_45;
            case 46: return PATTERN_46;
            case 47: return PATTERN_47;
            case 48: return PATTERN_48;
            case 49: return PATTERN_49;
            case 50: return PATTERN_50;
            case 51: return PATTERN_51;
            case 52: return PATTERN_52;
            case 53: return PATTERN_53;
            case 54: return PATTERN_54;
            default: return null;
        }
    }
}
