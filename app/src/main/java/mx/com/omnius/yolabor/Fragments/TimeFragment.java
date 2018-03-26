package mx.com.omnius.yolabor.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.com.omnius.yolabor.R;

/**
 * Created by UDIaz on 03/02/18.
 */

public class TimeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_time, container, false);
        }
        return view;
    }
}
