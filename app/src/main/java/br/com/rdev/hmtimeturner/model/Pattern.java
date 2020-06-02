package br.com.rdev.hmtimeturner.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pattern implements Parcelable {

    private String type;
    private String image;
    private double expectedPointsPerHour;
    private final double minPoints;
    private final double minPointsPerHour;
    private final double maxPoints;
    private final double hours;

    public Pattern(String patternType, String patternImage, double expectedPointsPerHour, double minPoints, double minPointsPerHour, double maxPoints, double hours) {
        this.type = patternType;
        this.image = patternImage;
        this.expectedPointsPerHour = expectedPointsPerHour;
        this.minPoints = minPoints;
        this.minPointsPerHour = minPointsPerHour;
        this.maxPoints = maxPoints;
        this.hours = hours;
    }

    /*public Pattern(String patternType, String patternImage, double expectedPointsPerHour) {
        this.type = patternType;
        this.image = patternImage;
        this.expectedPointsPerHour = expectedPointsPerHour;
        this.minPoints = 0;
        this.minPointsPerHour = 0;
        this.maxPoints = 0;
        this.hours = 0;
    }*/

    private Pattern (Parcel from) {
        type = from.readString();
        image = from.readString();
        expectedPointsPerHour = from.readDouble();
        minPoints = from.readDouble();
        minPointsPerHour = from.readDouble();
        maxPoints = from.readDouble();
        hours = from.readDouble();
    }

    public static final Creator<Pattern>
            CREATOR = new Parcelable.Creator<Pattern>() {

        public Pattern createFromParcel(Parcel in) {
            return new Pattern(in);
        }

        public Pattern[] newArray(int size) {
            return new Pattern[size];
        }
    };

    public String getType() {
        return type;
    }

    public String getPatternImage() {
        return image;
    }

    public double getExpectedPointsPerHour() {
        return expectedPointsPerHour;
    }

    public double getMinPoints() {
        return minPoints;
    }

    public double getMinPointsPerHour() {
        return minPointsPerHour;
    }

    public double getMaxPoints() {
        return maxPoints;
    }

    public double getHours() {
        return hours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(image);
        dest.writeDouble(expectedPointsPerHour);
        dest.writeDouble(minPoints);
        dest.writeDouble(minPointsPerHour);
        dest.writeDouble(maxPoints);
        dest.writeDouble(hours);
    }

    public void readFromParcel(Parcel in) {
        type = in.readString();
        image = in.readString();
        expectedPointsPerHour = in.readDouble();
    }
}