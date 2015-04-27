package com.example.tarde.terremoto_usgs;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by tarde on 13/04/15.
 */



public class DescargaTerremotos extends AsyncTask<String, Void,List<Terremoto>>{


    private ListView resultado;

    public DescargaTerremotos(ListView resultado) {
        this.resultado = resultado;

    }

    @Override
    protected List<Terremoto> doInBackground(String... params) {

        InputStream is = null;

        try{
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            is = conn.getInputStream();

            return procesarTerremotos(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Terremoto> procesarTerremotos(InputStream is) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser= factory.newSAXParser();
        SaxTerHandler handler = new SaxTerHandler();

        parser.parse(is,handler);

        return handler.getResultado();


    }

    @Override
    protected void onPostExecute(List<Terremoto> terremotos) {


        super.onPostExecute(terremotos);

        estableceIconos(terremotos);

        ListaTerAdapter adaptador =new ListaTerAdapter(resultado.getContext(),terremotos);

//        ArrayAdapter<Terremoto> adaptador= new ArrayAdapter<Terremoto>(resultado.getContext(),android.R.layout.simple_list_item_1,terremotos);


        resultado.setAdapter(adaptador);

    }

    private void estableceIconos(List<Terremoto> terremotos) {

        for (int i= 0 ; i<terremotos.size();i++){
            double magnitud = terremotos.get(i).getMagnitud();
            if ( magnitud == 0 ){
                terremotos.get(i).setIconoMagnitud(R.drawable.terremoto_desconocido);
            }else{
                if (magnitud > 4.5) {
                    terremotos.get(i).setIconoMagnitud(R.drawable.terremoto_rojo);
                }else if (magnitud <= 2.5){
                    terremotos.get(i).setIconoMagnitud(R.drawable.terremoto_verde);
                }else{
                    terremotos.get(i).setIconoMagnitud(R.drawable.terremoto_naranja);
                }
            }
        }

    }

}
