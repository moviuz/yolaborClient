package mx.com.omnius.yolabor.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import mx.com.omnius.yolabor.JobActivity;
import mx.com.omnius.yolabor.Model.JobTypeModel;
import mx.com.omnius.yolabor.R;
import mx.com.omnius.yolabor.YolaborApplication;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;
import mx.com.omnius.yolabor.utils.AppLog;
import mx.com.omnius.yolabor.utils.Constants;
import mx.com.omnius.yolabor.utils.Tools;
import mx.com.omnius.yolabor.utils.ViewAnimation;


/**
 * Created by UDIaz on 30/01/18.
 */
@SuppressLint("ValidFragment")

public class JobRequestFragment extends Fragment implements View.OnClickListener, AsyncTaskCompleteListener, Response.ErrorListener {
    private RadioButton raBtnInter, raBtnTras, raBtnFemale, raBtnMale;
    private Spinner type, languaje;
    private ImageButton btnDate, btnTime;
    private Button btnSendRequest, btnFilterWorker,btnCancelFilterWorker;
    private SeekBar seekBarHoras, seekbar_yrs, seekBar_horaInicio, seekBar_horaFin;
    private TextView txtHrs,txtDay,textYars,txtHrIni,txtHrFin,resul;
    private ImageButton bt_toggle_days, bt_toggle_hours;
    private View lyt_expand_days, lyt_expand_hours;
    private NestedScrollView nested_scroll_view;
    private Switch available;

    int progressChangedValue = 4;
    ArrayList<String> listDays = new ArrayList<String>();
    ArrayList<String> listSkill = new ArrayList<String>();
    private RadioGroup grupoDyas, raButtonGender;
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

        btnFilterWorker = (Button) view.findViewById(R.id.btnFilterWorker);
        btnFilterWorker.setOnClickListener(this);


        type = (Spinner) view.findViewById(R.id.spinner_type);
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
            case R.id.btnFilterWorker:
                //emptySpace(); valido que no esten vacios los campos
                showCustomDialog();
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

    private void initComponent(Dialog view) {
        txtDay = (TextView) view.findViewById(R.id.txtdias);
        grupoDyas = (RadioGroup) view.findViewById(R.id.grupoDays);

        // nested scrollview
        nested_scroll_view = (NestedScrollView) view.findViewById(R.id.scrollView2);
        // seccion monday
        bt_toggle_days = (ImageButton) view.findViewById(R.id.bt_toggle_days);
        lyt_expand_days = (View) view.findViewById(R.id.lyt_expand_hour);

        bt_toggle_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(view, lyt_expand_days);
                checkDay( view);

            }
        });

        nested_scroll_view = (NestedScrollView) view.findViewById(R.id.scrollView2);
        // seccion monday
        bt_toggle_hours = (ImageButton) view.findViewById(R.id.bt_toggle_hours);
        lyt_expand_hours = (View) view.findViewById(R.id.lyt_expand_hours);

        bt_toggle_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(view, lyt_expand_hours);
                //   checkMonday();
            }
        });
    }

    private void showCustomDialog() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.fragment_worker_filtrado);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        btnSendRequest = (Button) dialog.findViewById(R.id.btn_send_worker);
        btnCancelFilterWorker = (Button) dialog.findViewById(R.id.btn_cancel_filter_worker);

        gender(dialog);

        initComponent(dialog);



        ((AppCompatButton) dialog.findViewById(R.id.btn_send_worker)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // validarCampos();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.btn_cancel_filter_worker)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);

    }

    private void gender(Dialog dialog){

        String experenceTemp = String.valueOf(progressChangedValue);
       // KishApplication.preferenceHelper.putAvailable(Constants.NOT);
       // KishApplication.preferenceHelper.putExperence(experenceTemp);

        seekBar_horaInicio = (SeekBar) dialog.findViewById(R.id.seekBar_horaInicio);
        seekBar_horaFin = (SeekBar) dialog.findViewById(R.id.seekBar_horaFin);
        txtHrIni = (TextView) dialog.findViewById(R.id.txtHrIni);
        txtHrFin = (TextView) dialog.findViewById(R.id.txtHrFin);
        raBtnMale = (RadioButton) dialog.findViewById(R.id.raBtnMaleF);
        raBtnFemale = (RadioButton) dialog.findViewById(R.id.raBtnFemaleF);
        raButtonGender = (RadioGroup) dialog.findViewById(R.id.raButtonGender);
        raButtonGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.raBtnFemaleF){
                    //KishApplication.preferenceHelper.putGender(Constants.Female);
                }else if (checkedId == R.id.raBtnMaleF){
                    //KishApplication.preferenceHelper.putGender(Constants.Male);
                }
            }

        });

        available = (Switch) dialog.findViewById(R.id.switAvailable);
        available.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                   // KishApplication.preferenceHelper.putAvailable(Constants.YES);
                } else {
                    //KishApplication.preferenceHelper.putAvailable(Constants.NOT);
                }
            }
        });

        seekbar_yrs = (SeekBar) dialog.findViewById(R.id.seekbar_yrs);
        textYars = (TextView) dialog.findViewById(R.id.textYars);

        seekbar_yrs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                textYars.setText(progressChangedValue+"");

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                String experenceTemp = String.valueOf(progressChangedValue);
               // KishApplication.preferenceHelper.putExperence(experenceTemp);

            }
        });

        seekBar_horaInicio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressini = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressini = progress;
                txtHrIni.setText(progressini+"");

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                String horaIniTemp = String.valueOf(progressini);
               // KishApplication.preferenceHelper.putHoraIni(horaIniTemp);
            }
        });

        seekBar_horaFin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressfin = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressfin = progress;
                txtHrFin.setText(progressfin+"");

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                String horaFinTemp = String.valueOf(progressfin);
                //KishApplication.preferenceHelper.putHoraFin(horaFinTemp);
            }
        });

    }

    private void toggleSection(View bt, final View lyt) {
        boolean show = toggleArrow(bt);
        if (show) {
            ViewAnimation.expand(lyt, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt);
                }
            });
        } else {
            ViewAnimation.collapse(lyt);
        }
    }

    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

    public void checkDay(View dialog){
        grupoDyas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub

                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                if (checkedId == R.id.checkBoxD1){
                    txtDay.setText(radioButton.getText().toString());
                    getIdDay(txtDay.getText().toString());
                }else if (checkedId == R.id.checkBoxD2){
                    txtDay.setText(radioButton.getText().toString());
                    getIdDay(txtDay.getText().toString());
                }else if (checkedId == R.id.checkBoxD3){
                    txtDay.setText(radioButton.getText().toString());
                    getIdDay(txtDay.getText().toString());
                }else if (checkedId == R.id.checkBoxD4){
                    txtDay.setText(radioButton.getText().toString());
                    getIdDay(txtDay.getText().toString());
                }else if (checkedId == R.id.checkBoxD5){
                    txtDay.setText(radioButton.getText().toString());
                    getIdDay(txtDay.getText().toString());
                }else if (checkedId == R.id.checkBoxD6){
                    txtDay.setText(radioButton.getText().toString());
                    getIdDay(txtDay.getText().toString());
                }else if (checkedId == R.id.checkBoxD7){
                    txtDay.setText(radioButton.getText().toString());
                    getIdDay(txtDay.getText().toString());
                }
            }
        });


    }

    public void getIdDay(String day){
        for (int i = 0; i < listDays.size();i++){
            String tmporal = listDays.get(i);
            String[] parts = tmporal.split("/");
            String partId = parts[0];
            String partNombre = parts[1];
            if (partNombre.equals(day)){
                YolaborApplication.preferenceHelper.putIdDayM(partId);
            }
        }
    }

}
