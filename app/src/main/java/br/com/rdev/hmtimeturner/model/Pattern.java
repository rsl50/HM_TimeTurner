package br.com.rdev.hmtimeturner.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pattern implements Parcelable {

    private final String type;
    private final String imagem;
    private final double expected_hours;

    public Pattern(String local, String imagem, double expected_points) {
        this.type = local;
        this.imagem = imagem;
        this.expected_hours = expected_points;
    }

    private Pattern (Parcel from) {
        type = from.readString();
        imagem = from.readString();
        expected_hours = from.readDouble();
    }

    public static final Parcelable.Creator<Pattern>
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

    public String getImagem() {
        return imagem;
    }

    public double getExpected_hours() {
        return expected_hours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(imagem);
        dest.writeDouble(expected_hours);
    }
}