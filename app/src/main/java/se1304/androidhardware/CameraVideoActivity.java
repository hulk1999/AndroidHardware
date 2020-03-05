package se1304.androidhardware;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.VideoView;

import java.io.File;

public class CameraVideoActivity extends AppCompatActivity{

    private final int RECORD_REQUEST = 987;
    private Uri outputUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_video);
    }

    public void clickToRecord(View view) {
        File outputFile = new File(Environment.getExternalStorageDirectory() + "/PRM391/vid.mp4");
        outputUri = Uri.fromFile(outputFile);

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        startActivityForResult(intent, RECORD_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == RECORD_REQUEST) && (resultCode == RESULT_OK)){
            VideoView vivTaken = findViewById(R.id.vivTaken);
            vivTaken.setVideoPath(outputUri.getPath());
            vivTaken.start();
        }
    }
}
