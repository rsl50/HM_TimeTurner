package br.com.rdev.hmtimeturner.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.dao.PatternDAO;
import br.com.rdev.hmtimeturner.model.Pattern;
import br.com.rdev.hmtimeturner.ui.adapter.ListResultsAdapter;

public class ListResultsActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Resultados";
    private ListResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        setTitle(TITLE_APPBAR);
        List<Pattern> allResults = pegaTodasNotas();

//        Pattern pattern1 = new Pattern("Single", "pattern_1", 2.0);
//        Pattern pattern2 = new Pattern("Double", "pattern_2", 3.0);
//        Pattern pattern3 = new Pattern("Triple", "pattern_3", 4.0);
//        Pattern pattern4 = new Pattern("Special", "pattern_4", 6.0);
//        allResults.add(pattern1);
//        allResults.add(pattern2);
//        allResults.add(pattern3);
//        allResults.add(pattern4);

        Intent receivedData = getIntent();
        if (receivedData.hasExtra("result")) {

            ArrayList<Pattern> resultPatterns = receivedData.getParcelableArrayListExtra("result");
            //Pattern receivedResult = receivedData.getParcelableExtra("result");
            allResults.addAll(resultPatterns);
        }


        configuraRecyclerView(allResults);
    }

    private List<Pattern> pegaTodasNotas() {
        PatternDAO dao = new PatternDAO();
        return dao.todos();
    }

    private void configuraRecyclerView(List<Pattern> allResults) {
        RecyclerView listResults = findViewById(R.id.list_results_recyclerview);
        //LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(ListResultsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        //listResults.setLayoutManager(horizontalLayoutManagaer);
        configuraAdapter(allResults, listResults);
        //configuraItemTouchHelper(listResults);
    }

    private void configuraAdapter(List<Pattern> todasNotas, RecyclerView allResults) {
        adapter = new ListResultsAdapter(this, todasNotas);
        allResults.setAdapter(adapter);
    }
}
