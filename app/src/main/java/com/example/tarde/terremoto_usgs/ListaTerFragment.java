package com.example.tarde.terremoto_usgs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by tarde on 17/04/15.
 */
public class ListaTerFragment extends Fragment {

    private ListView listaTerremotos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_lista,container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        String url = getResources().getString(R.string.feed_usgs);
        //feed_4.5
        //url = getResources().getString(R.string.feed_4_5);
        //url = getResources().getString(R.string.feed_day);
        url = getResources().getString(R.string.feed_day);

        listaTerremotos =(ListView) getActivity().findViewById(R.id.listViewTerremotos);

        DescargaTerremotos descargaTerremotos=new DescargaTerremotos(listaTerremotos);

        descargaTerremotos.execute(url);
        getActivity().registerForContextMenu(listaTerremotos);
        //registerForContextMenu(listaTerremotos);

    }

    public void SetListaTerremotosClickListener(AdapterView.OnItemClickListener listaListener) {
        listaTerremotos.setOnItemClickListener(listaListener) ;
    }

    public Terremoto getTerremoto(int position) {
        return (Terremoto) listaTerremotos.getAdapter().getItem(position);
    }

}
