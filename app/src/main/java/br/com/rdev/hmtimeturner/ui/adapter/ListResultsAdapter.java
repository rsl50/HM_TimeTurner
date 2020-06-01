package br.com.rdev.hmtimeturner.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    public void onBindViewHolder (@NonNull PatternViewHolder holder,int position){
        Pattern nota = patterns.get(position);
        holder.vincula(nota);
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
        private final TextView expected_hours;
        private Pattern pattern;

        public PatternViewHolder(@NonNull View itemView) {
            super(itemView);
            pattern_image = itemView.findViewById(R.id.item_result_pattern);
            pattern_type = itemView.findViewById(R.id.item_result_value_pattern_type);
            expected_hours = itemView.findViewById(R.id.item_result_value_expected_time);
        }

        public void vincula(Pattern pattern) {
            this.pattern = pattern;
            preencheCampos(pattern);
        }

        private void preencheCampos(Pattern pattern) {
            Drawable drawableImagemPacote = ResourceUtil.devolveDrawable(context, pattern.getImagem());
            pattern_image.setImageDrawable(drawableImagemPacote);

            pattern_type.setText(pattern.getType());
            expected_hours.setText(String.format("%.2f", pattern.getExpected_hours()));
            }
        }
}

