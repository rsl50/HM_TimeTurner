package br.com.rdev.hmtimeturner.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.model.Pattern;
import br.com.rdev.hmtimeturner.util.ResourceUtil;

public class ListResultsAdapter extends RecyclerView.Adapter<ListResultsAdapter.PatternViewHolder> {

    private final List<Pattern> patterns;
    private final Context context;

    public ListResultsAdapter(Context context, List <Pattern> patterns) {
        this.patterns = patterns;
        this.context = context;
    }

    @NonNull
    @Override
    public ListResultsAdapter.PatternViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
        return new PatternViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder (@NonNull PatternViewHolder holder, int position){
        Pattern resultPattern = patterns.get(position);

        // Paint top result cards
        if (resultPattern.getIsTop() == 1){
            holder.constraintLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLineDivider));
        } else {
            holder.constraintLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLightDialogBackground));
        }

        holder.vincula(resultPattern);
    }

    @Override
    public int getItemCount () { return patterns.size(); }

    public void adiciona (Pattern pattern) {
        patterns.add(pattern);
        notifyDataSetChanged();
    }

    public void altera (int posicao, Pattern pattern) {
        patterns.set(posicao, pattern);

        //Adiciona efeito de transição/animação ao alterar item da lista
        notifyItemChanged(posicao);
    }

    public void remove ( int posicao) {
        patterns.remove(posicao);

        //Adiciona efeito de transição/animação ao remover item da lista
        notifyItemRemoved(posicao);
    }

    public void troca (int posicaoInicial, int posicaoFinal) {
        Collections.swap(patterns, posicaoInicial, posicaoFinal);

        //Adiciona efeito de transição/animação ao mover item da lista
        notifyItemMoved(posicaoInicial, posicaoFinal);
    }

    class PatternViewHolder extends RecyclerView.ViewHolder {
        private final ImageView pattern_image;
        private final TextView pattern_type;
        private final TextView expected_points_per_hour;
        private final TextView hours_required;
        private final TextView min_points;
        private final TextView min_points_hour;
        private final TextView max_points;
        private final ConstraintLayout constraintLayout;
        private Pattern pattern;

        public PatternViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            pattern_image = itemView.findViewById(R.id.item_result_pattern);
            pattern_type = itemView.findViewById(R.id.item_result_value_pattern_type);
            expected_points_per_hour = itemView.findViewById(R.id.item_result_value_expected_time);

            hours_required = itemView.findViewById(R.id.item_result_value_hours);
            min_points = itemView.findViewById(R.id.item_result_value_min_points);
            min_points_hour = itemView.findViewById(R.id.item_result_value_min_points_per_hour);
            max_points = itemView.findViewById(R.id.item_result_value_max_points);
        }

        public void vincula(Pattern pattern) {
            this.pattern = pattern;
            preencheCampos(pattern);
        }

        private void preencheCampos(Pattern pattern) {
            Drawable drawableImagemPacote = ResourceUtil.devolveDrawable(context, pattern.getPatternImage());
            pattern_image.setImageDrawable(drawableImagemPacote);

            pattern_type.setText(pattern.getType());
            expected_points_per_hour.setText(String.format("%.0f", pattern.getExpectedPointsPerHour()));

            hours_required.setText(String.format("%.0f", pattern.getHours()));
            min_points.setText(String.format("%.0f", pattern.getMinPoints()));
            min_points_hour.setText(String.format("%.0f", pattern.getMinPointsPerHour()));
            max_points.setText(String.format("%.0f", pattern.getMaxPoints()));
        }
    }
}

