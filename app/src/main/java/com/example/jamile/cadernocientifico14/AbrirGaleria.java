package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class AbrirGaleria extends AppCompatActivity {
    private ImageView imagem;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;
    Bitmap thumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_galeria);

//Parte de permissão
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }
//////////////////////////////////////

        //Abrindo a galeria
        //imagem = (ImageView) findViewById(R.id.ivImagem);
        imagem = (ImageView) findViewById(R.id.img);
       // Button galeria = (Button) findViewById(R.id.btimg);

                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS) {
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            thumbnail = (BitmapFactory.decodeFile(picturePath));
            // imagem.setImageBitmap(thumbnail);
            Intent intent2=new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            //String ident="Inicio";
            bundle.putString("DEUS", picturePath);
            // bundle.putString("Inicio", ident);
            intent2.putExtras(bundle);
            startActivity(intent2);
            finish();
            //imagem.setImageBitmap(thumbnail);
//
//            Intent intent2=new Intent(this, ReceberFoto.class);
//            //int imageId = R.drawable.sf; // Aqui tens de mudar dinamicamente (de acordo com o item que clicas) para a imagem que pretendes passar para os detalhes
//            //Bitmap jovens =((BitmapDrawable)imagem.getDrawable()).getBitmap();
//            intent2.putExtra("IMAGEMJ", picturePath );
//
//            startActivity(intent2);
        }
    }
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
}
