package com.example.fragments.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fragments.RegistroContacto;
import com.example.fragments.pojo.Contacto;
import com.example.fragments.R;
import com.example.fragments.adapter.Adaptador;
import com.example.fragments.presentador.RecyclerViewPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {


    ///Recycler viewer
    private RecyclerView rcViewer;
    private  RecyclerViewPresenter presenter;
    private View v;
    //Creamos un adaptador
    Adaptador adaptador;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        //Renderizamos la vista
        v = inflater.inflate(R.layout.fragment_recyclerview,container,false);
        rcViewer = (RecyclerView) v.findViewById(R.id.rcViewer);
        //Creamos nuestro presentador
        presenter = new RecyclerViewPresenter(this,getContext());
        presenter.obtenerContactosBaseDatos();


        //Obtenemos nuestro boton para agregar
        FloatingActionButton btn = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);
        Intent i = new Intent(v.getContext(), RegistroContacto.class);
        //Creamos el evento del click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(v.getContext(), RegistroContacto.class));
            }
        });
        return v;


    }

    @Override
    public void generarLinearLayoutVertical() {
        //Seteamos el layoutmanager
        LinearLayoutManager ll =new LinearLayoutManager( getActivity(),LinearLayoutManager.VERTICAL,false);
        rcViewer.setLayoutManager(ll);
    }

    @Override
    public Adaptador generarAdaptador(ArrayList<Contacto> contactos) {
        //Creamos nuestro adaptador, le pasamos los datos y la actividad
        adaptador = new Adaptador(contactos,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(Adaptador adaptador) {
        //Establecemos el adaptador al rc.
        rcViewer.setAdapter(adaptador);


    }
}
