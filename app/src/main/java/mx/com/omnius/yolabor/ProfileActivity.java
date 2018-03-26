package mx.com.omnius.yolabor;

import android.app.DatePickerDialog;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mx.com.omnius.yolabor.Fragments.PerfilFragment;
import mx.com.omnius.yolabor.Fragments.SkillFragment;
import mx.com.omnius.yolabor.Fragments.TimeFragment;


/**
 * Created by UDIaz on 26/01/18.
 */

public class ProfileActivity extends AppCompatActivity  {
    private ViewPager view_pager;
    private TabLayout tab_layout;
    private View view;
    public final Calendar c = Calendar.getInstance();
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private LocationManager locManager;


    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    private TextView txt_result;
    private EditText firstname, lastename, email, password,phone;
    private RelativeLayout relativeLayout;
    private ImageButton btnCalendar;
    private RadioGroup radioGroup;



    private static final String[] pageTitle = {"General","Skill", "Time"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proffile);
       firstname = (EditText) findViewById(R.id.firstname_proff);
        lastename = (EditText) findViewById(R.id.lastname_proff) ;
        email = (EditText) findViewById(R.id.email_proff);
        password = (EditText) findViewById(R.id.password_proff);
        phone = (EditText) findViewById(R.id.phone_proff);
        btnCalendar = (ImageButton) findViewById(R.id.btn_date);
        txt_result = (TextView) findViewById(R.id.txt_result);
        radioGroup = (RadioGroup) findViewById(R.id.radio_proff);
       llenarcampos();
        //initComponent();

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFecha();
            }
        });


    }

    //BottomNavigationIcon
    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        tab_layout = (TabLayout) findViewById(R.id.tab_fragment_job);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);
       // setupTabIcons();

    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
            this.mFragmentList = new ArrayList<>();
            mFragmentList.add(new PerfilFragment());
            mFragmentList.add(new SkillFragment());
            mFragmentList.add(new TimeFragment());




        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return pageTitle[position];
            //return mFragmentTitleList.get(position);
        }
    }


    private void llenarcampos(){
       String y = YolaborApplication.preferenceHelper.getGender();

        // final RelativeLayout relav = (RelativeLayout) findViewById(R.id.rla_profile) ;
        //elativeLayout = (RelativeLayout) findViewById(R.id.rla_profile);
        firstname.setText(YolaborApplication.preferenceHelper.getFirstName());
        lastename.setText(YolaborApplication.preferenceHelper.getLastName());
        email.setText(YolaborApplication.preferenceHelper.getEmail());
        password.setText((YolaborApplication.preferenceHelper.getPassword()));
        phone.setText(YolaborApplication.preferenceHelper.getPhone());
        txt_result.setText(YolaborApplication.preferenceHelper.getBirthdate());




        if (y =="M"){
            radioGroup.check(R.id.raBtnTraslate);
        }else {

            radioGroup.check(R.id.raBtnInterpetre);
        }


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
                txt_result.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

}