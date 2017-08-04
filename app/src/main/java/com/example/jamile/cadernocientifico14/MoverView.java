package com.example.jamile.cadernocientifico14;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Vector;

/**
 * Created by Jamile on 19/07/2017.
 */
public class MoverView extends View {
    float  distancia = 0;
    double cenx =0;
    double ceny = 0;
    static final  int PDF=1;
    static final  int VIDEO=2;
    static final  int LINK=3;
    static final  int IMAGEM=4;
    static final  int TEXTO=5;
    float antesX, antesY;
    int apertei =0;


    View l2;
    ImageView img;
    Bitmap thumbnail;
    //MainActivity.MoverView mover;
    private ImageView imagem;
    private final int GALERIA_IMAGENS=1;
    private final  int PERMISSAO_REQUEST =2;
    View view;

    Vector <Float> x=new Vector <Float> ();
    Vector <Float>  y=new Vector<Float>  ();
    //float[] radio = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};
    final float RADIO = 30;
    Vector paint = new  Vector();
    //Paint paint[] = new Paint[2];
    Paint p,p2,p3,p4;

    // DesenhaPonto ponto;

    int circulo = -1;
    String txt = "Mueve Algun circulo";
    Drawable imagen;
    static  Ponto ponto;
    static Vector <Ponto> pontos=new Vector<Ponto>();
    public MoverView(Context context) {
        super(context);
    }

    void criarPonto(int tipo, String caminhoLink){

        if(tipo==PDF){
            Paint p= new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.RED);
            paint.add(p);/////////
            ponto=new Ponto();
            ponto.setPaint(p);
            //ponto.setCaminhoLink(CaminhoLink);
            //Log.i("CAMINHOOOO6666", String.valueOf(CaminhoLink));
            x.add((float) 100);
            y.add((float) 150);
            invalidate ();
        }
        else if(tipo==VIDEO){
            Paint p= new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.BLUE);
            paint.add(p);
            ponto=new Ponto();
            ponto.setPaint(p);
           // ponto.setCaminhoLink(CaminhoLink);
            pontos.add(ponto);
            x.add((float) 100);
            y.add((float) 150);
            invalidate ();
        }
        else if(tipo==LINK){
            Paint p= new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.YELLOW);
            paint.add(p);
            ponto=new Ponto();
            ponto.setPaint(p);
            //ponto.setCaminhoLink(CaminhoLink);
            x.add((float) 100);
            y.add((float) 150);
            invalidate ();
        }
        else if(tipo==TEXTO){
            Paint p= new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.GRAY);
            paint.add(p);
            ponto=new Ponto();
            ponto.setPaint(p);
           // ponto.setCaminhoLink(CaminhoLink);
            x.add((float) 100);
            y.add((float) 150);
            invalidate ();
        }
        else if(tipo==IMAGEM){
            Paint p= new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.BLACK);
            paint.add(p);
            ponto=new Ponto();
            ponto.setPaint(p);
           // ponto.setCaminhoLink(CaminhoLink);
            x.add((float) 100);
            y.add((float) 150);
            invalidate ();
        }
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.argb(0, 0, 0, 0));
        canvas.drawColor(Color.TRANSPARENT);
        for (int i = 0; i < paint.size(); i++) {
            canvas.drawCircle(x.get(i), y.get(i), RADIO, (Paint) pontos.get(i).getPaint());
        }
    }

    public boolean onTouchEvent(MotionEvent evento) {

        float getx = evento.getX();
        // Log.i("getx", String.valueOf(getx));
        float gety = evento.getY();
        int acction = evento.getAction();

        // circulo=-1;
        if (acction == MotionEvent.ACTION_DOWN) {
            for (int i = 0; i < paint.size(); i++) {
                cenx = getx - x.get(i);
                ceny = gety - y.get(i);
                distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                // Log.i("distancia", String.valueOf(distancia));
                if (distancia <= 50) {
                    antesX=x.get(i);
                    antesY=y.get(i);
                    circulo = i;
                    // txt = "El circulo tocado es" + i;
                    invalidate();
                }
            }
        }
        if(acction==MotionEvent.ACTION_MOVE){
            Log.i("distancia2", String.valueOf(distancia));
            Log.i("********************","********");
            //Log.i("distancia1", String.valueOf(circulo));
            cenx = getx - x.get(circulo);
            ceny = gety - y.get(circulo);
            distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
            if(distancia<60 && distancia>12){
                if(circulo>-1){
                    //posição depois do  movimento
                    x.set(circulo,getx);
                    y.set(circulo,gety);
                    invalidate();
                }
                // circulo=-1;
            }


            else if((Math.abs(getx-antesX)<7&&Math.abs(gety-antesY)<7)){
                Log.i("distanciaXantesX",String.valueOf(antesX));
                Log.i("distanciaYantesY",String.valueOf(antesY));
                Log.i("distanciagetx",String.valueOf(getx));
                Log.i("distanciagety",String.valueOf(gety));//
                //chama
                apertei=1;





                //String caminhoLink="";
                Log.i("distancia_aperteiM", String.valueOf(apertei));
               // Intent intent2 = new Intent (String.valueOf(this.class));
////
//                    //intent.putExtra ("apertei", apertei) ;
//
//                    //Abrindo a galeria
//
//                    intent = new Intent(Intent.ACTION_PICK,
//                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, GALERIA_IMAGENS);


            }
            else {// calcular a distancia do toque atual com a distancia do circulo atual
                Log.i("dist",String.valueOf(circulo));
            }
        }

        // Log.i("distancia2","**********************************************************************", String.valueOf(PDF));
        return true;
    }

    public class PDF_Activity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Toast.makeText(this,
               "Estou no nav_manage!!!", Toast.LENGTH_LONG).show();
            File file = new File(ponto.getCaminhoLink());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
//            setContentView(R.layout.activity_pdf);
//            //PDFVIEW SHALL DISPLAY OUR PDFS
//            PDFView pdfView= (PDFView) findViewById(R.id.pdfView);
//            //SCROLLBAR TO ENABLE SCROLLING
//            // ScrollBar scrollBar = (ScrollBar) findViewById(R.id.scrollBar);
//            // pdfView.setScrollBar(scrollBar);
//            //VERTICAL SCROLLING
//            // scrollBar.setHorizontal(false);
//            //SACRIFICE MEMORY FOR QUALITY
//            //pdfView.useBestQuality(true)
//            //UNPACK OUR DATA FROM INTENT
//            Intent i=this.getIntent();
//            String path=i.getExtras().getString("PATH");
//            //GET THE PDF FILE
//            File file=new File(path);
//            if(file.canRead())
//            {
//                //LOAD IT
//                pdfView.fromFile(file).defaultPage(1).onLoad(new OnLoadCompleteListener() {
//                    @Override
//                    public void loadComplete(int nbPages) {
//                        Toast.makeText(com.example.jamile.cadernocientifico14.MoverView.PDF_Activity.this, String.valueOf(nbPages), Toast.LENGTH_LONG).show();
//                    }
//                }).load();
//            }
       }
    }

}

