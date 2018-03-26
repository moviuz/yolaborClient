package mx.com.omnius.yolabor.parse;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.util.Map;

import mx.com.omnius.yolabor.YolaborApplication;
import mx.com.omnius.yolabor.utils.AppLog;
import mx.com.omnius.yolabor.utils.Constants;

/**
 * Created by UDIaz on 03/02/18.
 */

public class VolleyMultiPartRequest extends Request<String> {
    private AsyncTaskCompleteListener listener;
    private Map<String, String> params;
    private int serviceCode;
    private static String TAG = "VolleyHttpRequest";



    HttpEntity httpentity;
    public VolleyMultiPartRequest(int method, Map<String, String> params,
                                  int serviceCode, AsyncTaskCompleteListener reponseListener,
                                  Response.ErrorListener errorListener) {
        super(method, params.get(Constants.URL), errorListener);
        YolaborApplication.requestQueue.getCache().clear();
        if (AppLog.isDebug) {
            for (String key : params.keySet()) {
                AppLog.Log(TAG, key + "  < === >  " + params.get(key));
            }
        }

        params.remove(Constants.URL);
        setRetryPolicy(new DefaultRetryPolicy(600000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.listener = reponseListener;
        this.params = params;
        this.serviceCode = serviceCode;

    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(String response) {

    }


    void doMultiPartRequest() {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (String key : this.params.keySet()) {
            if (key.equalsIgnoreCase(Constants.Params.PICTURE) && !this.params.get(key).isEmpty()) {
                File f = new File(this.params.get(key));
                builder.addBinaryBody(key, f, ContentType.MULTIPART_FORM_DATA, f.getName());
            } else {
                builder.addTextBody(key, this.params.get(key), ContentType.create("text/plain", MIME.UTF8_CHARSET));
            }
        }
    }


    @Override
    public String getBodyContentType() {
        return httpentity.getContentType().getValue();
    }
}
