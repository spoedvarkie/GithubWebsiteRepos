package com.example.leandri.itrw_324;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        final EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        final EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        TextView txtViewRegister = (TextView) findViewById(R.id.txtViewRegister);

        final Context context = this;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                BackgroundWorker backgroundWorker = new BackgroundWorker(context);
                backgroundWorker.execute("login", "", username, password, "", "", "", "", "");

                boolean isDone = false;
                do{
                    isDone = backgroundWorker.getIsDone();
                }while (isDone == false);

                boolean noResults = backgroundWorker.getNoResults();
                if(noResults == false) {
                    Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(loginIntent);
                }
                else {
                    TextView txtViewIncorrectCredentials = (TextView) findViewById(R.id.txtViewIncorrectCredentials);
                    txtViewIncorrectCredentials.setText("Incorrect credentials");
                }


            }
        });

        txtViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterIntent = new Intent(getApplicationContext(), Register_Activity.class);
                startActivity(goToRegisterIntent);
            }
        });
    }
}
