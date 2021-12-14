package com.example.fragments.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragments.db.ContructorContactos;
import com.example.fragments.pojo.Contacto;
import com.example.fragments.DetalleContacto;
import com.example.fragments.R;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Adaptador extends  RecyclerView.Adapter<Adaptador.ViewOld> {

    private Activity activity;

    //COntructor
    public Adaptador(ArrayList<Contacto> lista, Activity activity) {
        this.activity = activity;
        this.lista = lista;
    }

    private ArrayList<Contacto> lista;

    @NonNull
    @Override
    public ViewOld onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Renderizamos la vista
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        ViewOld old = new ViewOld(vista);
        return old;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewOld holder, int position) {
        Contacto contacto = lista.get(position);
        holder.tvTelefonoContacto.setText(contacto.getTelefono());
        holder.tvNombreContacto.setText(contacto.getNombre());
        holder.imgCardContacto.setImageResource(contacto.getFoto());
        holder.tvLikes.setText(Integer.toString(contacto.getLikes()));
        //establecemos los datos
       holder.imgLike.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Snackbar.make(v,"Le dio like a "+contacto.getNombre(),Snackbar.LENGTH_SHORT).show();
               ContructorContactos contructorContactos = new ContructorContactos(v.getContext());
               contructorContactos.darLike(contacto);

               holder.tvLikes.setText(Integer.toString(contructorContactos.obtenerLikesContacto(contacto)));
           }
       });

       //Boton de click
        holder.imgCardContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento=new Intent(activity, DetalleContacto.class);
                intento.putExtra("nombre",contacto.getNombre());
                intento.putExtra("telefono",contacto.getTelefono());
                intento.putExtra("correo",contacto.getEmail());
                intento.putExtra("foto",contacto.getFoto());
                activity.startActivity(intento);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewOld extends RecyclerView.ViewHolder {

        private TextView tvNombreContacto;
        private TextView tvTelefonoContacto;
        private ImageView imgCardContacto;
        private ImageView imgLike;
        private TextView tvLikes;

        public ViewOld(@NonNull View itemView) {
            super(itemView);
            tvNombreContacto = (TextView) itemView.findViewById(R.id.tvNombreContacto);
            tvTelefonoContacto = (TextView) itemView.findViewById(R.id.tvTelefonoContacto);
            imgCardContacto = (ImageView) itemView.findViewById(R.id.imgCardContacto);
            imgLike = (ImageView) itemView.findViewById(R.id.imgLike);
            tvLikes  = (TextView) itemView.findViewById(R.id.tvLikes);




        }
        

    }
}
