package com.utn.frt.sysacadapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by pablo on 11/06/2016.
 */
public class Parser extends AsyncTask<Void,Void,Void> {

    private MainActivity mainView;
    private String legajo;
    private String pass;
    private Elements elements;

    public Parser(MainActivity mainView) {
        this.mainView = mainView;
        this.legajo = this.mainView.getLegajo().getText().toString();
        this.pass = this.mainView.getPass().getText().toString();
        this.elements = null;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Log.d("Resultados","Conectando...");
            Log.d("Resultados",this.legajo);
            Log.d("Resultados",this.pass);
            Document doc = Jsoup.connect("http://sysacad.frt.utn.edu.ar/menuAlumno.asp")
                    .data("legajo", this.legajo)
                    .data("password", this.pass)
                    .post();
            Elements title = doc.getElementsByTag("title");
            this.elements = title;
        } catch (IOException ex) {
        System.err.println("Error "+ex.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("Resultados",this.elements.get(0).text().toString());
        Toast.makeText(mainView,this.elements.get(0).text().toString(),Toast.LENGTH_LONG).show();
    }
}