package mx.com.omnius.yolabor.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by UDIaz on 03/02/18.
 */

public class Utilities {
    static float density = 1;
    private static ProgressDialog mProgressDialog;
    private static Dialog mDialog;

   // private LoadingView imageview;

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
//            if (mProgressDialog == null) {
//                mProgressDialog = ProgressDialog.show(context, title, msg);
//                mProgressDialog.setCancelable(isCancelable);
//            }
//
//            if (!mProgressDialog.isShowing()) {
//                mProgressDialog.show();
//            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSimpleProgressDialog(Context context) {
       // showSimpleProgressDialog(context, null, context.getString(R.string.progress_loading), false);
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void showCustomProgressDialog(Context context, String title,
                                                String details, boolean iscancelable) {
        removeCustomProgressDialog();
        mDialog = new Dialog(context, R.style.MyDialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setContentView(R.layout.dialog_progress);
        LoadingView imageView = (LoadingView) mDialog.findViewById(R.id.iv_dialog_progress);
        imageView.setExternalRadius(40);
        imageView.setInternalRadius(40);
        imageView.setDuration(100);
        imageView.start();
        ((TextView) mDialog.findViewById(R.id.tv_dialog_title)).setText(title);
        ((TextView) mDialog.findViewById(R.id.tv_dialog_detail)).setText(details);
        if (!((Activity) context).isFinishing())
            mDialog.show();
    }*/

    public static void removeCustomProgressDialog() {
        try {
            if (mDialog != null) {
                mDialog.dismiss();
                mDialog = null;
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



   /* public static void Picasso(Context context, String url, ImageView view, int errorRes) {
        Picasso.with(context).load(url).error(errorRes)
                .into(view);
    }

    public static void Picasso(Context context, String url, ImageView view) {
        Picasso.with(context).load(url)
                .into(view);
    }*/


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean eMailValidation(String emailstring) {
        if (null == emailstring || emailstring.length() == 0) {
            return false;
        }
        Pattern emailPattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher emailMatcher = emailPattern.matcher(emailstring);
        return emailMatcher.matches();
    }

    public static void showToast(String msg, int coordinatorLayout, Activity activity) {
        try {
            Snackbar.make(activity.findViewById(coordinatorLayout), "" + msg, Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            AppLog.Log("AndyUtils", "" + e.getMessage());
        }
    }
    /*

    public static void showErrorToast(int id, Context ctx, int coordinatorLayout, Activity activity) {
        String msg = "Error";
        switch (id) {
            case 401:
                msg = ctx.getResources().getString(R.string.error_401);
                break;
            case 402:
                msg = ctx.getResources().getString(R.string.error_402);
                break;
            case 403:
                msg = ctx.getResources().getString(R.string.error_403);
                break;
            case 404:
                msg = ctx.getResources().getString(R.string.error_404);
                break;
            case 405:
                msg = ctx.getResources().getString(R.string.error_405);
                break;
            case 406:
                msg = ctx.getResources().getString(R.string.error_406);
                break;
            case 407:
                msg = ctx.getResources().getString(R.string.error_407);
                break;
            case 408:
                msg = ctx.getResources().getString(R.string.error_408);
                break;
            case 409:
                msg = ctx.getResources().getString(R.string.error_409);
                break;
            case 410:
                msg = ctx.getResources().getString(R.string.error_410);
                break;
            case 411:
                msg = ctx.getResources().getString(R.string.error_411);
                break;
            case 412:
                msg = ctx.getResources().getString(R.string.error_412);
                break;
            case 413:
                msg = ctx.getResources().getString(R.string.error_413);
                break;
            case 414:
                msg = ctx.getResources().getString(R.string.error_414);
                break;
            case 415:
                msg = ctx.getResources().getString(R.string.error_415);
                break;
            case 416:
                msg = ctx.getResources().getString(R.string.error_416);
                break;
            case 417:
                msg = ctx.getResources().getString(R.string.error_417);
                break;

            default:
                break;
        }
        showToast(msg, coordinatorLayout, activity);
    }


    public static double distance(double lat1, double lon1, double lat2,
                                  double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    private static Bitmap getBitmap(VectorDrawableCompat vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(context.getResources(), drawableId);
        } else if (drawable instanceof VectorDrawableCompat) {
            return getBitmap((VectorDrawableCompat) drawable);
        } else if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }


}
