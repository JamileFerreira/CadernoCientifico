package com.example.jamile.cadernocientifico14;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jamile on 12/08/2017.
 */

public class PaginaAdapter  extends ArrayAdapter<Pagina> {
    private final Context context;
    private final ArrayList<Pagina> elementos;
    public PaginaAdapter(Context context, ArrayList<Pagina> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);

        Bitmap thumbnail = (BitmapFactory.decodeFile(elementos.get(position).getCaminhoBackground()));
        ImageView fundo = (ImageView) rowView.findViewById(R.id.fundo);

        fundo.setImageBitmap(thumbnail);
        TextView numPagina = (TextView) rowView.findViewById(R.id.numPagina);
        numPagina.setText(Integer.toString(elementos.get(position).getNumPagina()));
        TextView data = (TextView) rowView.findViewById(R.id.data);
        data.setText("AA/BB/CCCC");
        return rowView;
    }
}