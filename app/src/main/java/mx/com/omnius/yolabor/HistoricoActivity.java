package mx.com.omnius.yolabor;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.com.omnius.yolabor.Adapter.HistoricoAdapter;
import mx.com.omnius.yolabor.Model.JobModel;
import mx.com.omnius.yolabor.Model.JobTypeModel;
import mx.com.omnius.yolabor.Model.People;
import mx.com.omnius.yolabor.data.DataGenerator;
import mx.com.omnius.yolabor.parse.AsyncTaskCompleteListener;
import mx.com.omnius.yolabor.parse.VolleyHttpRequest;
import mx.com.omnius.yolabor.utils.AppLog;
import mx.com.omnius.yolabor.utils.Constants;

import static mx.com.omnius.yolabor.YolaborApplication.requestQueue;


/**
 * Created by UDIaz on 26/01/18.
 */

public class HistoricoActivity extends AppCompatActivity  {

    private View parent_view;

    private RecyclerView recyclerView;
    private HistoricoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sectioned);
        parent_view = findViewById(android.R.id.content);
      //  historial();
        //initToolbar();
       // initComponent();
        Log.e("PRUEBA", ":::PRUEBA:" + "");
    }

    /*
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sectioned");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponent() {
        /+
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        List<People> items = DataGenerator.getPeopleData(this);
        items.addAll(DataGenerator.getPeopleData(this));
        items.addAll(DataGenerator.getPeopleData(this));

        int sect_count = 0;
        int sect_idx = 0;
        List<String> months = DataGenerator.getStringsMonth(this);
        for (int i = 0; i < items.size() / 6; i++) {
            items.add(sect_count, new People(months.get(sect_idx), true));
            sect_count = sect_count + 5;
            sect_idx++;*/
        }

        //set data and list adapter
/*
        mAdapter = new HistoricoAdapter(this, items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new HistoricoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, People obj, int position) {
                Snackbar.make(parent_view, "Item " + obj.name + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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
                ArrayList<String> listType = new ArrayList<String>();
                JobModel[] listaHistory = gson.fromJson(response, JobModel[].class);
                if (listaHistory != null) {
                    Log.e("DENTRO IF", "listHistorial" + listaHistory[1].getIdJob());
                    for (int i = 0; i < listaHistory.length; i++) {
                        String datos = listaHistory[i].getIdJob();
                        Log.e("Profile","Estados parse: " + datos);
                        listType.add(datos);
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
                    });
                }

                break;


        }
    }
}
*/


