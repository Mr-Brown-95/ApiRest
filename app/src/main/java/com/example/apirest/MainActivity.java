package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;

import com.example.apirest.Model.Periodista;
import com.example.apirest.Utils.Api;
import com.example.apirest.Utils.PeriodistaService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    PeriodistaService periodistaService;
    List<Periodista> listPeriodista =new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.listView);

        listarPeriodista();

        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this, PeriodistaActivity.class);
               intent.putExtra("ID","");
                intent.putExtra("NOMBRE","");
                intent.putExtra("APELLIDO1","");
                intent.putExtra("APELLIDO2","");
                intent.putExtra("TELEFONO","");
                intent.putExtra("ESPECIALIDAD","");
               startActivity(intent);
            }
        });

    }

    public void listarPeriodista(){
        periodistaService = Api.getPeriodistaService();
        Call<List<Periodista>>call= periodistaService.getPeriodista();
        call.enqueue(new Callback<List<Periodista>>() {
            @Override
            public void onResponse(Call<List<Periodista>> call, Response<List<Periodista>> response) {
                if(response.isSuccessful()) {
                    listPeriodista = response.body();
                    listView.setAdapter(new PeriodistaAdapter(MainActivity.this,R.layout.content_main, listPeriodista));
                }
            }

            @Override
            public void onFailure(Call<List<Periodista>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
