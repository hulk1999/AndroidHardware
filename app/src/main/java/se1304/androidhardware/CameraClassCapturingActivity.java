package se1304.androidhardware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class CameraClassCapturingActivity extends AppCompatActivity {

    private Camera camera;

    private Camera getCamera(){
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e){
            e.printStackTrace();
        }
        return camera;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            Intent intent = CameraClassCapturingActivity.this.getIntent();
            intent.putExtra("byteArrImg", data);
            CameraClassCapturingActivity.this.setResult(RESULT_OK, intent);
            finish();
        }
    };

    public void clickToCapture(View view){
        camera.takePicture(null, null, pictureCallback);
    }

}
