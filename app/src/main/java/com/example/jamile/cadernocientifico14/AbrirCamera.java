package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class AbrirCamera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_camera);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(data!=null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
            //if (resultCode == RESULT_OK && requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
//                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
//                // imagem.setImageBitmap(thumbnail);
                Intent intent2=new Intent(this, MainActivity.class);
                Bundle bundle2 = new Bundle();
                //String ident="Inicio";
                bundle2.putString("DEUS", picturePath);
                // bundle.putString("Inicio", ident);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                finish();
            }
        //}
        }


        if(data != null){
            Bundle bundle = data.getExtras();
           if(bundle != null){
                Bitmap img = (Bitmap) bundle.get("data");

                ImageView iv = (ImageView) findViewById(R.id.imageView1);
                iv.setImageBitmap(img);
            }
        }

    }
}
