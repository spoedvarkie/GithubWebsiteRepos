
package com.example.leandri.itrw_324;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Advert_Activity extends AppCompatActivity {
    private String sellerUserID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_);

        TextView txtViewTitle = (TextView) findViewById(R.id.txtViewTitle);
        TextView txtViewAuthor = (TextView) findViewById(R.id.txtViewAuthor);
        TextView txtViewPrice = (TextView) findViewById(R.id.txtViewPrice);
        TextView txtViewComments = (TextView) findViewById(R.id.txtViewComments);
        TextView txtVIewSellerUsername = (TextView) findViewById(R.id.txtViewSellerUsername);
        Button btnViewSeller = (Button) findViewById(R.id.btnViewSeller);

        final Context context = this;

        if(getIntent().hasExtra("com.example.leandri.ITRW_324.textbookID")){
            String textbookID = getIntent().getStringExtra("com.example.leandri.ITRW_324.textbookID");

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute("textbook", "", "", "", "", "", "", "", textbookID);

            boolean isDone = false;
            do{
                isDone = backgroundWorker.getIsDone();
            } while(isDone == false);

            txtViewTitle.setText(backgroundWorker.getTextbookTitle());
            txtViewAuthor.setText(backgroundWorker.getTextbookAuthor());
            txtViewPrice.setText("R " + backgroundWorker.getTextbookPrice());
            txtViewComments.setText(backgroundWorker.getTextbookComments());
            txtVIewSellerUsername.setText(backgroundWorker.getSellerUsername());
            sellerUserID = backgroundWorker.getSellerUserID();
        }

        btnViewSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewSellerDetailsIntent = new Intent(getApplicationContext(), Profile_Activity.class);
                viewSellerDetailsIntent.putExtra("com.example.leandri.ITRW_324.sellerUserID", sellerUserID);

                startActivity(viewSellerDetailsIntent);
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
