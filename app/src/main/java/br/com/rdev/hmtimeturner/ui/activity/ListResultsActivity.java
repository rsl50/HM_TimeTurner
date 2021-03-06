package br.com.rdev.hmtimeturner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.rdev.hmtimeturner.R;
import br.com.rdev.hmtimeturner.dao.PatternDAO;
import br.com.rdev.hmtimeturner.model.Pattern;
import br.com.rdev.hmtimeturner.ui.adapter.ListResultsAdapter;

import static br.com.rdev.hmtimeturner.ui.activity.MainActivityConstants.KEY_RESULTS;

public class ListResultsActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Resultados";
    private ListResultsAdapter adapter;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        setTitle(TITLE_APPBAR);

        btnReturn = findViewById(R.id.button_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadReceivedResults();
    }

    private void loadReceivedResults() {
        List<Pattern> allResults = pegaTodasNotas();

        Intent receivedData = getIntent();
        if (receivedData.hasExtra(KEY_RESULTS)) {
            ArrayList<Pattern> resultPatterns = receivedData.getParcelableArrayListExtra("result");
            allResults.addAll(resultPatterns);

            configuraRecyclerView(allResults);
        }
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

    private void configuraAdapter(List<Pattern> resultsPatterns, RecyclerView allResults) {
        adapter = new ListResultsAdapter(this, resultsPatterns);
        allResults.setAdapter(adapter);
    }
}
