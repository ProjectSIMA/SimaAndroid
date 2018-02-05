package it.sima.android;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements TaskCallback<String> {
    SurfaceView surfaceView;
    Camera camera;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        surfaceView = findViewById(R.id.surface_camera);
        camera = Camera.open();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    camera.setPreviewDisplay(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                camera.stopPreview();
            }
        });
    }

    public void postPhoto(View view){
        final CameraActivity activity = this;
        camera.takePicture(null,null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                PostBytesTask task = new PostBytesTask(activity);
                task.execute(bytes);
            }
        });


    }

    @Override
    public void onTaskCompleted(String result) {
        System.out.println(result);
    }
}
