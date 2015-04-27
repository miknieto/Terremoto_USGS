package com.example.tarde.terremoto_usgs;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class DetalleFrActivity extends ActionBarActivity {

    protected Terremoto recibido;
    DetalleTerFragment detalleTerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_fr);

        recibido = (Terremoto) getIntent().getSerializableExtra("terremoto");

        FragmentManager fragmentManager = getFragmentManager();
        detalleTerFragment = (DetalleTerFragment) getFragmentManager().findFragmentById(R.id.fr_detalle);
        detalleTerFragment.setTerremoto(recibido);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle, menu);
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
