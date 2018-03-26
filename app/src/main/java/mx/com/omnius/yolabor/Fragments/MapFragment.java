package mx.com.omnius.yolabor.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mx.com.omnius.yolabor.R;
import mx.com.omnius.yolabor.YolaborApplication;
import mx.com.omnius.yolabor.utils.Constants;

/**
 * Created by UDIaz on 29/01/18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    String latitud,longitud;
    double lati,longit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        latitud = YolaborApplication.preferenceHelper.getLongitudeC();
        longitud = YolaborApplication.preferenceHelper.getLatitudeC();
        if (view == null) {
            view = inflater.inflate(R.layout.activity_maps, container, false);
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        latitud = YolaborApplication.preferenceHelper.getLongitudeC();
        longitud = YolaborApplication.preferenceHelper.getLatitudeC();
        lati = Double.parseDouble(latitud);
        longit = Double.parseDouble(longitud);
        mMap = googleMap;

        Toast.makeText(getContext(), "LOGIN MAPA:"+ longitud, Toast.LENGTH_SHORT).show();
        Log.e("MAPALATI",latitud);
        Log.e("MAPALONGI",longitud);
        Log.e("+++",latitud);
        Log.e("***",longitud);
        // Add a marker in Sydney and move the camera
        LatLng gpsLocation = new LatLng(longit,lati);
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(longit,lati), 12.0f));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(gpsLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gpsLocation, Constants.MAP_ZOOM));
        mMap.addMarker(new MarkerOptions().position(gpsLocation).title("Your location"));
    }
}
