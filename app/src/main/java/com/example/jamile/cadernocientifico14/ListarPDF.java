package com.example.jamile.cadernocientifico14;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class ListarPDF extends Activity {
    ListView lv;
    String caminhoPDF = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pdf);

        lv = (ListView) findViewById(R.id.lv);

        lv.setVisibility(View.VISIBLE);
        lv.setAdapter(new CustomAdapter(ListarPDF.this));

    }

    public class CustomAdapter extends BaseAdapter {
        Context c;
        ArrayList<PDFDoc> pdfDocs;
        private String caminhoPDF;

        public CustomAdapter(Context c) {
            this.c = c;
            this.pdfDocs = getPDFs();
        }

        @Override
        public int getCount() {
            return pdfDocs.size();
        }

        @Override
        public Object getItem(int i) {
            return pdfDocs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                //INFLATE CUSTOM LAYOUT
                view = LayoutInflater.from(c).inflate(R.layout.model, viewGroup, false);
            }
            final PDFDoc pdfDoc = (PDFDoc) this.getItem(i);
            TextView nameTxt = (TextView) view.findViewById(R.id.nameTxt);
            ImageView img = (ImageView) view.findViewById(R.id.pdfImage);
            //BIND DATA
            nameTxt.setText(pdfDoc.getName());
            img.setImageResource(R.drawable.pdf_icon);
            //VIEW ITEM CLICK
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lv.setVisibility(View.GONE);
                    caminhoPDF = pdfDoc.getPath();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("resultado", pdfDoc.getPath());
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            });
            return view;
        }

        //OPEN PDF VIEW
        private void openPDFView(String path) {

            // ((MainActivity)getActivity()).getSupportFragmentManager().popBackStack();
            Intent i = new Intent(c, PDF_Activity.class);
            i.putExtra("PATH", path);
            c.startActivity(i);
        }

        ArrayList<PDFDoc> getPDFs() {
            ArrayList<PDFDoc> pdfDocs = new ArrayList<>();
            //TARGET FOLDER
            File downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            PDFDoc pdfDoc;
            if (downloadsFolder.exists()) {
                //GET ALL FILES IN DOWNLOAD FOLDER
                File[] files = downloadsFolder.listFiles();
                //LOOP THRU THOSE FILES GETTING NAME AND URI
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    if (file.getPath().endsWith("pdf")) {
                        pdfDoc = new PDFDoc();
                        pdfDoc.setName(file.getName());
                        pdfDoc.setPath(file.getAbsolutePath());
                        pdfDocs.add(pdfDoc);
                    }
                }
            }
            return pdfDocs;
        }


    }
}