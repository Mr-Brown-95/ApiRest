package com.example.apirest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apirest.Model.Periodista;

import java.util.List;

public class PeriodistaAdapter extends ArrayAdapter<Periodista> {

    private Context context;
    private List<Periodista> periodistas;

    public PeriodistaAdapter(@NonNull Context context, int resource, @NonNull List<Periodista> objects) {
        super(context, resource, objects);
        this.context = context;
        this.periodistas = objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.content_main, parent, false);

        TextView txtidPersona = (TextView) rowView.findViewById(R.id.IdPeriodista);
        TextView txtNombre = (TextView) rowView.findViewById(R.id.Nombre);
        TextView txtApellido1 = (TextView) rowView.findViewById(R.id.Apellido1);
        TextView txtApellido2 = (TextView) rowView.findViewById(R.id.Apellido2);
        TextView txtTelefono = (TextView) rowView.findViewById(R.id.Telefono);
        TextView txtEspecialidad = (TextView) rowView.findViewById(R.id.Especialidad);
        ;

        txtidPersona.setText(String.format("ID:%d", periodistas.get(position).getId()));
        txtNombre.setText(String.format("NOMBRE:%s", periodistas.get(position).getNombre()));
        txtApellido1.setText(String.format("APELLIDO1: %s", periodistas.get(position).getApellido1()));
        txtApellido2.setText(String.format("APELLIDO2: %s", periodistas.get(position).getApellido2()));
        txtTelefono.setText(String.format("TELEFONO: %s", periodistas.get(position).getTelefono()));
        txtEspecialidad.setText(String.format("ESPECIALIDAD: %s", periodistas.get(position).getEspecialidad()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PeriodistaActivity.class);
                intent.putExtra("ID", String.valueOf(periodistas.get(position).getId()));
                intent.putExtra("NOMBRE", periodistas.get(position).getNombre());
                intent.putExtra("APELLIDO1", periodistas.get(position).getApellido1());
                intent.putExtra("APELLIDO2", periodistas.get(position).getApellido2());
                intent.putExtra("TELEFONO", periodistas.get(position).getTelefono());
                intent.putExtra("ESPECIALIDAD", periodistas.get(position).getEspecialidad());
                context.startActivity(intent);
            }
        });
        return rowView;

    }

}
