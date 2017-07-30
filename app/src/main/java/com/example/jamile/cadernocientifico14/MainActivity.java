package com.example.jamile.cadernocientifico14;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.io.File;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

//
//

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final  int PDF=1;
    static final  int VIDEO=2;
    static final  int LINK=3;
    static final  int IMAGEM=4;
    static final  int TEXTO=5;
    View l2;
    ImageView img;
    Bitmap thumbnail;
    MoverView mover;
    private ImageView imagem;
    private final int GALERIA_IMAGENS=1;
    private final  int PERMISSAO_REQUEST =2;
    View view;
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
        image.setImageBitmap(thumbnail);
//       final ImageView zoon=(ImageView)findViewById(R.id.imageView);


        mover = new MoverView(this);
        mover.criarPonto(PDF);
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

        imagem = (ImageView) findViewById(R.id.img22);
               imagem.setImageBitmap(thumbnail);
               view.setBackground(imagem.getDrawable());
       View view2=getLayoutInflater().inflate(R.layout.content_main,null); // get reference to root activity view
        setContentView(view2);

        view2.setOnClickListener(new View.OnClickListener() {
            float zoomFactor = 2f;
            boolean zoomedOut = false;

            @Override
            public void onClick(View v) {
                if(zoomedOut) {
                    v.setScaleX(1);
                    v.setScaleY(1);
                    zoomedOut = false;
                }
                else {
                    v.setScaleX(zoomFactor);
                    v.setScaleY(zoomFactor);
                    zoomedOut = true;
                }
            }
        });
       // mAttacher= new PhotoViewAttacher((ImageView) view);
        //SGD=new ScaleGestureDetector(this, new ScaleListener());

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
            final ListView lv= (ListView) findViewById(R.id.lv);
            lv.setAdapter(new CustomAdapter(MainActivity.this));//mover.criarPonto(PDF);
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



    //
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
                    openPDFView(pdfDoc.getPath());
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
}
