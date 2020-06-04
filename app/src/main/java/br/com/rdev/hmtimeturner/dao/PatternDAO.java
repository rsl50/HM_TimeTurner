package br.com.rdev.hmtimeturner.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.rdev.hmtimeturner.model.Pattern;

public class PatternDAO {

    private final static ArrayList<Pattern> results = new ArrayList<>();

    public List<Pattern> todos() {
        return (List<Pattern>) results.clone();
    }

    public void insere(Pattern... patterns) {
        PatternDAO.results.addAll(Arrays.asList(patterns));
    }

    public void altera(int posicao, Pattern result) {
        results.set(posicao, result);
    }

    public void remove(int posicao) {
        results.remove(posicao);
    }

    public void troca(int posicaoInicio, int posicaoFim) {
        Collections.swap(results, posicaoInicio, posicaoFim);
    }

    public void removeTodos() {
        results.clear();
    }
}
