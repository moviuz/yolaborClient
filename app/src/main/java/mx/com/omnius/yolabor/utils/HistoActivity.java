package mx.com.omnius.yolabor.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.com.omnius.yolabor.Adapter.MainAdapter;
import mx.com.omnius.yolabor.HistoricoActivity;
import mx.com.omnius.yolabor.LoginActivity;
import mx.com.omnius.yolabor.MenuDrawer;
import mx.com.omnius.yolabor.Model.HistoModel;
import mx.com.omnius.yolabor.Model.JobModel;
import mx.com.omnius.yolabor.Model.UserModel;
import mx.com.omnius.yolabor.ProfileActivity;
import mx.com.omnius.yolabor.R;
import mx.com.omnius.yolabor.YolaborApplication;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;

import static mx.com.omnius.yolabor.YolaborApplication.requestQueue;

/**
 * Created by omnius on 14/03/18.
 */

public class HistoActivity extends AppCompatActivity implements Response.ErrorListener, AsyncTaskCompleteListener {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private MainAdapter adapter;
    private List<JobModel> data_list;
    private LinearLayout layoutVacio;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawer;
    private Menu menu_navigation;
    private View navigation_header;
    private FirebaseAuth mAuth;
    private boolean is_account_mode = false;

    private List<HistoModel> histo_list;




        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_list_sectioned);

            histo_list = new ArrayList<>();
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
            // layoutVacio = (LinearLayout) findViewById(R.id.layout_histo);
            LinearLayoutManager lm = new LinearLayoutManager(this);
            data_list = new ArrayList<>();
            recyclerView.setLayoutManager(lm);
            historial();
            initToolbar();
            //initNavigationMenu();
            adapter = new MainAdapter(this, histo_list);

            recyclerView.setAdapter(adapter);
        }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("History");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
    }
    private void historial(){

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(Constants.URL, Constants.ServiceType.HISTORY

                + Constants.Params.IDCLIENT + "="
                + YolaborApplication.preferenceHelper.getClientid() + "&"
                + Constants.Params.HISTORY + "="
                +"true"
        );

        requestQueue.add(new VolleyHttpRequest(Request.Method.GET, map,
                Constants.ServiceCode.HISTORY_CLIENT, this, this));
        Toast.makeText(this, "LOGIN METOD", Toast.LENGTH_SHORT).show();

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
        /*navigation_header = nav_view.getHeaderView(0);
        (navigation_header.findViewById(R.id.bt_account)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_hide = Tools.toggleArrow(view);
                is_account_mode = is_hide;
                menu_navigation.clear();
                if (is_hide) {
                    menu_navigation.add(1, 1000, 100, "evans.collins@mail.com").setIcon(R.drawable.ic_account_circle);
                    menu_navigation.add(1, 2000, 100, "adams.green@mail.com").setIcon(R.drawable.ic_account_circle);
                    menu_navigation.add(1, 1, 100, "Add account").setIcon(R.drawable.ic_add);
                    menu_navigation.add(1, 2, 100, "Manage accounts").setIcon(R.drawable.ic_settings);
                } else {
                    nav_view.inflateMenu(R.menu.menu_navigation_drawerl);
                    //  updateCounter(nav_view);
                }
            }
        });*/
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

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Toast.makeText(this, "Error al entrar en contacto con el servidor "+ error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case Constants.ServiceCode.HISTORY_CLIENT:
                AppLog.Log("","spinner "+ response);
                Log.e("MENSAJE", "facebook:onSuccess:" + response);
                Gson gson = new Gson();
                HistoModel[] hm = gson.fromJson(response,HistoModel[].class);
                if (hm != null) {
                    //Log.e("DENTRO IF", "listHistorial" + hm[1].getFullName());
                    for (int i = 0; i < hm.length; i++) {
                        Log.e("DENTRO IF", "listHistorial" + hm.length);
                        addHisto(hm[i].getFullName(),hm[i].getServicio());
                        Log.e("DENTRO IF", "VALOR I" + i);

                    }
                  /*  type.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listType));
                    type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // vacio
                        }
                    });*/
                }

                break;


        }
    }

    private void updateUI() {
        // Toast.makeText(LoginActivity.this, "You are login", Toast.LENGTH_LONG).show();
        Intent Login = new Intent(HistoActivity.this, LoginActivity.class);
        startActivity(Login);

    }

    public void addHisto(String name, String servicio){
    HistoModel histoModel = new HistoModel();
    histoModel.setFullName(name);
    histoModel.setServicio(servicio);
    histo_list.add(histoModel);
    adapter.notifyDataSetChanged();

    }
}
