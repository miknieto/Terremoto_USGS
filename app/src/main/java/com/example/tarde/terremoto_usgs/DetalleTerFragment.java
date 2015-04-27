package com.example.tarde.terremoto_usgs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.SimpleDateFormat;

/**
 * Created by tarde on 17/04/15.
 */
public class DetalleTerFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_detalle,container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void setTerremoto(Terremoto recibido) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        EditText etTitulo = (EditText) getActivity().findViewById(R.id.editText_Titulo);
        EditText etMagnitud = (EditText) getActivity().findViewById(R.id.editText_Magnitud);
        EditText etLink = (EditText) getActivity().findViewById(R.id.editText_Link);
        EditText etFecha = (EditText) getActivity().findViewById(R.id.editText_Fecha);
        EditText etLongitud = (EditText) getActivity().findViewById(R.id.editText_Longitud);
        EditText etLatitud = (EditText) getActivity().findViewById(R.id.editText_Latitud);


        etTitulo.setText(recibido.getTitulo());
        etMagnitud.setText("Magnitud: " + recibido.getMagnitud().toString());
        etLink.setText(recibido.getLink());
        etFecha.setText("Fecha: " + sdf.format( recibido.getFecha()));
        etLongitud.setText("Long.: " + recibido.getLongitud().toString());
        etLatitud.setText("Lat.:" + recibido.getLatitud().toString());


    }
}
