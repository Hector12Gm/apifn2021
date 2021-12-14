package com.example.fragments.fragments;

import com.example.fragments.R;
import com.example.fragments.adapter.Adaptador;
import com.example.fragments.pojo.Contacto;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {


    public  void generarLinearLayoutVertical();

    public Adaptador generarAdaptador(ArrayList<Contacto> contactos);

    public void inicializarAdaptadorRV( Adaptador adaptador );

}
