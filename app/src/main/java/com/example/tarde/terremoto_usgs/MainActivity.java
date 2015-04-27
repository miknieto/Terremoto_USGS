package com.example.tarde.terremoto_usgs;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.io.Serializable;


public class MainActivity extends ActionBarActivity {

    private ListaTerFragment listaTerFragment;
    private DetalleTerFragment detalleTerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        listaTerFragment = (ListaTerFragment) fragmentManager.findFragmentById(R.id.fr_listado);
        detalleTerFragment = (DetalleTerFragment) fragmentManager.findFragmentById(R.id.fr_detalle);

//        AdapterView.OnItemClickListener listaListener=new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            }
//        };


    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {

        listaTerFragment.SetListaTerremotosClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (detalleTerFragment != null && detalleTerFragment.isInLayout()) {

                    Log.i("depura","posicion adaptador " + position);
                    detalleTerFragment.setTerremoto( listaTerFragment.getTerremoto(position));

                }
            }

        });


//        if (detalleTerFragment != null && detalleTerFragment.isInLayout()) {
//
//            detalleTerFragment.setTerremoto( listaTerFragment.getTerremoto(1));
//
//        }

         super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (detalleTerFragment != null && detalleTerFragment.isInLayout()) {
//
//            detalleTerFragment.setTerremoto( listaTerFragment.getTerremoto(1));
//
//        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_contexto, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (detalleTerFragment == null || !detalleTerFragment.isInLayout()) {

            Intent intent = new Intent(MainActivity.this, DetalleFrActivity.class);

            Serializable terremoto_ser = (Serializable) listaTerFragment.getTerremoto(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
            intent.putExtra("terremoto", terremoto_ser);
            startActivity(intent);
        } else {
            detalleTerFragment.setTerremoto((Terremoto) listaTerFragment.getTerremoto(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position));
        }
        return super.onContextItemSelected(item);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
