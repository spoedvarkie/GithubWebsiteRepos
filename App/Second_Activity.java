package com.example.leandri.itrw_324;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Second_Activity extends AppCompatActivity {

    private static ArrayList<String> textbookIDsArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);

        TextView txtViewSearchResult = (TextView) findViewById(R.id.txtViewSearchResult);
        ListView listViewSearch = (ListView) findViewById(R.id.listViewSearch);

        if (getIntent().hasExtra("com.example.leandri.ITRW_324.SearchContent")) {

            String searchContent = getIntent().getExtras().getString("com.example.leandri.ITRW_324.SearchContent");
            String searchDisplayText = "Showing results for '" + searchContent + "'";

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute("search", searchContent, "", "", "", "", "", "", "");

            boolean isDone = false;
            do {
                isDone = backgroundWorker.getIsDone();
            } while(isDone == false);

            boolean noResults = backgroundWorker.getNoResults();
            if(noResults == false) {
                ArrayList<String> titlesArrayList = backgroundWorker.getTitlesArrayList();
                ArrayList<String> editionsArrayList = backgroundWorker.getEditionsArrayList();
                ArrayList<String> authorsArrayList = backgroundWorker.getAuthorsArrayList();
                ArrayList<String> pricesArrayList = backgroundWorker.getPriceArrayList();
                textbookIDsArrayList = backgroundWorker.getTextbookIDsArrayList();

                ItemAdapter itemAdapter = new ItemAdapter(this, titlesArrayList, editionsArrayList, pricesArrayList, authorsArrayList);
                listViewSearch.setAdapter(itemAdapter);
            }
            else
                searchDisplayText += "\nNo results found";

            txtViewSearchResult.setText(searchDisplayText);
        }

        listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewTextbookIntent = new Intent(getApplicationContext(), Advert_Activity.class);
                String textbookID = textbookIDsArrayList.get(position).toString();
                viewTextbookIntent.putExtra("com.example.leandri.ITRW_324.textbookID", textbookID);

                startActivity(viewTextbookIntent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(this, Profile_Activity.class));
                return true;
            case R.id.logout:
                startActivity(new Intent(this, Login_Activity.class));
                return true;
            case R.id.main_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
