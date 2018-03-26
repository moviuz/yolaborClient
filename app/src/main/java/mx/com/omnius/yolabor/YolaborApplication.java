package mx.com.omnius.yolabor;

import android.support.multidex.MultiDexApplication;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.facebook.FacebookSdk;

import mx.com.omnius.yolabor.utils.PreferenceHelper;

/**
 * Created by UDIaz on 03/02/18.
 */

public class YolaborApplication extends MultiDexApplication {
    public static PreferenceHelper preferenceHelper;
    public static RequestQueue requestQueue, locationRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        //  initFabric();
        preferenceHelper = new PreferenceHelper(getApplicationContext());
        //configureLanguage();
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        locationRequestQueue = new RequestQueue(cache, network);
        locationRequestQueue.start();
        FacebookSdk.sdkInitialize(getApplicationContext());
        //requestQueue = Volley.newRequestQueue(this);
    }

}
