package com.example.tarde.terremoto_usgs;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class DetalleActivity extends ActionBarActivity {

    protected Terremoto recibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        recibido = (Terremoto) getIntent().getSerializableExtra("terremoto");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        EditText etTitulo = (EditText) findViewById(R.id.editText_Titulo);
        EditText etMagnitud = (EditText) findViewById(R.id.editText_Magnitud);
        EditText etLink = (EditText) findViewById(R.id.editText_Link);
        EditText etFecha = (EditText) findViewById(R.id.editText_Fecha);
        EditText etLongitud = (EditText) findViewById(R.id.editText_Longitud);
        EditText etLatitud = (EditText) findViewById(R.id.editText_Latitud);


        etTitulo.setText(recibido.getTitulo());
        etMagnitud.setText(recibido.getMagnitud().toString());
        etLink.setText(recibido.getLink());
        etFecha.setText(sdf.format( recibido.getFecha()));
        etLongitud.setText(recibido.getLongitud().toString());
        etLatitud.setText(recibido.getLatitud().toString());


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
