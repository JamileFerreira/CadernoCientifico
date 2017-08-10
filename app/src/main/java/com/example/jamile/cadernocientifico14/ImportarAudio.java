package com.example.jamile.cadernocientifico14;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

public class ImportarAudio extends Activity {
final int RESULT_LOAD_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar_audio);
        Intent GaleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(GaleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            Log.i("vvvvv::::::::::","hhhh");
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Log.i("vvvv::::::::::", picturePath);
            Intent returnIntent = new Intent();
           // Log.i("TESTE 22222::::::::::", outputFile);
            returnIntent.putExtra("resultado", picturePath);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
        else{ finish();}
    }
}
