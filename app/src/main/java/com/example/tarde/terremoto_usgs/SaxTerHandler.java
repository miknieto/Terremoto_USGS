package com.example.tarde.terremoto_usgs;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tarde on 13/04/15.
 */
public class SaxTerHandler extends DefaultHandler{

    private ArrayList<Terremoto> resultado;
    private Terremoto terreActual;
    private StringBuilder sb;

    public ArrayList<Terremoto> getResultado() {
        return resultado;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        resultado=new ArrayList<Terremoto>();
        sb = new StringBuilder();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        sb = new StringBuilder();

        if (localName.equals("entry")) terreActual = new Terremoto();
        if (terreActual != null) {
            if (localName.equals("link")) {
                if (attributes.getValue("type").equals("text/html"))
                    terreActual.setLink(attributes.getValue("href"));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (terreActual != null) {
            if (localName.equals("entry")) resultado.add(terreActual);
            else if (localName.equals("title")) {
                String texto = sb.toString();
                terreActual.setTitulo(texto);
                terreActual.setMagnitud( Float.valueOf(texto.substring(2,6).trim()) );
            }else if(localName.equals("updated")){
                try {
                    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
                    String fecha = sb.toString();
                    Date fechaDate = null;
                    fechaDate = sdf.parse(fecha);
                    terreActual.setFecha(fechaDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else if (localName.equals("point")){
                String texto = sb.toString();
                String []coordenadas;

                coordenadas = texto.split(" ");
                if (coordenadas.length>0) {
                    terreActual.setLongitud(Float.valueOf(coordenadas[0]));
                    terreActual.setLatitud(Float.valueOf(coordenadas[1]));
                }

            }

        }



    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        sb.append(ch,start,length);

    }
}
