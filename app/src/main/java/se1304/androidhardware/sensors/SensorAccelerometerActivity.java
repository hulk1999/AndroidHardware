package se1304.androidhardware.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import se1304.androidhardware.R;

public class SensorAccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private float[] lastState;
    TextView[] txtAccelerationArr;
    TextView[] txtArr;
    private boolean isInitialized;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private final float NOISE = 2.0f;  // maximum ignored change on acceleration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_accelerometer);

        isInitialized = false;
        txtAccelerationArr = new TextView[3];
        txtAccelerationArr[0] = findViewById(R.id.txtXAxisAcceleration);
        txtAccelerationArr[1] = findViewById(R.id.txtYAxisAcceleration);
        txtAccelerationArr[2] =  findViewById(R.id.txtZAxisAcceleration);
        txtArr = new TextView[3];
        txtArr[0] = findViewById(R.id.txtXAxis);
        txtArr[1] = findViewById(R.id.txtYAxis);
        txtArr[2] = findViewById(R.id.txtZAxis);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get accelerometer & register this activity as listener
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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

        // get current acceleration of axes
        float[] currentState = {event.values[0], event.values[1], event.values[2]};

        if (!isInitialized){  // first call
            lastState = currentState;
            isInitialized = true;
        } else {

            // get amount of acceleration change on each axis
            float[] stateChanged = new float[3];
            for (int i = 0; i <= 2; i++){
                stateChanged[i] = lastState[i]-currentState[i] < NOISE ? 0.0f : lastState[i]-currentState[i];
            }
            lastState = currentState;

            // change text and width of textviews according to how much acceleration changed
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
