package br.com.rdev.hmtimeturner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.rdev.hmtimeturner.R;

public class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
    int patternImage[];
    String[] patternsNames;
    LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, int[] patternImage, String[] patternsNames) {
        this.context = context;
        this.patternImage = patternImage;
        this.patternsNames = patternsNames;
        inflater = (LayoutInflater.from(context));
    }

    @Override
        public int getCount() {
            return patternImage.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.custom_spinner_items, null);
            ImageView icon = view.findViewById(R.id.patternImage);
            TextView names = view.findViewById(R.id.patternName);
            icon.setImageResource(patternImage[i]);
            names.setText(patternsNames[i]);
            return view;
        }
}
