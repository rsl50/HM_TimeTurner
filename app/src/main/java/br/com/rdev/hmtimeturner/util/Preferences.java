package br.com.rdev.hmtimeturner.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static java.util.Locale.US;

public class Preferences {

    public Preferences() {
    }

    public static void setPrefs(String key, String value, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("user_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs(String key, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("user_preferences", MODE_PRIVATE);
        return sharedpreferences.getString(key, "notfound");
    }

    public static void setPrefs(String key, boolean value, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("user_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setArrayPrefs(String arrayName, ArrayList<String> array, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("user_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public static ArrayList<String> getArrayPrefs(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("user_preferences", MODE_PRIVATE);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }

    public static void saveArrayToPreferences(String arrayName, double[][] array, Context mContext) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                arrayList.add(String.format(US, "%.1f", array[row][column]));
                //Log.d("SAVE", "GRAVA["+row+"]"+"["+column+"]:"+array[row][column]);
            }
        }

        setArrayPrefs(arrayName, arrayList, mContext);
    }

    public static void getArrayFromPreferences(String arrayName, EditText[][] fieldArray, Context mContext) {
        ArrayList<String> arrayList = getArrayPrefs(arrayName, mContext);

        if (arrayList.size() > 0) {
            int arrayIndex = 0;

            for (int row = 0; row < 4; row++) {
                for (int column = 0; column < 4; column++) {

                    double doubleValue = Double.parseDouble(arrayList.get(arrayIndex));
                    String stringValue = "0";

                    if (!arrayName.equalsIgnoreCase("nonClassesTimes")) {
                        stringValue = String.format(US, "%.0f", doubleValue);
                    } else {
                        if (doubleValue % 1 == 0) {
                            stringValue = String.format(US, "%.0f", doubleValue);
                        } else {
                            stringValue = String.format(US, "%.1f", doubleValue);
                        }
                    }

                    fieldArray[row][column].setText(stringValue);

                    //Log.d("SAVE", "LENDO["+row+"]"+"["+column+"]:"+arrayList.get(arrayIndex));
                    arrayIndex++;
                }
            }
        }
    }

    public boolean contains(String specialSelected, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("user_preferences", MODE_PRIVATE);

        return prefs.contains(specialSelected);
    }
}
