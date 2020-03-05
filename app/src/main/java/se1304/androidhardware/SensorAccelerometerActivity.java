package se1304.androidhardware;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public class SensorAccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private float[] lastState;
    private boolean isInitialized;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private final float NOISE = 2.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_accelerometer);

        isInitialized = false;
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

    private int max(int num1, int num2){
        return num1 < num2 ? num2 : num1;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        TextView[] txtAccelerationArr = {findViewById(R.id.txtXAxisAcceleration), findViewById(R.id.txtYAxisAcceleration), findViewById(R.id.txtZAxisAcceleration)};
        TextView[] txtArr = {findViewById(R.id.txtXAxis), findViewById(R.id.txtYAxis), findViewById(R.id.txtZAxis)};
        float[] currentState = {event.values[0], event.values[1], event.values[2]};

        if (!isInitialized){
            lastState = currentState;
            isInitialized = true;
        } else {

            float[] stateChanged = new float[3];
            for (int i = 0; i <= 2; i++){
                stateChanged[i] = lastState[i]-currentState[i] < NOISE ? 0.0f : lastState[i]-currentState[i];
            }
            lastState = currentState;

            for (int i = 0; i <= 2; i++){
                txtAccelerationArr[i].setText("Acceleration: " + currentState[i] + " m/s^2");
                txtArr[i].setText(String.format ("%.1f", stateChanged[i]));
                LayoutParams layoutParams = txtArr[i].getLayoutParams();
                layoutParams.width = max((int) (stateChanged[i]/20*760), 60);
                txtArr[i].setLayoutParams(layoutParams);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
