package se1304.androidhardware;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class CameraIntentActivity extends AppCompatActivity {

    private static final int CAPTURE_REQUEST = 123;
    private ImageView imgTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_intent);

        imgTaken = findViewById(R.id.imgTaken);
    }

    public void clickToCapture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CAPTURE_REQUEST) && (resultCode == RESULT_OK)){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTaken.setImageBitmap(bitmap);
        }
    }
}
