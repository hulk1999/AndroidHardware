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

    public void clickToSensors(View view) {
    }

    public void clickToCameraIntent(View view) {
        Intent intent = new Intent(this, CameraIntentActivity.class);
        startActivity(intent);
    }

    public void clickToCameraClass(View view) {
        Intent intent = new Intent(this, CameraClassActivity.class);
        startActivity(intent);
    }
}
