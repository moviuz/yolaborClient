package mx.com.omnius.yolabor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by UDIaz on 31/01/18.
 */

public class JobActivity extends AppCompatActivity implements View.OnClickListener{

    private ActionBar actionBar;
    private Toolbar toolbar;
    private TabLayout tab_layout;

    private TextView txtCurrent, txtChat, txtCancel;

    private Menu menu_navigation;
    private DrawerLayout drawer;
    private View navigation_header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_job);
        txtCurrent = (TextView) findViewById(R.id.txtCurrent);
        txtChat = (TextView) findViewById(R.id.txtChat);
        txtCancel = (TextView) findViewById(R.id.txtCancel);
        txtCurrent.setOnClickListener(this);
        txtChat.setOnClickListener(this);
        txtCancel.setOnClickListener(this);
        initToolbar();
        //initComponent();

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtCurrent:
                Toast.makeText(this, "Current presionado", Toast.LENGTH_SHORT).show();
                Intent detailRequest = new Intent(getApplicationContext(), JobDetailActivity.class );
                startActivity(detailRequest);
                break;
            case R.id.txtChat:
                Toast.makeText(this, "Chat presionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txtCancel:
                Toast.makeText(this, "Cancel presionado", Toast.LENGTH_SHORT).show();
                break;
            

        }
        
    }
       
}

