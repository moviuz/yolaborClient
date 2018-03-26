package mx.com.omnius.yolabor.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


import mx.com.omnius.yolabor.JobActivity;
import mx.com.omnius.yolabor.Model.JobTypeModel;
import mx.com.omnius.yolabor.R;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;
import mx.com.omnius.yolabor.utils.AppLog;
import mx.com.omnius.yolabor.utils.Constants;
import mx.com.omnius.yolabor.utils.Tools;
import mx.com.omnius.yolabor.YolaborApplication;


/**
 * Created by UDIaz on 30/01/18.
 */
@SuppressLint("ValidFragment")

public class JobRequestFragment extends Fragment implements View.OnClickListener, AsyncTaskCompleteListener, Response.ErrorListener {
    private RadioButton raBtnInter, raBtnTras;
    private Spinner type, languaje;
    private ImageButton btnDate, btnTime;
    private Button btnSendRequest;
   // private TextView resuldate;

    String[] types = {"Standard Interpretation", "Standard Translation", "Advanced Interpretation", "Advanced Traslation"};
    String[] languajes = {"Spanish", "French", "Italian","Poñ"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_job_request, container, false);
        raBtnInter = (RadioButton) view.findViewById(R.id.raBtnInterpetre);
        raBtnTras = (RadioButton) view.findViewById(R.id.raBtnTraslate);
//        raBtnInter.setOnClickListener(this);
//        raBtnTras.setOnClickListener(this);
        btnDate = (ImageButton) view.findViewById(R.id.btn_date);
        btnTime = (ImageButton) view.findViewById(R.id.btn_time);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);

        btnSendRequest = (Button) view.findViewById(R.id.btnSendRequest);
        btnSendRequest.setOnClickListener(this);


        type = (Spinner) view.findViewById(R.id.spinner_company);
        languaje = (Spinner) view.findViewById(R.id.spinner_languaje);

        languaje.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, languajes));

        jobType();

        languaje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });













        return view;
    }

    @Override
    public void onClick(View view) {
//    boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.raBtnInterpetre:
                // if (checked)
                Toast.makeText(getContext(), "Interprete presionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.raBtnTraslate:
                // if (checked)
                Toast.makeText(getContext(), "Traslate Presionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_date:
                //showDatePickerDialog((ImageButton) view);
                dialogDatePickerLight((ImageButton) view);
                break;
            case R.id.btn_time:
               // Toast.makeText(getContext(), "Time presionado", Toast.LENGTH_SHORT).show();
                //showTimePickerDialog((ImageButton) view);
                dialogTimePickerLight((ImageButton) view);
                break;

            case R.id.btnSendRequest:
                Intent job = new Intent(getContext(), JobActivity.class);
                startActivity(job);

                break;
        }
    }



    private void dialogTimePickerLight(final ImageButton bt) {
        Calendar cur_calender = Calendar.getInstance();
        TimePickerDialog datePicker = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
               // ((TextView) getActivity().findViewById(R.id.result)).setText(hourOfDay + " : " + minute);
            }
        }, cur_calender.get(Calendar.HOUR_OF_DAY), cur_calender.get(Calendar.MINUTE), true);
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.show(getActivity().getFragmentManager(), "Timepickerdialog");
    }


    private void dialogDatePickerLight(final ImageButton bt) {
        Calendar cur_calender = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        ((TextView) getActivity().findViewById(R.id.txt_result)).setText(Tools.getFormattedDateSimple(date_ship_millis));
                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.setMinDate(cur_calender);
        datePicker.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case Constants.ServiceCode.JOB_TYPE:
                AppLog.Log("","spinner "+ response);
                Log.e("MENSAJE", "facebook:onSuccess:" + response);
                Gson gson = new Gson();
                ArrayList<String> listType = new ArrayList<String>();
                JobTypeModel[] listaTipos = gson.fromJson(response, JobTypeModel[].class);
                if (listaTipos != null) {
                    for (int i = 0; i < listaTipos.length; i++) {
                        String datos = listaTipos[i].getName();
                         AppLog.Log("Profile","Estados parse: " + datos);
                        listType.add(datos);
                    }
                    type.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listType));
                    type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // vacio
                        }
                    });
                }

                break;


        }
    }

    private void jobType() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(Constants.URL, Constants.ServiceType.JOB_TYPE);


        YolaborApplication.requestQueue.add(new VolleyHttpRequest(Request.Method.GET, map,
                Constants.ServiceCode.JOB_TYPE, this, this));


    }
    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Toast.makeText(getContext(), "Error al entrar en contacto con el servidor "+ error, Toast.LENGTH_SHORT).show();
    }



}
