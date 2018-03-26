package mx.com.omnius.yolabor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.com.omnius.yolabor.LoginActivity;
import mx.com.omnius.yolabor.Fragments.JobRequestFragment;
import mx.com.omnius.yolabor.Fragments.MapFragment;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;
import mx.com.omnius.yolabor.utils.Constants;
import mx.com.omnius.yolabor.utils.HistoActivity;
import mx.com.omnius.yolabor.utils.Tools;
import static mx.com.omnius.yolabor.YolaborApplication.requestQueue;


/**
 * Created by UDIaz on 26/01/18.
 */

public class MenuDrawer extends AppCompatActivity  {

    private FirebaseAuth mAuth;
    private TabLayout tab_layout;
    private ViewPager view_pager;
    private static final String[] pageTitle = {"Map","Request"};
    private ActionBar actionBar;
    private Toolbar toolbar;
    private Menu menu_navigation;
    private DrawerLayout drawer;
    private View navigation_header;
    private boolean is_account_mode = false;
    private TextView textView, name;
    private String x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();




        setContentView(R.layout.activity_menu_drawer);

       initToolbar();

       initComponent();
        initNavigationMenu();
       // llenarcampos();
        Log.e("DENTRO DEL IF","MENUDRAWABLE");




    }

    //MenuDrawerMail
    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Home");
        toolbar.setNavigationIcon(R.drawable.ic_menu);

    }
    public  void llenarcampos(){
        x = YolaborApplication.preferenceHelper.getEmail();
        Log.e("YYY esto YYY", x);
        textView.setText("FERCHO");
    }

    private void initNavigationMenu() {
        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                //updateCounter(nav_view);
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                onItemNavigationClicked(item);
                return true;
            }
        });

        // open drawer at sta
       // drawer.openDrawer(GravityCompat.START);
       // updateCounter(nav_view);
        menu_navigation = nav_view.getMenu();

        // navigation header
        navigation_header = nav_view.getHeaderView(0);
        textView = (TextView) navigation_header.findViewById(R.id.drawable_email);
        name = (TextView) navigation_header.findViewById(R.id.drawable_name);
        x = YolaborApplication.preferenceHelper.getEmail();
        Log.e("YYY esto YYY", x);
        textView.setText(x);
        name.setText(YolaborApplication.preferenceHelper.getFirstName() + YolaborApplication.preferenceHelper.getLastName());


    }

    private void onItemNavigationClicked(MenuItem item) {

       if (item.getTitle().equals(getString(R.string.perfil))){
           Intent perfil = new Intent(getApplicationContext(),ProfileActivity.class);
           startActivity(perfil);
           drawer.closeDrawers();
       }else if (item.getTitle().equals(getString(R.string.my_jobs))){
           Intent servc = new Intent(getApplicationContext(),HistoricoActivity.class);
           startActivity(servc);
           drawer.closeDrawers();

       }else if (item.getTitle().equals(getString(R.string.historico))){
           Intent servc = new Intent(getApplicationContext(),HistoActivity.class);
           startActivity(servc);
           drawer.closeDrawers();

       }else if (item.getTitle().equals(getString(R.string.feedback))){
           Toast.makeText(this, "Feedbak", Toast.LENGTH_SHORT).show();
           drawer.closeDrawers();

       }else if (item.getTitle().equals(getString(R.string.settings))) {
           Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
           Intent servc = new Intent(getApplicationContext(),SettingActivity.class);
           startActivity(servc);
           drawer.closeDrawers();
       }else if  (item.getTitle().equals(getString(R.string.logout))){

           mAuth.signOut();
           LoginManager.getInstance().logOut();
           updateUI();
           Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_SHORT).show();
           actionBar.setTitle(item.getTitle());
           drawer.closeDrawers();

       } else if (item.getTitle().equals(getString(R.string.home_item))){
           Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
           drawer.closeDrawers();
       }
    }

    //BottomNavigationIcon
    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        tab_layout = (TabLayout) findViewById(R.id.tab_fragment_job);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);
        setupTabIcons();

    }
    private void setupTabIcons() {
        tab_layout.getTabAt(0).setIcon(R.drawable.ic_google_maps);
        tab_layout.getTabAt(1).setIcon(R.drawable.ic_request);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }



    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
            this.mFragmentList = new ArrayList<>();
            mFragmentList.add(new MapFragment());
            mFragmentList.add(new JobRequestFragment());

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

    @Override
    public void onStart() {
        String x ;
        x = LoginActivity.getName();
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if((currentUser == null) && (x!= "m")){
            updateUI();
        }


    }

    private void updateUI() {
        // Toast.makeText(LoginActivity.this, "You are login", Toast.LENGTH_LONG).show();
        Intent Login = new Intent(MenuDrawer.this, LoginActivity.class);
        startActivity(Login);

    }



}

