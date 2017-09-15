package com.example.leandri.itrw_324;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewHome = (ListView) findViewById(R.id.listViewHome);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        final EditText txtBoxSearch = (EditText) findViewById(R.id.txtBoxSearch);

        final Context context = this;
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("main", "", "", "", "", "", "", "", "");

        boolean isDone = false;
        do {
            isDone = backgroundWorker.getIsDone();
        } while(isDone == false);

        ArrayList<String> titlesArrayList = backgroundWorker.getTitlesArrayList();
        ArrayList<String> editionsArrayList = backgroundWorker.getEditionsArrayList();
        ArrayList<String> authorsArrayList = backgroundWorker.getAuthorsArrayList();
        ArrayList<String> pricesArrayList = backgroundWorker.getPriceArrayList();
        final ArrayList<String> textbookIDsArrayList = backgroundWorker.getTextbookIDsArrayList();

        ItemAdapter itemAdapter = new ItemAdapter(this, titlesArrayList, editionsArrayList, pricesArrayList, authorsArrayList);
        listViewHome.setAdapter(itemAdapter);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent = txtBoxSearch.getText().toString();
                Intent searchIntent = new Intent(getApplicationContext(), Second_Activity.class);
                searchIntent.putExtra("com.example.leandri.ITRW_324.SearchContent", searchContent);

                startActivity(searchIntent);
            }
        });

        listViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
