package mx.com.omnius.yolabor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;

import mx.com.omnius.yolabor.Model.UserModel;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;
import mx.com.omnius.yolabor.utils.Constants;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import static mx.com.omnius.yolabor.YolaborApplication.requestQueue;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AsyncTaskCompleteListener, Response.ErrorListener {
    private static String Name;

    View bg;
    EditText txtUsername, txtPassword;
    Button btnSignUp;

    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;
    private Button mbtnFacebook;

private static final String TAG ="FACELOG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initialize Firebase Auth
         mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = (EditText) findViewById(R.id.txtUername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("Sign In");
        }

        bg = findViewById(R.id.activity_loginsignup_style12);

        String url = BuildConfig.IMAGE_URL + "login-signup/style-12/Login-Register-12-960.jpg";

        Glide.with(this)
                .load(url)
                .thumbnail(0.01f)
                .centerCrop()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        bg.setBackground(resource);
                    }
                });


        // Initialize Facebook Login button

        mbtnFacebook = (Button) findViewById(R.id.btnSignFacebook);

        mCallbackManager = CallbackManager.Factory.create();

        mbtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });

            }
        });


    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // Pass the activity result back to the Facebook SDK
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null ){
            updateUI();
        }

    }

    private void updateUI() {
       // Toast.makeText(LoginActivity.this, "You are login", Toast.LENGTH_LONG).show();
        Intent Mapa = new Intent(getApplicationContext(), MenuDrawer.class);
        startActivity(Mapa);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loginsignup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_search:
                Toast.makeText(this, "action search clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "action setting clicked!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtForgotPassword:
                Toast.makeText(this, "Forgot Password clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSignIn:
                //Toast.makeText(this, "Sign In button clicked!", Toast.LENGTH_SHORT).show();
                Intent SingIn = new Intent(getApplicationContext(), SinginActivity.class);
                startActivity(SingIn);
                break;
            case R.id.btnSignUp:
                //Toast.makeText(this, "Sign Up button clicked!", Toast.LENGTH_SHORT).show();
                //Intent Mapa = new Intent(getApplicationContext(), MenuDrawer.class);
                //startActivity(Mapa);
                login();
                break;
            /*case R.id.login_button:
                Toast.makeText(this, "Sign In with Facebook clicked!", Toast.LENGTH_SHORT).show();
                break;*/
            default:
                break;
        }
    }

    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case Constants.ServiceCode.LOGIN:
                Gson gson = new Gson();
                UserModel user = gson.fromJson(response, UserModel.class);
                if (user != null){
                    if (user.getEmail().equals(txtUsername.getText().toString())&& user.getPassword().equals(txtPassword.getText().toString())){
                            YolaborApplication.preferenceHelper.putClientId(user.getIdClient());
                            YolaborApplication.preferenceHelper.putFirstName(user.getFirstname());
                            YolaborApplication.preferenceHelper.putLastName(user.getLastname());
                            YolaborApplication.preferenceHelper.putEmail(user.getEmail());
                            YolaborApplication.preferenceHelper.putPassword((user.getPassword()));
                            YolaborApplication.preferenceHelper.putPhone(user.getPhone());
                            YolaborApplication.preferenceHelper.putItin(user.getItin());
                            YolaborApplication.preferenceHelper.putBirthdate(user.getBirthdate());
                            YolaborApplication.preferenceHelper.putGender(user.getGender());
                            YolaborApplication.preferenceHelper.putCompany(user.getCompany());

                            Preferences.savePreferenceString(LoginActivity.this,user.getIdClient(),Preferences.PREFERENCE_USUARIO_LOGIN);

                        Intent Mapa = new Intent(getApplicationContext(), MenuDrawer.class);
String x = YolaborApplication.preferenceHelper.getEmail();
                        Log.e("XXX AQUI XXX", x);
                        startActivity(Mapa);
                    }
                }else{
                  Toast.makeText(this, "Email or Password Invalid!!", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    private void login() {
        setName("m");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(Constants.URL, Constants.ServiceType.LOGIN

                + Constants.Params.EMAIL + "="
                + txtUsername.getText().toString() + "&"
                + Constants.Params.PASSWORD + "="
                + txtPassword.getText().toString());

        requestQueue.add(new VolleyHttpRequest(Request.Method.GET, map,
                Constants.ServiceCode.LOGIN, this, this));
        Toast.makeText(this, "LOGIN METOD", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Toast.makeText(this, "Error al entrar en contacto con el servidor "+ error, Toast.LENGTH_SHORT).show();
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }


}
