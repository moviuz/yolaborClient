package mx.com.omnius.yolabor.parse;

/**
 * Created by UDIaz on 03/02/18.
 */

public interface AsyncTaskCompleteListener {
    void onTaskCompleted(String response, int serviceCode);
}
