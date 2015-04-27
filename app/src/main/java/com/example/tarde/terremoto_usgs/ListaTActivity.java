package com.example.tarde.terremoto_usgs;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class ListaTActivity extends ActionBarActivity {


    protected ListView listaTerremotos;
    protected ArrayList<Terremoto> terremotos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String url = getResources().getString(R.string.feed_usgs);
        //feed_4.5
        url = getResources().getString(R.string.feed_4_5);
        // inicio entrada terremoto es entry
        //Queremos title,substr(2,6) para la magnitud
        // updated para la fecha formato("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
        // link, atributo href con xmlpullparse.getAttribute(null,"href"); cuando el type sea "text/html"
        //georss:point incluye pareja long, lat. Hay que parsearlo. Funcion split dandole el ' ' como separador
        listaTerremotos = (ListView) findViewById(R.id.listViewTerremotos);
        DescargaTerremotos descargaTerremotos=new DescargaTerremotos(listaTerremotos);

        descargaTerremotos.execute(url);

        registerForContextMenu(listaTerremotos);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_contexto, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Intent intent = new Intent(ListaTActivity.this, DetalleActivity.class);

//        Serializable terremoto_ser = (Serializable) terremotos.get(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
        Serializable terremoto_ser = (Serializable) listaTerremotos.getAdapter().getItem(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
        intent.putExtra("terremoto",terremoto_ser);
        startActivity(intent);
        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
