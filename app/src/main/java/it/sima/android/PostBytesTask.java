package it.sima.android;

import android.os.AsyncTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by theelix on 05/02/18.
 */

public class PostBytesTask extends AsyncTask<byte[],Void,String> {
    TaskCallback<String> taskCallback;

    public PostBytesTask(TaskCallback<String> taskCallback) {
        this.taskCallback = taskCallback;
    }

    @Override
    protected String doInBackground(byte[]... pictures) {
        try {
            URL url = new URL("http://10.0.0.6:8080/face/detect");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            ByteArrayInputStream bias = new ByteArrayInputStream(pictures[0]);
            OutputStream outputStream = connection.getOutputStream();
            byte[] buffer = new byte[4096];
            while(bias.read(buffer) != -1){
                outputStream.write(buffer);
            }
            outputStream.flush();
            outputStream.close();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while(inputStream.read(buffer) != -1){
                baos.write(buffer);
            }
            baos.flush();
            baos.close();
            inputStream.close();
            String string = new String(baos.toByteArray(),"UTF-8");
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        taskCallback.onTaskCompleted(s);
    }
}
