package com.example.hp.innovex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivitiy extends AppCompatActivity {

    public static final String PREFS_NAME = "preferences";
    public static final String PREF_UNAME = "Username";
    public static final String PREF_PASSWORD = "Password";
    public static final String PREF_EMAIL = "Email";


    public String usernameValue = "";
    public String passwordValue = "";
    public String confirmPasswordValue = "";
    public String emailValue = "";


    CheckBox checkBox;
    EditText username;
    EditText password;
    EditText password_confirm;
    EditText email;
    Button Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Intent start = getIntent();

        username = findViewById(R.id.Username);
        password_confirm = findViewById(R.id.R_password);
        password = findViewById(R.id.Password);
        email = findViewById(R.id.Email);
        checkBox=findViewById(R.id.checkbox);
        Signup=findViewById(R.id.register);


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();

    }


    private void savePreferences()
    {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();


        usernameValue = username.getText().toString();
        passwordValue = password.getText().toString();
        confirmPasswordValue = password_confirm.getText().toString();
        emailValue = email.getText().toString();

//            System.out.println("onPause save name: " + UnameValue);
//            System.out.println("onPause save password: " + PasswordValue);


        if(passwordValue.equals("")|| usernameValue.equals("") || emailValue.equals("") || passwordValue.length()<4 || usernameValue.length()<4 || emailValue.length()<12){
            Toast.makeText(this, "INVALID FIELD...", Toast.LENGTH_SHORT).show();

            if(passwordValue.equals("") || passwordValue.length()<4){
                password.setText("");
            }

            if(emailValue.equals("") || emailValue.length()<15){
                email.setText("");

            }
            if(username.equals("") || usernameValue.length()<4){
                username.setText("");
            }
        }
        else if(!passwordValue.equals(confirmPasswordValue) ||  passwordValue.equals("")){
            Toast.makeText(SignUpActivitiy.this,"Password do not match...",Toast.LENGTH_SHORT).show();
            password.setText("");
            password_confirm.setText("");
        }
         else if (passwordValue.equals(confirmPasswordValue) && passwordValue!="") {

            if (checkBox.isChecked()) {
                editor.putString(PREF_UNAME, usernameValue);
                editor.putString(PREF_EMAIL, emailValue);
                editor.putString(PREF_PASSWORD, passwordValue);
                editor.commit();
               // Toast.makeText(this, "data saved successfully", Toast.LENGTH_SHORT).show();
                Intent startProject = new Intent(SignUpActivitiy.this,MainActivity.class);
                startActivity(startProject);

            } else{
                Toast.makeText(this, "please agree terms of services", Toast.LENGTH_SHORT).show();
            }
        }

    }



}
