package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest.Model.Periodista;
import com.example.apirest.Utils.Api;
import com.example.apirest.Utils.PeriodistaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeriodistaActivity extends AppCompatActivity {
    PeriodistaService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peridista_layout);

        TextView idper=(TextView)findViewById(R.id.Id);
        EditText txtId=(EditText)findViewById(R.id.txtId);

        TextView nombre=(TextView)findViewById(R.id.nombre);
        final EditText txtNombre=(EditText)findViewById(R.id.txtNombre);

        TextView apellido1=(TextView)findViewById(R.id.apellido1);
        final EditText txtApellido1=(EditText)findViewById(R.id.txtApellido1);

        TextView apellido2=(TextView)findViewById(R.id.apellido2);
        final EditText txtApellido2=(EditText)findViewById(R.id.txtApellido2);

        TextView telefono=(TextView)findViewById(R.id.telefono);
        final EditText txtTelefono=(EditText)findViewById(R.id.txtTelefono);

        TextView especialidad=(TextView)findViewById(R.id.especialidad);
        final EditText txtEspecialidad=(EditText)findViewById(R.id.txtEspecialidad);

        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);


        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String nom=bundle.getString("NOMBRE");
        String ape1=bundle.getString("APELLIDO1");
        String ape2=bundle.getString("APELLIDO2");
        String tel=bundle.getString("TELEFONO");
        String esp=bundle.getString("ESPECIALIDAD");

        txtId.setText(id);
        txtNombre.setText(nom);
        txtApellido1.setText(ape1);
        txtApellido2.setText(ape2);
        txtTelefono.setText(tel);
        txtEspecialidad.setText(esp);
        if(id.trim().length()==0||id.equals("")){
            idper.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Periodista p=new Periodista();
                p.setNombre(txtNombre.getText().toString());
                p.setApellido1(txtApellido1.getText().toString());
                p.setApellido2(txtApellido2.getText().toString());
                p.setTelefono(txtTelefono.getText().toString());
                p.setEspecialidad(txtEspecialidad.getText().toString());
                if(id.trim().length()==0||id.equals("")){
                    addPeriodista(p);
                    Intent intent =new Intent(PeriodistaActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    updatePeriodista(p,Integer.valueOf(id));
                    Intent intent =new Intent(PeriodistaActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePeriodista(Integer.valueOf(id));
                Intent intent =new Intent(PeriodistaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PeriodistaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void addPeriodista(Periodista p){
        service= Api.getPeriodistaService();
        Call<Periodista>call=service.addPeriodista(p);
        call.enqueue(new Callback<Periodista>() {
            @Override
            public void onResponse(Call<Periodista> call, Response<Periodista> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PeriodistaActivity.this,"Se agrego conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Periodista> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PeriodistaActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void updatePeriodista(Periodista p, int id){
        service= Api.getPeriodistaService();
        Call<Periodista>call=service.updatePeriodista(p,id);
        call.enqueue(new Callback<Periodista>() {
            @Override
            public void onResponse(Call<Periodista> call, Response<Periodista> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PeriodistaActivity.this,"Se Actualizó conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Periodista> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PeriodistaActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void deletePeriodista(int id){
        service= Api.getPeriodistaService();
        Call<Periodista>call=service.deletePeriodista(id);
        call.enqueue(new Callback<Periodista>() {
            @Override
            public void onResponse(Call<Periodista> call, Response<Periodista> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PeriodistaActivity.this,"Se Elimino el registro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Periodista> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PeriodistaActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
