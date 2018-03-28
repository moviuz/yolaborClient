package mx.com.omnius.yolabor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v13.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.location.LocationListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import android.Manifest;

import mx.com.omnius.yolabor.Model.CompanyModel;
import mx.com.omnius.yolabor.Model.UserModel;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;
import mx.com.omnius.yolabor.utils.Constants;


import static mx.com.omnius.yolabor.YolaborApplication.requestQueue;

/**
 * Created by omnius on 23/02/18.
 */

public class SinginActivity extends AppCompatActivity implements AsyncTaskCompleteListener,Response.ErrorListener {

    public final Calendar c = Calendar.getInstance();
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private LocationManager locManager;
    private Spinner compSpiner;


    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);


    String gender;
    EditText txtName,txtLastName,txtEmail, textPhone, textPassword, textItin, txtPasword;
    TextView textBirtdate;
    ImageButton dateBirth;
    RadioGroup rgroupGender;
    String latitud,longitud;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_singin);

        txtName = (EditText) findViewById(R.id.cfirstname);
        txtLastName = (EditText) findViewById(R.id.clastname);
        txtEmail = (EditText) findViewById(R.id.cemail);
        textPhone =(EditText) findViewById(R.id.cphone);
        textPassword = (EditText) findViewById(R.id.cpassword);
        textBirtdate = (TextView) findViewById(R.id.cresult);
        textItin = (EditText) findViewById(R.id.citin);
        txtPasword = (EditText) findViewById(R.id.cpassword) ;
        dateBirth = (ImageButton) findViewById(R.id.cbtn_date);
        rgroupGender =(RadioGroup) findViewById(R.id.crgroup);

        compSpiner = (Spinner) findViewById(R.id.spinner_company);


         Button button = (Button) findViewById(R.id.singinButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singin();

            }
        });

        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFecha();
            }
        });


        rgroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.raMale){
                    gender = "M";
                }else if(i == R.id.raFemale){
                    gender = "F";
                }

            }
        });
        
        allCompany();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {

        }


    }

    private void allCompany() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(Constants.URL, Constants.ServiceType.ALLCOMPANY);


        YolaborApplication.requestQueue.add(new VolleyHttpRequest(Request.Method.GET, map,
                Constants.ServiceCode.ALLCOMPANY, this, this));
    }


    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case Constants.ServiceCode.ALLCOMPANY:
                Gson gns = new Gson();
                ArrayList<String> listCompany = new ArrayList<String>();
                CompanyModel[] listaComp = gns.fromJson(response, CompanyModel[].class);
                if (listaComp != null){
                    for (int i = 0; i < listaComp.length; i++) {
                        String datos = listaComp[i].getName();
                        listCompany.add(datos);
                }
                    compSpiner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listCompany));
                    compSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

            case Constants.ServiceCode.NEW_CLIENT:
                Gson gson = new Gson();
                UserModel user = gson.fromJson(response, UserModel.class);
                if (user != null){
                    if (user.getEmail().equals(txtName.getText().toString())&& user.getPassword().equals(txtPasword.getText().toString())){
                        Intent Mapa = new Intent(getApplicationContext(), MenuDrawer.class);
                        startActivity(Mapa);
                    }
                }else{
                    Toast.makeText(this, "Email or Password Invalid!!", Toast.LENGTH_SHORT).show();
                }



                break;
        }

    }
        private void singin(){
            latitud = YolaborApplication.preferenceHelper.getLongitudeC();
            longitud = YolaborApplication.preferenceHelper.getLatitudeC();
            Log.e("test1",txtName.getText().toString());
            Log.e("test1",txtLastName.getText().toString());
            Log.e("test1",txtEmail.getText().toString());
            Log.e("test1",txtPasword.getText().toString());
            Log.e("test1",textPhone.getText().toString());
            Log.e("test1",textItin.getText().toString());
            Log.e("test1",textBirtdate.getText().toString());
            Log.e("test1",gender);
            Log.e("test1latitud",latitud);
            Log.e("test1LONGITUD",longitud);

            /*HashMap<String, String> map = new HashMap<String, String>();
            map.put(Constants.URL, Constants.ServiceType.SINGIN

                    + Constants.Params.FIRSTNAME + "="
                    + txtName.getText().toString() + "&"
                    + Constants.Params.LASTNAME + "="
                    + txtLastName.getText().toString() + "&"
                    + Constants.Params.EMAIL + "="
                    + txtEmail.getText().toString() + "&"
                    + Constants.Params.PASSWORD + "="
                    + txtPasword.getText().toString() + "&"
                    + Constants.Params.PHONE + "="
                    + textPhone.getText().toString() + "&"
                    + Constants.Params.ITIN + "="
                    + textItin.getText().toString() + "&" );


            requestQueue.add(new VolleyHttpRequest(Request.Method.GET, map,
                    Constants.ServiceCode.NEW_CLIENT, this, this)); */
    }
    private void registrar() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(Constants.URL,Constants.ServiceType.SINGIN

                + Constants.Params.FIRSTNAME + "="
                + txtName.getText().toString() + "&"
                + Constants.Params.LASTNAME + "="
                + txtLastName.getText().toString() + "&"
                + Constants.Params.EMAIL + "="
                + txtEmail.getText().toString() + "&"
                + Constants.Params.PHONE + "="
                + textPhone.getText().toString() + "&"
                + Constants.Params.PASSWORD + "="
                + textPassword.getText().toString() + "&"
                + Constants.Params.BIRTHDATE + "="
                + textBirtdate.getText().toString() + "&"
                + Constants.Params.GENDER + "="
                + gender.toString() + "&"
                + Constants.Params.ITIN + "="
                + textItin.getText().toString() + "&"
                + Constants.Params.COMPANY + "="
                + "1" + "&"
                + Constants.Params.PARTNER +  "="
                +  "1" + "&"
                + Constants.Params.LOGINMETHOD

                + Constants.Params.MOTHERLENGUAGE +"="
                + "1" + "&"
                + Constants.Params.AVIABILITY + "="
                + "Looking for job" + "&"
                + Constants.Params.EXPERIENCE + "="
                + "5" + "&"
                + Constants.Params.RESUME + "="
                + "LOREM IMPUTS ASPA ASKID ASDI" + "&"
                + Constants.Params.LATITUDATEND + "="
                + YolaborApplication.preferenceHelper.getLongitudeC() + "&"
                + Constants.Params.LONGITUATTEND + "="
                + YolaborApplication.preferenceHelper.getLatitudeC() + "&"
                + Constants.Params.RADIOATEEND + "="
                + "0" + "&"
                + Constants.Params.LATITUDE + "="
                + YolaborApplication.preferenceHelper.getLongitudeC() + "&"
                + Constants.Params.LONGITUDE + "="
                + YolaborApplication.preferenceHelper.getLatitudeC() + "&"
                + Constants.Params.LOGINMETHOD + "="
                + "N" + "&"
                + Constants.Params.ADDRESATEND + "="
                + "a"

        );

        requestQueue.add(new VolleyHttpRequest(Request.Method.GET, map,
                Constants.ServiceCode.NEW_CLIENT, this, this));
        Toast.makeText(this, "REGISTREAR", Toast.LENGTH_SHORT).show();

    }


    private void obtenerFecha(){
            DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                    final int mesActual = month + 1;
                    //Formateo el día obtenido: antepone el 0 si son menores de 10
                    String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                    //Formateo el mes obtenido: antepone el 0 si son menores de 10
                    String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                    //Muestro la fecha con el formato deseado
                    textBirtdate.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


                }
                //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                /**
                 *También puede cargar los valores que usted desee
                 */
            },anio, mes, dia);
            //Muestro el widget
            recogerFecha.show();

        }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Toast.makeText(this, "Error al entrar en contacto con el servidor "+ error, Toast.LENGTH_SHORT).show();
    }






}
