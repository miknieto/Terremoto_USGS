package com.example.tarde.terremoto_usgs;

import android.net.ParseException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tarde on 06/04/15.
 */
public class Terremoto implements Serializable {

    private String titulo;
    private Float magnitud;
    private String link;
    private Date fecha;
    private Float longitud;
    private Float latitud;
    private int iconoMagnitud; //pq es recurso

    public Terremoto(String titulo, Float magnitud, String link, String fecha, Float longitud, Float latitud) {
        this.titulo = titulo;
        this.magnitud = magnitud;
        this.link = link;
        this.fecha = new Date();
        this.longitud = longitud;
        this.latitud = latitud;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {

            this.fecha = formatter.parse(fecha);

        } catch (java.text.ParseException e) {

            e.printStackTrace();
        }


    }


    public Terremoto(String titulo, Float magnitud, String link, Date fecha, Float longitud, Float latitud) {
        this.titulo = titulo;
        this.magnitud = magnitud;
        this.link = link;
        this.fecha = fecha;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Terremoto(String titulo, Float magnitud) {
        this.titulo = titulo;
        this.magnitud = magnitud;
    }

    public Terremoto() {

    }

    @Override
    public String toString() {
        return titulo + ": magnitud=" + magnitud ;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(Float magnitud) {
        this.magnitud = magnitud;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getStringFecha() {

        String retorno;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm zzzz");
        retorno = formatter.format(fecha);

        return retorno;


    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public int getIconoMagnitud() {
        return iconoMagnitud;
    }

    public void setIconoMagnitud(int iconoMagnitud) {
        this.iconoMagnitud = iconoMagnitud;
    }
}
