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
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Vector;

import uk.co.senab.photoview.PhotoViewAttacher;

import static com.example.jamile.cadernocientifico14.MainActivity.MoverView.AUDIO;
import static com.example.jamile.cadernocientifico14.MainActivity.MoverView.CAMERA;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int PDF = 1;
    static final int VIDEO = 2;
    static final int LINK = 3;
    static final int IMAGEM = 4;
    static final int TEXTO = 5;
    static String CaminhoLink;
    static Vector<Ponto> pontos = new Vector<Ponto>();
    View l2;
    ImageView img;
    Bitmap thumbnail;
    static MoverView mover;
    private ImageView imagem;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;
    View view;
    static ListView lv;
    Matrix matrix = new Matrix();
    Float scale = 1f;
    ScaleGestureDetector SGD;
    PhotoViewAttacher mAttacher;
    static final int ACTIVITY_LISTARPDF = 1;
    static final int ACTIVITY_AUDIO = 2;
    static final int ACTIVITY_ADICIONARLINK = 3;
    static final int ACTIVITY_ADICIONARIMAGEM = 4;
    static final int ACTIVITY_ADICIONARTEXTO = 5;
    static final int ACTIVITY_IMPORTARAUDIO=6;
    static final int ACTIVITY_CAMERA = 7;
    static String picturePath;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fragment f = (Fragment)findViewById(R.id.fragmentB);
        //EmBrancoFragment.view.setVisibility(GONE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();
        picturePath = bundle.getString("DEUS");

        thumbnail = (BitmapFactory.decodeFile(picturePath));
        ImageView image = (ImageView) findViewById(R.id.img22);
        //Intent intent2 = getIntent();
//        Bundle bundle= intent2.getExtras();
//        String picturePath = bundle.getString("DEUS");
        image.setImageBitmap(thumbnail);
//       final ImageView zoon=(ImageView)findViewById(R.id.imageView);

        mover = new MoverView(this);
        // mover.criarPonto(PDF);
        Log.i("CAMINHOOOO1", String.valueOf(CaminhoLink));
        view = findViewById(R.id.view_root2);

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = scale * detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5f));
            matrix.setScale(scale, scale);
            imagem.setImageMatrix(matrix);
            return true;
        }
    }

    //    @Override
//    public  boolean onTouchEvent(MotionEvent event){
//        SGD.onTouchEvent(event);
//        return  true;
//    }
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

        if (id == R.id.itemPDF) {
            Intent i = new Intent(this, ListarPDF.class);
            startActivityForResult(i, ACTIVITY_LISTARPDF);

        } else if (id == R.id.itemAUDIO) {
            //EmBrancoFragment.view.setVisibility(View.VISIBLE);
           // CaminhoLink=EmBrancoFragment.outputFile;
            //Log.i("EmBrancoFragment", String.valueOf(CaminhoLink));
            //view.findFocus();

            Intent i = new Intent(this, GravarAudio.class);
            startActivityForResult(i, ACTIVITY_AUDIO);

            //mover.criarPonto(AUDIO);

        } else if (id == R.id.itemImportarAUDIO) {
            //EmBrancoFragment.view.setVisibility(View.VISIBLE);
            // CaminhoLink=EmBrancoFragment.outputFile;
            //Log.i("EmBrancoFragment", String.valueOf(CaminhoLink));
            //view.findFocus();

            Intent i = new Intent(this, ImportarAudio.class);
            startActivityForResult(i, ACTIVITY_IMPORTARAUDIO);

            //mover.criarPonto(AUDIO);
        }
        else if (id == R.id.itemTEXTO) {
            Intent i = new Intent(this, EscreverTexto.class);
            startActivityForResult(i, ACTIVITY_ADICIONARTEXTO);
            //mover.criarPonto(TEXTO);

        } else if (id == R.id.itemLINK) {
            Intent i = new Intent(this, AdicionarLink.class);
            startActivityForResult(i, ACTIVITY_ADICIONARLINK);

        } else if (id == R.id.itemIMAGEM) {
            Intent i = new Intent(this, AdicionarImagem.class);
            startActivityForResult(i, ACTIVITY_ADICIONARIMAGEM);
        }

        else if (id == R.id.itemCAMERA) {
            Intent i = new Intent(this, TirarFoto.class);
            startActivityForResult(i, ACTIVITY_CAMERA);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ACTIVITY_LISTARPDF) {
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("resultado");
                CaminhoLink=resultado;
//                Toast.makeText(this,
//                        "PDF2!!!", Toast.LENGTH_LONG).show();
                mover.criarPonto(PDF);

                //Coloque no EditText
                //EditText seuEditText= (EditText) findViewById(R.id.seuEditText);
                //seuEditText.setText(resultado);
            }
        }
        else if(requestCode == ACTIVITY_ADICIONARLINK){
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("resultado");
                CaminhoLink=resultado;
//                Toast.makeText(this,
//                        "LINK!!!", Toast.LENGTH_LONG).show();
                mover.criarPonto(LINK);

                //Coloque no EditText
                //EditText seuEditText= (EditText) findViewById(R.id.seuEditText);
                //seuEditText.setText(resultado);
            }
        }

        else if(requestCode == ACTIVITY_ADICIONARIMAGEM){
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("resultado");
                CaminhoLink=resultado;
                Log.i("TESTE 333333333::::::::::", resultado);
//                Toast.makeText(this,
//                        "IMAGEM!!!", Toast.LENGTH_LONG).show();
                mover.criarPonto(IMAGEM);
                //Coloque no EditText
                //EditText seuEditText= (EditText) findViewById(R.id.seuEditText);
                //seuEditText.setText(resultado);
            }
        }
        else if(requestCode == ACTIVITY_CAMERA){
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("resultado");
                CaminhoLink=resultado;
                Log.i("TESTE 333333333::::::::::", resultado);
//                Toast.makeText(this,
//                        "IMAGEM!!!", Toast.LENGTH_LONG).show();
                mover.criarPonto(CAMERA);
                //Coloque no EditText
                //EditText seuEditText= (EditText) findViewById(R.id.seuEditText);
                //seuEditText.setText(resultado);
            }
        }

        else if(requestCode == ACTIVITY_AUDIO){
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("resultado");
                CaminhoLink=resultado;
                Log.i("TESTE 333333333::::::::::", resultado);
//                Toast.makeText(this,
//                        "AUDIO!!!", Toast.LENGTH_LONG).show();
                mover.criarPonto(AUDIO);
                //Coloque no EditText
                //EditText seuEditText= (EditText) findViewById(R.id.seuEditText);
                //seuEditText.setText(resultado);
            }
        }
        else if(requestCode == ACTIVITY_IMPORTARAUDIO){
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("resultado");
                CaminhoLink=resultado;
                Log.i("TESTE 333333333::::::::::", resultado);
//                Toast.makeText(this,
//                        "IMPORTAR ÁUDIO!!!", Toast.LENGTH_LONG).show();
                mover.criarPonto(AUDIO);
                //Coloque no EditText
                //EditText seuEditText= (EditText) findViewById(R.id.seuEditText);
                //seuEditText.setText(resultado);
            }
        }
        else if(requestCode == ACTIVITY_ADICIONARTEXTO){
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("resultado");
                CaminhoLink=resultado;
                Log.i("TESTE 333333333::::::::::", resultado);
//                Toast.makeText(this,
//                        "AUDIO!!!", Toast.LENGTH_LONG).show();
                mover.criarPonto(TEXTO);
                //Coloque no EditText
                //EditText seuEditText= (EditText) findViewById(R.id.seuEditText);
                //seuEditText.setText(resultado);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////// CustomAdapter ////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////// MoverView ////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class MoverView extends View {
        float distancia = 0;
        double cenx = 0;
        double ceny = 0;
        static final int PDF = 1;
        static final int VIDEO = 2;
        static final int LINK = 3;
        static final int IMAGEM = 4;
        static final int TEXTO = 5;
        static final int AUDIO = 7;
        static final int  IMPORTARAUDIO=8;
        static final int CAMERA=9;
        float antesX, antesY;
        int apertei = 0;


        View l2;
        ImageView img;
        Bitmap thumbnail;
        //MainActivity.MoverView mover;
        private ImageView imagem;
        private final int GALERIA_IMAGENS = 1;
        private final int PERMISSAO_REQUEST = 2;
        View view;

        Vector<Float> x = new Vector<Float>();
        Vector<Float> y = new Vector<Float>();
        //float[] radio = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};
        final float RADIO = 40;
        Vector paint = new Vector();
        //Paint paint[] = new Paint[2];
        Paint p, p2, p3, p4;
//        Bitmap bipmap = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.audio2);
//        Bitmap bipmap2 = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.pdf);

        // DesenhaPonto ponto;

        int circulo = -1;
        String txt = "Mueve Algun circulo";
        Drawable imagen;
        Ponto ponto;
        Vector<Ponto> pontos = new Vector<Ponto>();
        Context c;

        public MoverView(Context context) {
            super(context);
            this.c = context;
        }

        void criarPonto(int tipo) {

            if (tipo == PDF) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.RED);
                paint.add(p);/////////
                ponto = new Ponto();
                ponto.setPaint(p);
                ponto.setTipo(PDF);
//                bipmap=getCircleBitmap(bipmap);
//                bipmap2=getCircleBitmap(bipmap2);
                ponto.setCaminhoLink(CaminhoLink);
                Log.i("CAMINHOOOO6666", String.valueOf(CaminhoLink));
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            } else if (tipo == VIDEO) {Log.i("EmBrancoFragment2", String.valueOf(CaminhoLink));
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.BLUE);
                paint.add(p);
                ponto = new Ponto();
                ponto.setTipo(VIDEO);
                ponto.setPaint(p);
                ponto.setCaminhoLink(CaminhoLink);
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            } else if (tipo == LINK) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.YELLOW);
                paint.add(p);
                ponto = new Ponto();
                ponto.setTipo(LINK);
                ponto.setPaint(p);
                ponto.setCaminhoLink(CaminhoLink);
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            } else if (tipo == TEXTO) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.GRAY);
                paint.add(p);
                ponto = new Ponto();
                ponto.setPaint(p);
                ponto.setTipo(TEXTO);
                ponto.setCaminhoLink(CaminhoLink);
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            } else if (tipo == IMAGEM) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.BLACK);
                paint.add(p);
                ponto = new Ponto();
                ponto.setPaint(p);
                ponto.setTipo(IMAGEM);
                ponto.setCaminhoLink(CaminhoLink);
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            }
            else if (tipo == IMAGEM) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.BLACK);
                paint.add(p);
                ponto = new Ponto();
                ponto.setPaint(p);
                ponto.setTipo(IMAGEM);
                ponto.setCaminhoLink(CaminhoLink);
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            }
            else if (tipo == CAMERA) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.BLACK);
                paint.add(p);
                ponto = new Ponto();
                ponto.setPaint(p);
                ponto.setTipo(CAMERA);
                ponto.setCaminhoLink(CaminhoLink);
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            }
            else if (tipo == AUDIO) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.GREEN);
                paint.add(p);
                ponto = new Ponto();
                ponto.setPaint(p);
                ponto.setTipo(AUDIO);
                ponto.setCaminhoLink(CaminhoLink);
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            }
            else if (tipo == IMPORTARAUDIO) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(Color.GREEN);
                paint.add(p);
                ponto = new Ponto();
                ponto.setPaint(p);
                ponto.setTipo(IMPORTARAUDIO);
                ponto.setCaminhoLink(CaminhoLink);
                pontos.add(ponto);
                x.add((float) 100);
                y.add((float) 150);
                invalidate();
            }
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.argb(0, 0, 0, 0));
            canvas.drawColor(Color.TRANSPARENT);
            for (int i = 0; i < pontos.size(); i++) {
                //if(pon){}
                //canvas.drawBitmap(bipmap,10,10,null);
                //canvas.drawBitmap(bipmap2,100,100, null);
                canvas.drawCircle(x.get(i), y.get(i), RADIO, pontos.get(i).getPaint());
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
                        antesX = x.get(i);
                        antesY = y.get(i);
                        circulo = i;
                        // txt = "El circulo tocado es" + i;
                        invalidate();
                    }
                }
            }
            if (acction == MotionEvent.ACTION_MOVE) {
                Log.i("distancia2", String.valueOf(distancia));
                Log.i("********************", "********");
                //Log.i("distancia1", String.valueOf(circulo));
                if (circulo != -1) {
                    cenx = getx - x.get(circulo);
                    ceny = gety - y.get(circulo);
                    distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    if (distancia < 60 && distancia > 12) {
                        if (circulo > -1) {
                            //posição depois do  movimento
                            x.set(circulo, getx);
                            y.set(circulo, gety);
                            invalidate();
                        }
                        // circulo=-1;
                    } else if ((Math.abs(getx - antesX) < 7 && Math.abs(gety - antesY) < 7)) {

                        Log.i("CAMINHO", String.valueOf(ponto.getCaminhoLink()));
                        Log.i("distanciaYantesY", String.valueOf(antesY));
                        Log.i("distanciagetx", String.valueOf(getx));
                        Log.i("distanciagety", String.valueOf(gety));//
                        //chama


                        // fazer o for pra selecionar a menor distancia
                        for (int i = 0; i < pontos.size(); i++) {
                            //canvas.drawCircle(x.get(i), y.get(i), RADIO,  pontos.get(i).getPaint());
                            if ((Math.abs(x.get(i) - antesX) < 7 && Math.abs(y.get(i) - antesY) < 7)) {
                                //Log.i("LINK6668", pontos.get(i).getCaminhoLink());
                                if (pontos.get(i).getTipo() == PDF) {
                                    Intent it = new Intent(c, PDFACT.class);
                                    //l
                                    //Log.i("LINK6669", pontos.get(//i).getCaminhoLink());

                                    Bundle bundle = new Bundle();
                                    //String ident="Inicio";
                                    bundle.putString("CaminhoLink", pontos.get(i).getCaminhoLink());
                                    it.putExtras(bundle);
                                    startActivity(it);
                                } else if (pontos.get(i).getTipo() == LINK) {
                                    Log.i("LINK6667", pontos.get(i).getCaminhoLink());
                                    Intent it = new Intent(c, AbrirLink.class);
                                    //l

                                    Bundle bundle = new Bundle();
                                    //String ident="Inicio";
                                    bundle.putString("CaminhoLink", pontos.get(i).getCaminhoLink());
                                    it.putExtras(bundle);
                                    startActivity(it);
                                }
                                else if (pontos.get(i).getTipo() == IMAGEM) {
                                    Log.i("IMAGEM::::::::::", pontos.get(i).getCaminhoLink());
                                    Intent it = new Intent(c, AbrirImagem.class);

                                    Bundle bundle = new Bundle();

                                    bundle.putString("caminhoImagem", pontos.get(i).getCaminhoLink());
                                    it.putExtras(bundle);
                                    startActivity(it);
                                }
                                else if (pontos.get(i).getTipo() == CAMERA) {
                                    Log.i("IMAGEM::::::::::", pontos.get(i).getCaminhoLink());
                                    Intent it = new Intent(c, AbrirImagem.class);

                                    Bundle bundle = new Bundle();

                                    bundle.putString("caminhoImagem", pontos.get(i).getCaminhoLink());
                                    it.putExtras(bundle);
                                    startActivity(it);
                                }
                                else if (pontos.get(i).getTipo() == AUDIO) {
                                    Log.i("IMAGEM::::::::::", pontos.get(i).getCaminhoLink());
                                    Intent it = new Intent(c, PlayAudio.class);

                                    Bundle bundle = new Bundle();

                                    bundle.putString("caminhoImagem", pontos.get(i).getCaminhoLink());
                                    it.putExtras(bundle);
                                    startActivity(it);
                                }
                                else if (pontos.get(i).getTipo() == IMPORTARAUDIO) {
                                    Log.i("IMAGEM::::::::::", pontos.get(i).getCaminhoLink());
                                    Intent it = new Intent(c, PlayAudio.class);

                                    Bundle bundle = new Bundle();

                                    bundle.putString("caminhoImagem", pontos.get(i).getCaminhoLink());
                                    it.putExtras(bundle);
                                    startActivity(it);
                                }
                                else if (pontos.get(i).getTipo() == TEXTO) {
                                    Log.i("IMAGEM::::::::::", pontos.get(i).getCaminhoLink());
                                    Intent it = new Intent(c, ExibirTexto.class);

                                    Bundle bundle = new Bundle();

                                    bundle.putString("texto", pontos.get(i).getCaminhoLink());
                                    it.putExtras(bundle);
                                    startActivity(it);
                                }
                            }
                        }

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

    public void chamarCriarPonto(int tipo) {
        mover.criarPonto(tipo);

    }


    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }
}