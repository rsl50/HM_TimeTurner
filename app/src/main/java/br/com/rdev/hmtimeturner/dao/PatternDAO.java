package br.com.rdev.hmtimeturner.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.rdev.hmtimeturner.model.Pattern;

public class PatternDAO {

    private final static ArrayList<Pattern> results = new ArrayList<>();

    public List<Pattern> lista() {
        List<Pattern> pacotes = new ArrayList<>(Arrays.asList(
                new Pattern("Single", "pattern_1", 2.0, 0, 0, 0, 0),
                new Pattern("Double", "pattern_2", 3.0, 0, 0, 0, 0),
                new Pattern("Triple", "pattern_3", 4.0, 0, 0, 0, 0),
                new Pattern("Special", "pattern_4", 6.0, 0, 0, 0, 0)));
        return pacotes;
    }

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
