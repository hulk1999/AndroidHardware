package se1304.androidhardware;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

// provide a SurfaceView for Previewing camera before capturing
public class CameraPreviewer extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera;

    public CameraPreviewer(Context context, Camera camera){
        super(context);
        this.camera = camera;
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        try {
            this.camera.setPreviewDisplay(holder);
            this.camera.startPreview();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

    }
}
