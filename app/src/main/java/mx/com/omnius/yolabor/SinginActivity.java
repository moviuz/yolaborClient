package mx.com.omnius.yolabor;

import android.*;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import mx.com.omnius.yolabor.Model.CompanyModel;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;
import mx.com.omnius.yolabor.utils.AppLog;
import mx.com.omnius.yolabor.utils.Constants;

/**
 * Created by omnius on 23/02/18.
 */

public class SinginActivity extends AppCompatActivity implements AsyncTaskCompleteListener,Response.ErrorListener, View.OnClickListener {

    public final Calendar c = Calendar.getInstance();
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private LocationManager locManager;
    private Spinner compSpiner;
    private Uri uri = null;
    private String profileImageData, profileImageFilePath;


    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);


    String gender;
    EditText cfirstname,clastname,cemail, cphone, cpassword, textItin, txtPasword;
    TextView cresult;
    ImageButton dateBirth;
    RadioGroup rgroupGender;
    String latitud,longitud;
    ImageView profilePhoto;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_singin);

        cfirstname = (EditText) findViewById(R.id.cfirstname);
        clastname = (EditText) findViewById(R.id.clastname);
        cemail = (EditText) findViewById(R.id.cemail);
        cphone =(EditText) findViewById(R.id.cphone);
        cpassword = (EditText) findViewById(R.id.cpassword);
        cresult = (TextView) findViewById(R.id.cresult);
        textItin = (EditText) findViewById(R.id.citin);
        txtPasword = (EditText) findViewById(R.id.cpassword) ;
        dateBirth = (ImageButton) findViewById(R.id.cbtn_date);
        rgroupGender =(RadioGroup) findViewById(R.id.crgroup);
        profilePhoto = (ImageView) findViewById(R.id.image_profile);

        compSpiner = (Spinner) findViewById(R.id.spinner_company);


         Button btnsing = (Button) findViewById(R.id.singinButton);

        btnsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();

            }
        });


        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFecha();
            }
        });

      profilePhoto.setOnClickListener(this);
        //  profilePhoto.setOnClickListener(this);

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


    @Override
    public  void   onClick(View view) {
        switch (view.getId()) {
            case R.id.image_profile:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                            showPictureDialog();
                        }else{
                            android.support.v4.app.ActivityCompat.requestPermissions(this, new String[]{ android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                    }else{
                        android.support.v4.app.ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }else{
                    showPictureDialog();
                }
                break;
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
                break;
            case Constants.ServiceCode.NEW_CLIENT:
         if (!response.equals(null) & !response.equals(" ") ){
                    Log.e("ERROR ENTRO SIN PERMISO", response);
                    Intent Mapa = new Intent(getApplicationContext(), MenuDrawer.class);
                            startActivity(Mapa);
                    }
                else{
                    Toast.makeText(this, "Email or Password Invalid!!", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }

    private void registrar() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(Constants.URL,Constants.ServiceType.SINGIN

                + Constants.Params.FIRSTNAME + "="
                + cfirstname.getText().toString() + "&"
                + Constants.Params.LASTNAME + "="
                + clastname.getText().toString() + "&"
                + Constants.Params.EMAIL + "="
                + cemail.getText().toString() + "&"
                + Constants.Params.PHONE + "="
                + cphone.getText().toString() + "&"
                + Constants.Params.PASSWORD + "="
                + cpassword.getText().toString() + "&"
                + Constants.Params.BIRTHDATE + "="
                + cresult.getText().toString() + "&"
                + Constants.Params.GENDER + "="
                + gender.toString() + "&"
                + Constants.Params.ITIN + "="
                + textItin.getText().toString() + "&"
                + Constants.Params.COMPANY + "="
                + "1" + "&"
                + Constants.Params.PARTNER +  "="
                +  "1" + "&"
                + Constants.Params.LATITUD + "="
                + YolaborApplication.preferenceHelper.getLatitudeC() + "&"
                + Constants.Params.LONGITUD + "="
                + YolaborApplication.preferenceHelper.getLongitudeC() +"&"
                + Constants.Params.LOGINMETHOD + "="
                + "N"
);

       YolaborApplication.requestQueue.add(new VolleyHttpRequest(Request.Method.GET, map,
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
                    cresult.setText(year + BARRA + mesFormateado + BARRA + diaFormateado);


                }
                //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                /**
                 *También puede cargar los valores que usted desee
                 */
            },anio, mes, dia);
            //Muestro el widget
            recogerFecha.show();

        }
    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle(getResources().getString(
                R.string.dialog_select_type_photo));
        String[] pictureDialogItems = {getResources().getString(R.string.gallery_dialog), getResources().getString(R.string.camera_dialog)};

        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                selectPhotoFromGallery();
                                break;
                            case 1:
                                takePhoto();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void takePhoto() {
        Calendar cal = Calendar.getInstance();
        File file = new File(Environment.getExternalStorageDirectory(), (cal.getTimeInMillis() + ".jpg"));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        uri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(cameraIntent, Constants.TAKE_PHOTO);
    }

    private void selectPhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, Constants.SELECT_PHOTO);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Toast.makeText(this, "Error al entrar en contacto con el servidor "+ error, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.SELECT_PHOTO) {
            if (data != null) {

                Uri contentURI = data.getData();
                AppLog.Log("", "Choose photo on activity result "+ contentURI);
                beginCrop(contentURI);
            }

        } else if (requestCode == Constants.TAKE_PHOTO) {

            if (uri != null) {

                profileImageFilePath = uri.getPath();
                AppLog.Log("", "Take photo on activity result");
                beginCrop(uri);
            } else {
                Toast.makeText(this, R.string.toast_unable_to_selct_image, Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == Crop.REQUEST_CROP) {
            AppLog.Log("", "Crop photo on activity result");
            handleCrop(resultCode, data);
        }
    }


    private void beginCrop(Uri source) {
        Uri outputUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), (Calendar.getInstance()
                .getTimeInMillis() + ".jpg")));
        //new Crop(source).output(outputUri).asSquare().start(this);
        Crop.of(source, outputUri).asSquare().start(this);

    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            AppLog.Log("", "Handle crop");
            profileImageData = getRealPathFromURI(Crop.getOutput(result));
            AppLog.Log("","ruta de imagen0 "+String.valueOf(profileImageData));
            try {
                ExifInterface ei = new ExifInterface(getRealPathFromURI(Crop.getOutput(result)));
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch(orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        profilePhoto.setRotation(90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        profilePhoto.setRotation(180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        profilePhoto.setRotation(270);
                        break;
                    case ExifInterface.ORIENTATION_UNDEFINED:
                        profilePhoto.setRotation(0);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
/*
            SharedPreferences prefs =
                    getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("ImagePerfil", String.valueOf((Crop.getOutput(result))));
            editor.commit();

            AppLog.Log("","ruta de imagen "+String.valueOf((Crop.getOutput(result))));
            */


            profilePhoto.setImageURI(Crop.getOutput(result));

        } else if (resultCode == Crop.RESULT_ERROR) {

            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}
