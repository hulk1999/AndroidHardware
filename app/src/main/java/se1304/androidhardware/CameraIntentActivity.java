package se1304.androidhardware;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class CameraIntentActivity extends AppCompatActivity {

    private static final int CAPTURE_REQUEST = 123;
    private ImageView imgTaken;
    private Uri outputUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_intent);

        imgTaken = findViewById(R.id.imgTaken);
    }

    public void clickToCapture(View view) {
        File outputFile = new File(Environment.getExternalStorageDirectory() + "/PRM391/img.jpg");
        outputUri = Uri.fromFile(outputFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        startActivityForResult(intent, CAPTURE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CAPTURE_REQUEST) && (resultCode == RESULT_OK)){
            Bitmap bitmap = BitmapFactory.decodeFile(outputUri.getPath());
            imgTaken.setImageBitmap(bitmap);
        }
    }
}
