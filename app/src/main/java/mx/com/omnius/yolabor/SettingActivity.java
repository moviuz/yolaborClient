package mx.com.omnius.yolabor;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static java.security.AccessController.getContext;

/**
 * Created by omnius on 16/03/18.
 */

public class SettingActivity extends AppCompatActivity {

    private Spinner time,radius;
    private Toolbar toolbar;
    private ActionBar actionBar;



    String[] times = {"5", "10", "15","20","25","30"};
    String[] radiuss = {"5", "10", "15","20","25","30"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        time = (Spinner) findViewById(R.id.spinner_time);
        radius = (Spinner) findViewById(R.id.spinner_radius);
        initToolbar();
        time.setAdapter(new ArrayAdapter<String>(time.getContext(), android.R.layout.simple_spinner_item,times));
        radius.setAdapter(new ArrayAdapter<String>(radius.getContext(),android.R.layout.simple_spinner_item,radiuss));
    }


    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Settings");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
    }
}
