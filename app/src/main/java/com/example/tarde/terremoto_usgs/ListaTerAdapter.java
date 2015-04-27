package com.example.tarde.terremoto_usgs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarde on 21/04/15.
 */
public class ListaTerAdapter extends BaseAdapter {

    private Context context;
    private List<Terremoto> arrayList;

    public ListaTerAdapter(Context contexto, List<Terremoto> terremotos) {
        this.arrayList = terremotos;
        this.context = contexto;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ElemVista{
        TextView titulo_item;
        TextView fecha_item;
        ImageView icono;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ElemVista view;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);


        if (convertView == null){

            view = new ElemVista();
            convertView = inflater.inflate(R.layout.listater_item,null);
            view.titulo_item = (TextView) convertView.findViewById(R.id.texto_titulo);
            view.fecha_item = (TextView) convertView.findViewById(R.id.texto_fecha);
            view.icono = (ImageView) convertView.findViewById(R.id.image_icon);


        }else{
            view = (ElemVista) convertView.getTag();

        }

        Terremoto eq = arrayList.get(position);
        view.titulo_item.setText(eq.getTitulo());
        view.fecha_item.setText(eq.getStringFecha());

        view.icono.setImageResource(eq.getIconoMagnitud());

        convertView.setTag(view);


        return convertView;
    }
}
