package it.sima.android;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
            public void onPictureTaken(final byte[] bytes, final Camera camera) {
                RequestQueue queue = Volley.newRequestQueue(activity);
                StringRequest request = new StringRequest(StringRequest.Method.POST, "http://10.0.0.6:8080/face/detect", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(activity,response,Toast.LENGTH_SHORT).show();
                        camera.startPreview();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity,"ERRORE! " + error.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.w("sima",error.networkResponse.toString());
                    }
                }){
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        return bytes;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "image/jpeg";
                    }
                };
                queue.add(request);
                queue.start();
            }
        });


    }

    @Override
    public void onTaskCompleted(String result) {
        System.out.println(result);
    }
}
