package com.example.fragments.presentador;

import android.content.Context;
import android.content.Intent;

import com.example.fragments.db.ContructorContactos;
import com.example.fragments.fragments.IRecyclerViewFragmentView;
import com.example.fragments.pojo.Contacto;

import java.util.ArrayList;

public class RecyclerViewPresenter implements IRecyclerViewPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ContructorContactos contructorContactos;
    private ArrayList<Contacto> contactos;


    ///Obttiene el contexto del la actividad
    public RecyclerViewPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;



    }


    @Override
    public void obtenerContactosBaseDatos() {

        //Obtenemos los datos de la base de datos
        contructorContactos = new ContructorContactos(this.context);
        contactos = contructorContactos.obtenerDatos();
        mostrarContactosRV();

    }

    @Override
    public void mostrarContactosRV() {

        //Mandamos a llamar los metodo
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.generarAdaptador(contactos));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
