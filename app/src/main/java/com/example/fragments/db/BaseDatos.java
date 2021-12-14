package com.example.fragments.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.fragments.pojo.Contacto;

import java.util.ArrayList;

public class BaseDatos  extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBD.DATA_NAME, null, ConstantesBD.VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creamos las tablas
        String queryCrearTablaContacto = "CREATE TABLE "+ConstantesBD.TABLE_CONTACTS+ " ( "+
                ConstantesBD.TABLE_CONTACS_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ConstantesBD.TABLE_CONTACS_NOMBRE + " TEXT,"+
                ConstantesBD.TABLE_CONTACS_TELEFONO + " TEXT,"+
                ConstantesBD.TABLE_CONTACS_EMAIL + " TEXT,"+
                ConstantesBD.TABLE_CONTACS_FOTO + " INTEGER"
                +")";
        String queryCrearTablaLikes = "CREATE TABLE " + ConstantesBD.TABLE_LIKES_CONTACT +" ( "+
                    ConstantesBD.TABLE_LIKES_CONTACT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO + " INTEGER,"+
                    ConstantesBD.TABLE_LIKES_NUMERO_LIKES +" INTEGER, "+
                    "FOREIGN KEY ("+ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO+
                    ") REFERENCES "+ ConstantesBD.TABLE_CONTACTS+" (" + ConstantesBD.TABLE_CONTACS_ID+ ")"
                +")";

        //Ejecutamos las consultas
        db.execSQL(queryCrearTablaContacto);
        db.execSQL(queryCrearTablaLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se ejecuta cada vez que hay cambios
        db.execSQL("DROP TABLE IF EXIST "+ConstantesBD.TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_LIKES_CONTACT);
        onCreate(db);
    }

    public ArrayList<Contacto> obtenerTodosLosContactos(){

        //Creamos una lista con los contactos
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();

        //Prepramos la consulta
        String query = "SELECT * FROM "+ ConstantesBD.TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        //Obtenemos el cursor para iterar entre los registros
        Cursor cursor = db.rawQuery(query,null);


        //Regresara verdadero mientra haya registros que recorrer
        while (cursor.moveToNext()){

            //Creamos un nuevo contacto
            Contacto contactoActual = new Contacto();

            //Obtenemos cada dato por su orden de columna
            contactoActual.setId(cursor.getInt(0));
            contactoActual.setNombre(cursor.getString(1));
            contactoActual.setTelefono(cursor.getString(2));
            contactoActual.setEmail(cursor.getString(3));
            contactoActual.setFoto(cursor.getInt(4));

            //Preparamos nuestra consulta para obtener el numero de likes
            String querynew = String.format("SELECT COUNT(%s) AS LIKES FROM  %s WHERE %s = %s ",
                    ConstantesBD.TABLE_LIKES_NUMERO_LIKES,ConstantesBD.TABLE_LIKES_CONTACT,
                    ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO,contactoActual.getId());
            Cursor registrosLikes = db.rawQuery(querynew,null);
            //Si tiene likes le asignamos el numero que tiene
            if(registrosLikes.moveToNext())
                contactoActual.setLikes(registrosLikes.getInt(0));
            else
                contactoActual.setLikes(0);
            contactos.add(contactoActual);
        }

        //Cerramos la conexion a la base de datos
        db.close();
        return contactos;
    }

    public void insertarContacto(ContentValues contentValues){

        //obtenemos un obejeto para manipular la base de datos que permita la edición
        SQLiteDatabase db = this.getWritableDatabase();
        //Insertamos en la tabla que queremos, ademas de pasar los valores que se insertaran
        db.insert(ConstantesBD.TABLE_CONTACTS,null,contentValues);
        //Cerramos la conexión
        db.close();

    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_LIKES_CONTACT,null,contentValues);
        db.close();
    }

    //Obtiene el numero de likes
    public int obtenerLikesContacto(Contacto contacto){
        int likes = 0;
        String query = String.format("SELECT COUNT(%s) FROM  %s WHERE %s = %s ",
                ConstantesBD.TABLE_LIKES_NUMERO_LIKES,ConstantesBD.TABLE_LIKES_CONTACT,
                ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO,contacto.getId());
        Log.d("QUERY: ",query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(query,null);
        if(cursor.moveToNext())
            likes = cursor.getInt(0);
        db.close();
        return likes;
    }
}
