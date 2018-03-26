package mx.com.omnius.yolabor.utils;

/**
 * Created by UDIaz on 03/02/18.
 */

public class AppLog {

    public static final boolean isDebug = true;

    public static final void Log(String tag, String message) {
        if (isDebug) {
            android.util.Log.i(tag, message + "");
        }
    }

    public static final void handleException(String tag, Exception e) {
        if (isDebug) {
            if (e != null) {
                android.util.Log.d(tag, e.getMessage() + "");
                e.printStackTrace();
            }
        }
    }

}
