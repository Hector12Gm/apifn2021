package com.example.fragments.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.fragments.R;
import com.example.fragments.pojo.Contacto;

import java.util.ArrayList;

public class ContructorContactos {

    private Context context;

    public ContructorContactos(Context context) {
        this.context = context;
    }

    public ArrayList<Contacto> obtenerDatos(){
       BaseDatos bd = new BaseDatos(this.context);

        return  bd.obtenerTodosLosContactos();
    }


    //Se require una base de datos ademas del contacto a insertar en la base dedatos
    public void insertarContactos(BaseDatos bd, Contacto contacto){

        //creamos los parametros que se enviaran a la consulta
        ContentValues contentValues1 = new ContentValues();

        //Agregamos el nombre del parametro o de la columna, seguido de sus valor
        //Para este caso se toma del contacto
        contentValues1.put(ConstantesBD.TABLE_CONTACS_NOMBRE,contacto.getNombre());
        contentValues1.put(ConstantesBD.TABLE_CONTACS_TELEFONO,contacto.getTelefono());
        contentValues1.put(ConstantesBD.TABLE_CONTACS_EMAIL,contacto.getEmail());
        contentValues1.put(ConstantesBD.TABLE_CONTACS_FOTO,R.drawable.img_1);
        //Llamamos a nuestro metodo del contacto
        bd.insertarContacto(contentValues1);
    }

    public void darLike(Contacto contacto){

        BaseDatos db = new BaseDatos(this.context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO, contacto.getId());
        contentValues.put(ConstantesBD.TABLE_LIKES_NUMERO_LIKES,1);
        db.insertarLikeContacto(contentValues);
    }

    public  int obtenerLikesContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(this.context);
        return db.obtenerLikesContacto(contacto);

    }
}
