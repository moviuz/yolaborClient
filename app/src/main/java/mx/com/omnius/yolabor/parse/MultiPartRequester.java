package mx.com.omnius.yolabor.parse;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;


import java.io.File;
import java.util.Map;

import mx.com.omnius.yolabor.utils.AppLog;
import mx.com.omnius.yolabor.utils.Constants;
import mx.com.omnius.yolabor.utils.Utilities;

/**
 * Created by UDIaz on 03/02/18.
 */

public class MultiPartRequester {
    private Map<String, String> map;
    private AsyncTaskCompleteListener mAsynclistener;
    private int serviceCode;
    private HttpClient httpclient;
    private Activity activity;
    private AsyncHttpRequest request;
    private static final String TAG = "MultiPartRequester";

    public MultiPartRequester(Activity activity, Map<String, String> map,
                              int serviceCode, AsyncTaskCompleteListener asyncTaskCompleteListener) {
        this.map = map;
        this.serviceCode = serviceCode;
        this.activity = activity;
        // is Internet Connection Available...
        if (Utilities.isNetworkAvailable(activity)) {
            mAsynclistener = (AsyncTaskCompleteListener) asyncTaskCompleteListener;
            request = (AsyncHttpRequest) new AsyncHttpRequest().execute(map
                    .get("url"));
        } else {
            Toast.makeText(activity, "Intrnet no disponible", Toast.LENGTH_SHORT).show();
        }

    }

    class AsyncHttpRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            map.remove("url");
            try {
                HttpPost httppost = new HttpPost(urls[0]);
                httpclient = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 600000);

                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                for (String key : map.keySet()) {
                    if (key.equalsIgnoreCase(Constants.Params.PICTURE) && !map.get(key).isEmpty()) {
                        File f = new File(map.get(key));
                        builder.addBinaryBody(key, f, ContentType.APPLICATION_ATOM_XML.MULTIPART_FORM_DATA, f.getName());
                    } else {
                        builder.addTextBody(key, map.get(key), ContentType.create("text/plain", MIME.UTF8_CHARSET));
                    }
                    AppLog.Log(TAG, key + "---->" + map.get(key));
                }
                httppost.setEntity(builder.build());
                ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
                if (manager.getMemoryClass() < 25) {
                    System.gc();
                }
                HttpResponse response = httpclient.execute(httppost);
                String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

                return responseBody;

            } catch (Exception e) {
                e.printStackTrace();
            } catch (OutOfMemoryError oume) {
                System.gc();
            } finally {
                if (httpclient != null)
                    httpclient.getConnectionManager().shutdown();

            }
            return null;

        }

        @Override
        protected void onPostExecute(String response) {
            if (mAsynclistener != null) {
                mAsynclistener.onTaskCompleted(response, serviceCode);
            }
        }
    }

    public void cancelTask() {
        request.cancel(true);
        AppLog.Log(TAG, "task is canelled");

    }
}
