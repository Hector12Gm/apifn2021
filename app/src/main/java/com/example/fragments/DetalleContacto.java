package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvCorreo;
    private ImageView imgC;
    private ImageView imageViewPhone;
    private ImageView imageViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        tvNombre = (TextView) findViewById( R.id.tvNombre );
        tvCorreo = (TextView) findViewById( R.id.tvCorreo );
        tvTelefono = (TextView) findViewById( R.id.tvTelefono );
        imgC = (ImageView) findViewById( R.id.imgC );

        imageViewEmail = (ImageView) findViewById( R.id.btnMail);
        imageViewPhone = (ImageView) findViewById( R.id.btnTelefono);

        Bundle bundle = getIntent().getExtras();

        tvNombre.setText( bundle.getString( getResources().getString( R.string.nombre ) ) );
        tvTelefono.setText( bundle.getString( getResources().getString( R.string.telefono ) ) );
        tvCorreo.setText ( bundle.getString( getResources().getString( R.string.correo ) ) );
        imgC.setImageResource( bundle.getInt( getResources().getString( R.string.foto ) ) );

        imageViewEmail.setOnClickListener(new Click());
        imageViewPhone.setOnClickListener(new Click());

    }

    protected void intent_call(){
        if (ContextCompat.checkSelfPermission(DetalleContacto.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(DetalleContacto.this, new String[] {
                    Manifest.permission.CALL_PHONE}, 10);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvTelefono.getText().toString()));
            startActivity(intent);
        }
    }

    protected   void intent_email(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,tvCorreo.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public class Click implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnMail:
                    intent_email();
                    break;
                case R.id.btnTelefono:
                    intent_call();
                    break;
            }
        }
    }
}