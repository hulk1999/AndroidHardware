package se1304.androidhardware.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import java.io.FileOutputStream;

import se1304.androidhardware.R;

public class CameraClassCapturingActivity extends AppCompatActivity {

    private Camera camera;
    String outputPath;

    // get camera object
    private Camera getCamera(){
        Camera camera = null;
        try {
            camera = Camera.open();
            camera.setDisplayOrientation(90);
        } catch (Exception e){
            e.printStackTrace();
        }
        return camera;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide application name on top
        setContentView(R.layout.activity_camera_class_capturing);

        // get camera & set preview on layout
        camera = getCamera();
        CameraPreviewer cameraPreviewer = new CameraPreviewer(this, camera);
        FrameLayout framePreview = findViewById(R.id.framePreview);
        framePreview.addView(cameraPreviewer);
    }

    private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // release camera
            camera.release();

            try {
                // get image as byte array & convert to bitmap
                Bitmap bmpTaken = BitmapFactory.decodeByteArray(data, 0, data.length);

                // rotate image
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                bmpTaken = Bitmap.createBitmap(bmpTaken, 0, 0, bmpTaken.getWidth(), bmpTaken.getHeight(), matrix, true);

                // save image as jpg
                FileOutputStream fos = new FileOutputStream(outputPath);
                bmpTaken.compress(Bitmap.CompressFormat.JPEG, 50, fos);

                fos.flush();
                fos.close();
            } catch (Exception e){
                e.printStackTrace();
            }

            // close this activity after taking picture
            Intent intent = CameraClassCapturingActivity.this.getIntent();
            CameraClassCapturingActivity.this.setResult(RESULT_OK, intent);
            finish();
        }
    };

    public void clickToCapture(View view){
        // get path to save image
        Intent intent = this.getIntent();
        outputPath = intent.getStringExtra("outputPath");
        // call camera object to take picture
        camera.takePicture(null, null, pictureCallback);
    }

}
