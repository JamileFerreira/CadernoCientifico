package com.example.jamile.cadernocientifico14;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final  int PDF=1;
    static final  int VIDEO=2;
    static final  int LINK=3;
    static final  int IMAGEM=4;
    static final  int TEXTO=5;
    static String CaminhoLink;
    static Vector <Ponto> pontos=new Vector<Ponto>();
    View l2;
    ImageView img;
    Bitmap thumbnail;
    static MoverView mover;
    private ImageView imagem;
    private final int GALERIA_IMAGENS=1;
    private final  int PERMISSAO_REQUEST =2;
    View view;
    static  ListView lv;
    Matrix matrix=new Matrix();
    Float scale=1f;
    ScaleGestureDetector SGD;
    PhotoViewAttacher mAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Intent intent2 = getIntent();
        Bundle bundle= intent2.getExtras();
        String picturePath = bundle.getString("DEUS");

        thumbnail = (BitmapFactory.decodeFile(picturePath));
        ImageView image = (ImageView) findViewById(R.id.img22);
        //Intent intent2 = getIntent();
//        Bundle bundle= intent2.getExtras();
//        String picturePath = bundle.getString("DEUS");
        image.setImageBitmap(thumbnail);
//       final ImageView zoon=(ImageView)findViewById(R.id.imageView);

        mover = new MoverView(this);
        mover.criarPonto(PDF);
        Log.i("CAMINHOOOO1", String.valueOf(CaminhoLink));
        view=findViewById(R.id.view_root2);

        final LinearLayout screenlayout = (LinearLayout) findViewById(R.id.view_root2);
        screenlayout.addView(mover);
        view.setBackground(image.getDrawable());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
   private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector detector)
        {
            scale=scale*detector.getScaleFactor();
            scale=Math.max(0.1f,Math.min(scale,5f));
            matrix.setScale(scale,scale);
            imagem.setImageMatrix(matrix);
            return true;
        }
    }
    @Override
    public  boolean onTouchEvent(MotionEvent event){
        SGD.onTouchEvent(event);
        return  true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
             lv= (ListView) findViewById(R.id.lv);
            //visibilidade
//            TextView layone= (TextView) view.findViewById(R.id.layone);
//
//            layone.setVisibility(View.VISIBLE);
            lv.setVisibility(View.VISIBLE);
            lv.setAdapter(new CustomAdapter(MainActivity.this));
           // Log.i("CAMINHOOOO2", String.valueOf(CaminhoLink));
          //  mover.criarPonto(PDF);

             Toast.makeText(this,
                    "Estou no nav_share!!!", Toast.LENGTH_LONG).show();
//            this.getSupportFragmentManager().popBackStack();
//            this.getSupportFragmentManager().findFragmentById(R.id.fragment2);
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment2, new EmBrancoFragment()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {mover.criarPonto(VIDEO);
//            Toast.makeText(this,
//                    "Estou no fragmento!!!", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_slideshow) {  mover.criarPonto(TEXTO);// Toast.makeText(this,
//                "Estou no nav_slideshow!!!", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_manage) {mover.criarPonto(LINK);
//            Toast.makeText(this,
//                "Estou no nav_manage!!!", Toast.LENGTH_LONG).show();
        } else if (id == R.id.img) {mover.criarPonto(IMAGEM);// Toast.makeText(this,
            //"Estou no nav_share!!!", Toast.LENGTH_LONG).show();

//        } else if (id == R.id.nav_send) {Toast.makeText(this,
//                "Estou no nav_send!!!", Toast.LENGTH_LONG).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////

    //Retorna se houve ou não permissão
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
// A permissão foi concedida. Pode continuar
            } else {
// A permissão foi negada. Precisa ver o que deve ser desabilitado
            }
            return;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////// CustomAdapter ////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class CustomAdapter extends BaseAdapter {
        Context c;
        ArrayList<PDFDoc> pdfDocs;
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
            if(view==null)
            {
                //INFLATE CUSTOM LAYOUT
                view= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
            }
            final PDFDoc pdfDoc= (PDFDoc) this.getItem(i);
            TextView nameTxt= (TextView) view.findViewById(R.id.nameTxt);
            ImageView img= (ImageView) view.findViewById(R.id.pdfImage);
            //BIND DATA
            nameTxt.setText(pdfDoc.getName());
            img.setImageResource(R.drawable.pdf_icon);
            //VIEW ITEM CLICK
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CaminhoLink=pdfDoc.getPath();
                    Log.i("CAMINHOOOO5555", String.valueOf(CaminhoLink));
                    lv.setVisibility(View.GONE);
                    mover.criarPonto(PDF);//////////////////////////////////////////////


                }
            });
            return view;
        }
        //OPEN PDF VIEW
        private void openPDFView(String path)
        {

           // ((MainActivity)getActivity()).getSupportFragmentManager().popBackStack();
            Intent i=new Intent(c,PDF_Activity.class);
            i.putExtra("PATH",path);
            c.startActivity(i);
        }

        private static ArrayList<PDFDoc> getPDFs()
        {
            ArrayList<PDFDoc> pdfDocs=new ArrayList<>();
            //TARGET FOLDER
            File downloadsFolder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            PDFDoc pdfDoc;
            if(downloadsFolder.exists())
            {
                //GET ALL FILES IN DOWNLOAD FOLDER
                File[] files=downloadsFolder.listFiles();
                //LOOP THRU THOSE FILES GETTING NAME AND URI
                for (int i=0;i<files.length;i++)
                {
                    File file=files[i];
                    if(file.getPath().endsWith("pdf"))
                    {
                        pdfDoc=new PDFDoc();
                        pdfDoc.setName(file.getName());
                        pdfDoc.setPath(file.getAbsolutePath());
                        pdfDocs.add(pdfDoc);
                    }
                }
            }
            return pdfDocs;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////// MoverView ////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

        float[] x = {50, 50,50,50, 50,50,50, 50,50,50, 50,50,50, 50,50,50, 50,50,50, 50,50,50, 50,50};
        float[] y = {130, 130,130,130, 130,130,130, 130,130,130, 130,130,130, 130,130,130, 130,130};
        //float[] radio = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};
        float[] radio = {20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20};
        Vector paint = new  Vector();
        //Paint paint[] = new Paint[2];
        Paint p,p2,p3,p4;

        // DesenhaPonto ponto;

        int circulo = -1;
        String txt = "Mueve Algun circulo";
        Drawable imagen;
        Ponto ponto;
        Vector <Ponto> pontos=new Vector<Ponto>();
    Context c;
        public MoverView(Context context) {
            super(context);
            this.c=context;
        }

        void criarPonto(int tipo){


            if(tipo==PDF){
                Paint p= new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.RED);
                paint.add(p);/////////
                ponto=new Ponto();
                ponto.setForma(p);
                ponto.setCaminhoLink(CaminhoLink);
                Log.i("CAMINHOOOO6666", String.valueOf(CaminhoLink));
            }
            else if(tipo==VIDEO){
                Paint p= new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.BLUE);
                paint.add(p);
                ponto=new Ponto();
                ponto.setForma(p);
                ponto.setCaminhoLink(CaminhoLink);
                invalidate ();
            }
            else if(tipo==LINK){
                Paint p= new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.YELLOW);
                paint.add(p);
                ponto=new Ponto();
                ponto.setForma(p);
                ponto.setCaminhoLink(CaminhoLink);
                invalidate ();
            }
            else if(tipo==TEXTO){
                Paint p= new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.GRAY);
                paint.add(p);
                ponto=new Ponto();
                ponto.setForma(p);
                ponto.setCaminhoLink(CaminhoLink);
                invalidate ();
            }
            else if(tipo==IMAGEM){
                Paint p= new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.BLACK);
                paint.add(p);
                ponto=new Ponto();
                ponto.setForma(p);
                ponto.setCaminhoLink(CaminhoLink);
                invalidate ();
            }
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.argb(0, 0, 0, 0));
            canvas.drawColor(Color.TRANSPARENT);
            for (int i = 0; i < paint.size(); i++) {
                canvas.drawCircle(x[i], y[i], radio[i], (Paint) paint.get(i));
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
                    cenx = getx - x[i];
                    ceny = gety - y[i];
                    distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    // Log.i("distancia", String.valueOf(distancia));
                    if (distancia <= 50) {
                        antesX=x[i];
                        antesY=y[i];
                        circulo = i;
                        // txt = "El circulo tocado es" + i;
                        invalidate();
                    }
                }
            }
            if(acction==MotionEvent.ACTION_MOVE) {
                Log.i("distancia2", String.valueOf(distancia));
                Log.i("********************", "********");
                //Log.i("distancia1", String.valueOf(circulo));
                if (circulo != -1) {
                    cenx = getx - x[circulo];
                    ceny = gety - y[circulo];
                    distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    if (distancia < 60 && distancia > 12) {
                        if (circulo > -1) {
                            //posição depois do  movimento
                            x[circulo] = getx;
                            y[circulo] = gety;
                            invalidate();
                        }
                        // circulo=-1;
                    } else if ((Math.abs(getx - antesX) < 7 && Math.abs(gety - antesY) < 7)) {

                        Log.i("CAMINHO", String.valueOf(ponto.getCaminhoLink()));
                        Log.i("distanciaYantesY", String.valueOf(antesY));
                        Log.i("distanciagetx", String.valueOf(getx));
                        Log.i("distanciagety", String.valueOf(gety));//
                        //chama

                        Intent it = new Intent(c, PDFACT.class);
                        //l

                        Bundle bundle = new Bundle();
                        //String ident="Inicio";
                        bundle.putString("CaminhoLink", CaminhoLink);
                        it.putExtras(bundle);
                        startActivity(it);

                        apertei = 1;


                        Log.i("distancia_aperteiM", String.valueOf(apertei));


                    } else {// calcular a distancia do toque atual com a distancia do circulo atual
                        Log.i("dist", String.valueOf(circulo));
                    }
                }
            }
            // Log.i("distancia2","**********************************************************************", String.valueOf(PDF));
            return true;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
}