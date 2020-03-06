package se1304.androidhardware;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        // list sensors using SensorManager
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // build list of sensors as string
        StringBuilder sbrSensorList  = new StringBuilder();
        int i = 0;
        for (Sensor s : sensorList){
            sbrSensorList.append(++i + ". Name: " + s.getName() + "\n");
            sbrSensorList.append("      Vendor: " + s.getVendor() + "\n");
            sbrSensorList.append("      Version: " + s.getVersion() + "\n");
        }

        // set sensor list to textview
        TextView txtSensorList = findViewById(R.id.txtSensorList);
        txtSensorList.setText(sbrSensorList);
    }
}
