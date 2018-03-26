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

public class PerfilFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        /*
        //extraemos el drawable en un bitmap
        Drawable originalDrawable = getResources().getDrawable(R.drawable.photo_male);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado


        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());


        ImageView imageView = (ImageView) view.findViewById(R.id.image_perfi);

        imageView.setImageDrawable(roundedDrawable);
        */


        if (view == null) {
            view = inflater.inflate(R.layout.fragemnt_perfil, container, false);
        }
        return view;
    }

}
