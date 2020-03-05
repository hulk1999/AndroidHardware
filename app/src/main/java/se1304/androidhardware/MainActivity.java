package se1304.androidhardware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToListSensors(View view) {
        Intent intent = new Intent(this, SensorListActivity.class);
        startActivity(intent);
    }

    public void clickToSensorBarometer(View view) {
        Intent intent = new Intent(this, SensorBarometerActivity.class);
        startActivity(intent);
    }

    public void clickToCompass(View view) {
        Intent intent = new Intent(this, SensorCompassActivity.class);
        startActivity(intent);
    }

    public void clickToAccelerometer(View view) {
        Intent intent = new Intent(this, SensorAccelerometerActivity.class);
        startActivity(intent);
    }

    public void clickToProximity(View view) {
        Intent intent = new Intent(this, SensorProximityActivity.class);
        startActivity(intent);
    }

    public void clickToCameraIntent(View view) {
        Intent intent = new Intent(this, CameraIntentActivity.class);
        startActivity(intent);
    }

    public void clickToCameraClass(View view) {
        Intent intent = new Intent(this, CameraClassActivity.class);
        startActivity(intent);
    }

    public void clickToCameraVideo(View view) {
        Intent intent = new Intent(this, CameraVideoActivity.class);
        startActivity(intent);
    }
}
