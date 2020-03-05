package se1304.androidhardware;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SensorAccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private float mLastX, mLastY, mLastZ;
    private boolean bInit;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private final float NOISE = 2.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_accelerometer);

        bInit = false;
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView txtXAxis = findViewById(R.id.txtXAxis);
        TextView txtYAxis = findViewById(R.id.txtYAxis);
        TextView txtZAxis = findViewById(R.id.txtZAxis);

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if (!bInit){
            mLastX = x;
            mLastY = y;
            mLastZ = z;
            txtXAxis.setText("0.0");
            txtYAxis.setText("0.0");
            txtZAxis.setText("0.0");
            bInit = true;
        } else {
            float deltaX = mLastX - x;
            float deltaY = mLastY - y;
            float deltaZ = mLastZ - z;

            deltaX = (deltaX < NOISE) ? 0.0f : deltaX;
            deltaY = (deltaY < NOISE) ? 0.0f : deltaY;
            deltaZ = (deltaZ < NOISE) ? 0.0f : deltaZ;
            mLastX = x;
            mLastY = y;
            mLastZ = z;

            txtXAxis.setText(deltaX + "");
            txtYAxis.setText(deltaY + "");
            txtZAxis.setText(deltaZ + "");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
