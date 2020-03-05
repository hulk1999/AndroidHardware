package se1304.androidhardware;

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

public class CameraClassCapturingActivity extends AppCompatActivity {

    private Camera camera;
    String outputPath;

    private Camera getCamera(){
        Camera camera = null;
        try {
            camera = Camera.open();
            camera.setDisplayOrientation(90);
            Camera.Parameters parameters = camera.getParameters();
            parameters.set("orientation", "portrait");
            camera.setParameters(parameters);
        } catch (Exception e){
            e.printStackTrace();
        }
        return camera;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_camera_class_capturing);

        camera = getCamera();
        CameraPreviewer cameraPreviewer = new CameraPreviewer(this, camera);
        FrameLayout framePreview = findViewById(R.id.framePreview);
        framePreview.addView(cameraPreviewer);
    }

    private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            camera.release();

            try {
                FileOutputStream fos = new FileOutputStream(outputPath);
                Bitmap bmpTaken = BitmapFactory.decodeByteArray(data, 0, data.length);

                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                bmpTaken = Bitmap.createBitmap(bmpTaken, 0, 0, bmpTaken.getWidth(), bmpTaken.getHeight(), matrix, true);

                bmpTaken.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                fos.flush();
                fos.close();
            } catch (Exception e){
                e.printStackTrace();
            }

            Intent intent = CameraClassCapturingActivity.this.getIntent();
            CameraClassCapturingActivity.this.setResult(RESULT_OK, intent);
            finish();
        }
    };

    public void clickToCapture(View view){
        Intent intent = this.getIntent();
        outputPath = intent.getStringExtra("outputPath");
        camera.takePicture(null, null, pictureCallback);
    }

}
