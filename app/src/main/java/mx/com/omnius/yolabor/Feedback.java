package mx.com.omnius.yolabor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import mx.com.omnius.yolabor.Model.CompanyModel;
import mx.com.omnius.yolabor.Model.UserModel;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;
import mx.com.omnius.yolabor.utils.Constants;

import static mx.com.omnius.yolabor.YolaborApplication.requestQueue;

/**
 * Created by omnius on 26/03/18.
 */

public class Feedback extends AppCompatActivity implements AsyncTaskCompleteListener,Response.ErrorListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    RadioGroup rgroupFeedback;
    EditText message;
    Button sendMessage;
    String typeSource;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initToolbar();



        rgroupFeedback = (RadioGroup) findViewById(R.id.radioGroup_feedback);
        message = (EditText) findViewById(R.id.message_feedback);
        sendMessage = (Button) findViewById(R.id.btn_feedback);


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });


        rgroupFeedback.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.raFeedback){
                    typeSource = "F";
                }else if(i == R.id.raSupport){
                    typeSource = "S";
                }
            }
        });
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Support");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case Constants.ServiceCode.SUPPORT:

                Log.e("RESPUESTA SEND MESNSAJE" , response);
              if (response != "0"){

                  AlertDialog.Builder builder = new AlertDialog.Builder(this);
                  builder.setMessage("Message send")
                          .setCancelable(false)
                          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int id) {
                                  Intent menuDraw = new Intent(getApplicationContext(),MenuDrawer.class);
                                  startActivity(menuDraw);
                              }
                          });
                  AlertDialog alert = builder.create();
                  alert.show();
                }


                break;
        }

    }


    private void send(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(Constants.URL, Constants.ServiceType.SUPPORT

                + Constants.Params.TYPE+ "="
                + typeSource + "&"
                + Constants.Params.SOURCE + "="
                + "C" + "&"
                + Constants.Params.IDUSER + "="
                + YolaborApplication.preferenceHelper.getClientid() + "&"
                + Constants.Params.MESSAGE + "="
                + message.getText().toString().replace(" ", "%20") + "&"
                + Constants.Params.EMAIL + "="
                + YolaborApplication.preferenceHelper.getEmail() );



        YolaborApplication.requestQueue.add(new VolleyHttpRequest(Request.Method.GET, map,
                Constants.ServiceCode.SUPPORT, this, this));

    }
}
