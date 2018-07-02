package com.example.hp.innovex;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.example.hp.innovex.SignUpActivitiy.PREFS_NAME;
import static com.example.hp.innovex.SignUpActivitiy.PREF_PASSWORD;
import static com.example.hp.innovex.SignUpActivitiy.PREF_UNAME;


public class FacebookLogin extends AppCompatActivity {

    RelativeLayout layout1,layout2;
    Button business;
    ImageView logo;
    Button login;
    EditText userName_et;
    EditText password_et;
    TextView signUp;
    CallbackManager callbackManager;
    Button facebook_button;
    Button loginButton;
    ProgressDialog mDialog;
    private ProfileTracker profileTracker;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

        Intent h=new Intent(FacebookLogin.this,MainActivity.class);
        startActivity(h);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            ImageView iv = (ImageView) findViewById(R.id.left);
            int height = 50;

            logo.getLayoutParams().height = 120;

            logo.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;

            layout1.setVisibility(View.VISIBLE);
            business.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.loginfb_button);
        facebook_button=findViewById(R.id.fb_button);
        layout1 = findViewById(R.id.layout1);
        login = findViewById(R.id.login_button);
        layout2 = findViewById(R.id.layout2);
        business = findViewById(R.id.business);
        logo = findViewById(R.id.logo);
        userName_et = findViewById(R.id.username);
        password_et = findViewById(R.id.password);
        signUp = findViewById(R.id.signup);
        handler.postDelayed(runnable, 2000);


        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged (
                    Profile oldProfile,
                    Profile currentProfile){
                Log.i("profileTracker", "profileTracker");
            }
        };
        Profile profile = Profile.getCurrentProfile();
        if(   (profile != null) ){
            Toast.makeText(this, "Already logged in  through facebook", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(FacebookLogin.this, MainActivity.class);
            startActivity(i);
        };

        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == facebook_button) {
                    loginButton.performClick();
                    loginButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            facebook(view);
                           // Toast.makeText(FacebookLogin.this, "facebook clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        printKeyHash();
//        if(!userName_et.getText().equals("") && !password_et.getText().equals("")){
//
//            Toast.makeText(this, "Press LOGIN button ..U  are already logged in!!!", Toast.LENGTH_SHORT).show();
//        }



        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);


        final String UnameValue = settings.getString(PREF_UNAME, "");
        final String PasswordValue = settings.getString(PREF_PASSWORD, "");
        userName_et.setText(UnameValue);
        password_et.setText(PasswordValue);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Toast.makeText(FacebookLogin.this, "going to sign up activity", Toast.LENGTH_SHORT).show();

                if (UnameValue.equals("") || PasswordValue.equals("")) {
                    Intent start = new Intent(FacebookLogin.this, SignUpActivitiy.class);
                    startActivity(start);
                } else {
                    Toast.makeText(FacebookLogin.this, "You are already logged in..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = userName_et.getText().toString();
                String pass = password_et.getText().toString();

                if (name.equals(UnameValue) && pass.equals(PasswordValue) && name.length() != 0 && pass.length() != 0) {
                    Toast.makeText(FacebookLogin.this, "LOGIN successfull", Toast.LENGTH_SHORT).show();

                    Intent gotomain = new Intent(FacebookLogin.this, MainActivity.class);
                    startActivity(gotomain);
                } else {

                    Toast.makeText(FacebookLogin.this, "Enter correct data to LOGIN", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void facebook(View v) {

        if (v == facebook_button) {
            LoginButton loginButton = (LoginButton) findViewById(R.id.loginfb_button);
            loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    mDialog = new ProgressDialog(FacebookLogin.this);
                    mDialog.setMessage("Retreiving data.....");
                    mDialog.show();

                    String accessToken = loginResult.getAccessToken().getToken();

                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            mDialog.dismiss();
                            getData(object);

                            Log.d("response", response.toString());

                        }
                    });

                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,email");
                    request.setParameters(parameters);
                    request.executeAsync();

                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {


                }
            });

            if (AccessToken.getCurrentAccessToken() != null) {

                Intent in = new Intent(FacebookLogin.this,MainActivity.class);
                startActivity(in);

            }

        }
    }
    private void getData(JSONObject object) {

        try{
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");

            userName_et.setText(object.getString("email")+"");
            password_et.setText(object.getString("id")+"");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void printKeyHash() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.hp.facebooklogin", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}