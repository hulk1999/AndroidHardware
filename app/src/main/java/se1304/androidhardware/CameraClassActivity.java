package se1304.androidhardware;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CameraClassActivity extends AppCompatActivity {

    private final int CAPTURE_REQUEST = 123;
    private ImageView imvTaken;
    private String outputPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_class);

        imvTaken = findViewById(R.id.imvTaken);
    }

    public void clickToCapture(View view) {

        // provide path to save image
        outputPath = Environment.getExternalStorageDirectory() + "/PRM391/img.jpg";

        // start CameraClassCapturingActivity to capture image & save
        Intent intent = new Intent(this, CameraClassCapturingActivity.class);
        intent.putExtra("outputPath", outputPath);
        startActivityForResult(intent, CAPTURE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CAPTURE_REQUEST) && (resultCode == RESULT_OK)){
            // get saved image & load to layout
            Bitmap bitmap = BitmapFactory.decodeFile(outputPath);
            imvTaken.setImageBitmap(bitmap);
        }
    }
}
