package com.example.antonio.encuentrahipotenochas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by antonio on 30/11/16.
 */

public class SelecPersonajeDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    // Declaración de variables
    RespSeleccPersonaje respSeleccPersonaje;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View miDialogo = inflater.inflate(R.layout.selec_personaje_spinner, null);

        builder.setView(miDialogo)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // OK
                    }
                });

        AdaptadorSeleccion adaptadorSeleccion = new AdaptadorSeleccion(getActivity(), R.layout.selec_personaje, getResources().getStringArray(R.array.nombres));

        Spinner spinnerSelecPersonaje = (Spinner) miDialogo.findViewById(R.id.spinner);
        spinnerSelecPersonaje.setAdapter(adaptadorSeleccion);
        spinnerSelecPersonaje.setOnItemSelectedListener(this);

        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        respSeleccPersonaje.onPersonajeSeleccionado(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Adaptador para generar un elemento personalizado para el spinner de selección de personaje.
     */
    public class AdaptadorSeleccion extends ArrayAdapter<String> {

        public AdaptadorSeleccion(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return crearFilaPersonalizada(position, convertView, parent);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return crearFilaPersonalizada(position, convertView, parent);
        }

        /**
         * Crea una fila personalizada para el spinner de selección.
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        public View crearFilaPersonalizada(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View miFila = inflater.inflate(R.layout.selec_personaje, parent, false);

            TextView personaje = (TextView) miFila.findViewById(R.id.personaje);
            String[] arrayNombres = getResources().getStringArray(R.array.nombres);
            TypedArray arrayImagenes = getResources().obtainTypedArray(R.array.imagenes);

            personaje.setText(arrayNombres[position]);
            personaje.setCompoundDrawablesWithIntrinsicBounds(arrayImagenes.getDrawable(position), null, null, null);

            return miFila;
        }
    }

    /**
     * Interfaz que permite devolver el personaje seleccionado.
     */
    public interface RespSeleccPersonaje {
        public void onPersonajeSeleccionado(int i);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        respSeleccPersonaje = (RespSeleccPersonaje) context;
    }
}
