package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragments.db.BaseDatos;
import com.example.fragments.db.ContructorContactos;
import com.example.fragments.pojo.Contacto;

public class RegistroContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_contacto);

        Button btn = (Button) findViewById(R.id.btnRegistro);
        EditText edtNombre = (EditText)  findViewById(R.id.edtNombre);
        EditText edtCorreo = (EditText)  findViewById(R.id.edtCorreo);
        EditText edtTelefono = (EditText)  findViewById(R.id.edtTelefono);
        BaseDatos bd = new BaseDatos(this);
        ContructorContactos contructorContactos = new ContructorContactos(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono = edtTelefono.getText().toString();
                String nombre = edtNombre.getText().toString();
                String correo = edtNombre.getText().toString();
                Contacto contacto = new Contacto();
                contacto.setLikes(0);
                contacto.setNombre(nombre);
                contacto.setTelefono(telefono);
                contacto.setEmail(correo);
                contacto.setFoto(0);
                contructorContactos.insertarContactos(bd,contacto);

                onBackPressed();
            }
        });


    }
}