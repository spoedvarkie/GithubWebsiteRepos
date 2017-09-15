package com.example.leandri.itrw_324;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        final EditText editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        final EditText editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        final EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        final EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        final EditText editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        final EditText editTextContactNum = (EditText) findViewById(R.id.editTextContctNum);
        final EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        final Context context = this;

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName;
                String lastName;
                String username;
                String password;
                String confirmPassword;
                String contactNum;
                String email;

                TextView txtViewIncorrectInfo = (TextView) findViewById(R.id.txtViewIncorrectInfo);

                if(!(firstName = editTextFirstName.getText().toString()).equals("") &&
                        !(lastName = editTextLastName.getText().toString()).equals("") &&
                        !(username = editTextUsername.getText().toString()).equals("") &&
                        !(password = editTextPassword.getText().toString()).equals("") &&
                        !(confirmPassword = editTextConfirmPassword.getText().toString()).equals("") &&
                        !(contactNum = editTextContactNum.getText().toString()).equals("") &&
                        !(email = editTextEmail.getText().toString()).equals("")){
                    if(password.equals(confirmPassword)){
                        if(contactNum.length() == 10){
                            BackgroundWorker backgroundWorker = new BackgroundWorker(context);
                            backgroundWorker.execute("register", "", username, password, firstName, lastName, contactNum, email, "");

                            boolean isDone = false;
                            do{
                                isDone = backgroundWorker.getIsDone();
                            } while (isDone == false);

                            boolean noResults = backgroundWorker.getNoResults();
                            if(noResults == false){
                                Intent registerIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(registerIntent);
                            }
                            else{
                                txtViewIncorrectInfo.setText("Register unsuccessful");
                            }
                        }
                        else{
                            txtViewIncorrectInfo.setText("Invalid contact number");
                        }
                    }
                    else{
                        txtViewIncorrectInfo.setText("Passwords do not match");
                    }
                }
                else{
                    txtViewIncorrectInfo.setText("All fields are required");
                }
            }
        });


    }
}
