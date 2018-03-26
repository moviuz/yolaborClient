package mx.com.omnius.yolabor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by UDIaz on 03/02/18.
 */

public class PreferenceHelper {
    private SharedPreferences app_prefs;
    private final String USER_ID = "user_id";
    private final String DEVICE_TOKEN = "device_token";
    private final String SESSION_TOKEN = "session_token";
    private final String REQUEST_ID = "request_id";
    private final String INSTANT_REQUEST_ID = "instant_request_id";
    private final String WALKER_LATITUDE = "walkerlatitude";
    private final String WALKER_LONGITUDE = "walkerlongitude";
    private final String PASSWORD = "password";
    private final String EMAIL = "f";
    private final String LOGIN_BY = "login_by";
    private final String SOCIAL_ID = "social_id";
    private final String REQUEST_TIME = "request_time";
    private final String WAITING_REQUEST_TIME = "waiting_request_time";
    private final String TRIP_START = "trip_start";
    private final String DISTANCE = "distance";
    private final String UNIT = "unit";
    private Context context;
    private final String PAYMENT_TYPE = "paymentType";
    private final String DEST_LAT = "dest_lat";
    private final String DEST_LNG = "dest_lng";
    private final String IS_APPROVED = "is_approved";
    private final String SOUND_AVAILABILITY = "sound_availability";
    private final String LATITUDE = "latitude";
    private final String LONGITUDE = "longitude";
    private final String IS_NAVIGATE = "is_navigate";
    private final String DIST_LATITUDE = "dist_latitude";
    private final String DIST_LONGITUDE = "dist_longitude";
    // **********************************************************************************************************

    private final String CLIENT_ID = "client_id";
    private final String FIRSTNAME = "firstname";
    private final String LASTNAME = "lastname";
    private final String PHONE = "phone";
    private final String BIRTHDATE = "birthdare";
    private final String GENDER = "gender";
    private final String ITIN = "itin";
    private final String COMPANY = "company";







    // **********************************************************************************************************
    private final String LANG_SHORT = "lang_short";
    private final String LANG_LONG = "lan_long";
    private final String SHARE_TIME = "time";
    private final String PRE_MIN = "premin";
    private final String TRIP_PRE_MIN = "trippremin";
    private final String TOTAL_TRIP_PRE_MIN = "totaltrippremin";

    private final String TIMER_START = "startTime";


    private final String TIMER_PAUSE = "PauseTime";

    private final Boolean TIMER_bool = false;


    private final String LATITUDE_C = "latitude_c";
    private final String LONGITUDE_C ="longitude_c";

    private final String OWNER ="ownerid";

    private final String USER_N ="usern";

    private final String ID_ZONA = "id_zona";
    private final String ID_ALERT_WALKER = "id_alert_walker";

    private final String LAT_WALKER = "lat_walker";
    private final String LON_WALKER = "lon_walker";


    //private DatabaseAdapter databaseAdapter;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences(Constants.PREF_NAME,
                Context.MODE_PRIVATE);
        this.context = context;
        //databaseAdapter = new DatabaseAdapter(context);
    }





    public void putIdZona(String idZona) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(ID_ZONA, idZona);
        edit.commit();

    }

    public String getIdZona() {
        return app_prefs.getString(ID_ZONA, "");
    }


    public void putIdAlertWalker(String idAlert) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(ID_ALERT_WALKER, idAlert);
        edit.commit();

    }

    public String getIdAlertWalker() {
        return app_prefs.getString(ID_ALERT_WALKER, "");
    }


    public void putLatWalker(String latWalker) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LAT_WALKER, latWalker);
        edit.commit();

    }

    public String getLatWalker() {
        return app_prefs.getString(LAT_WALKER, "");
    }






    public void putLonWalker(String lonWalker) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LON_WALKER, lonWalker);
        edit.commit();

    }

    public String getLonWalker() {
        return app_prefs.getString(LON_WALKER, "");
    }

/////////////////////////////////////////////

    public void putPassword(String password) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PASSWORD, password);
        edit.commit();
    }

    public String getPassword() {
        return app_prefs.getString(PASSWORD, null);
    }





    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void putLatitudeC(String latitudec) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LATITUDE_C, latitudec);
        edit.commit();

    }

public String getLatitudeC(){ return  app_prefs.getString(LATITUDE_C, "");}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void putLongitudeC(String latitudec) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LONGITUDE_C, latitudec);
        edit.commit();

    }

    public String getLongitudeC(){ return app_prefs.getString(LONGITUDE_C, "") ;}
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void putUserN(String userN) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USER_N, userN);
        edit.commit();

    }

    public String getUserN() {
        return app_prefs.getString(USER_N, "");
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void putOwner(String owner) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(OWNER, owner);
        edit.commit();

    }

    public String getOwner() {
        return app_prefs.getString(OWNER, "");
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void putDestinationLatitude(String latitude) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(DIST_LATITUDE, latitude);
        edit.commit();

    }

    public String getDestinationLatitude() {
        return app_prefs.getString(DIST_LATITUDE, "");
    }

    public void putDestinationLongitude(String longitude) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(DIST_LONGITUDE, longitude);
        edit.commit();

    }

    public boolean isNavigate() {
        return app_prefs.getBoolean(IS_NAVIGATE, false);
    }

    public void putIsNavigate(boolean navigate) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(IS_NAVIGATE, navigate);
        edit.commit();
    }

    public double getLatitude() {
        return app_prefs.getFloat(LATITUDE, 0.0f);
    }

    public double getLongitude() {
        return app_prefs.getFloat(LONGITUDE, 0.0f);
    }

    public void putIsApproved(String approved) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(IS_APPROVED, approved);
        edit.commit();
    }

    public void putUserId(String userId) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USER_ID, userId);
        edit.commit();
    }
    public String getUserId() {
        return app_prefs.getString(USER_ID, "");
    }

    public String getDeviceToken() {
        return app_prefs.getString(DEVICE_TOKEN, null);
    }

    public void putSessionToken(String sessionToken) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(SESSION_TOKEN, sessionToken);
        edit.commit();
    }

    public void putRequestId(int reqId) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putInt(REQUEST_ID, reqId);
        edit.commit();
    }

    public void putInstantJobId(int reqId) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putInt(INSTANT_REQUEST_ID, reqId);
        edit.commit();
    }

    public void putDistance(Float distance) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putFloat(DISTANCE, distance);
        edit.commit();
    }

    public float getDistance() {
        return app_prefs.getFloat(DISTANCE, 0.0f);
    }


    public String getUnit() {
        return app_prefs.getString(UNIT, " ");
    }

    public void putIsTripStart(boolean status) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(TRIP_START, status);
        edit.commit();
    }

    public void putEmail(String email) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(EMAIL, email);
        edit.commit();
    }

    public String getEmail() {
        return app_prefs.getString(EMAIL, null);
    }



    public void putLoginBy(String loginBy) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LOGIN_BY, loginBy);
        edit.commit();
    }

    public String getLoginBy() {
        return app_prefs.getString(LOGIN_BY, Constants.MANUAL);
    }

    public void putSocialId(String socialId) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(SOCIAL_ID, socialId);
        edit.commit();
    }

    public void putRequestTime(long time) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putLong(REQUEST_TIME, time);
        edit.commit();
    }

    public void putPaymentType(int type) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putInt(PAYMENT_TYPE, type);
        edit.commit();
    }

    public void putClientDestination(LatLng destination) {
        SharedPreferences.Editor edit = app_prefs.edit();
        if (destination == null) {
            edit.putString(DEST_LAT, null);
            edit.putString(DEST_LNG, null);
        } else {
            edit.putString(DEST_LAT, String.valueOf(destination.latitude));
            edit.putString(DEST_LNG, String.valueOf(destination.longitude));
        }
        edit.commit();
    }

    public void clearRequestData() {
        putRequestId(Constants.NO_REQUEST);
        putRequestTime(Constants.NO_TIME);
        putInstantJobId(Constants.NO_TIME);
        putIsTripStart(false);
        putClientDestination(null);
        putPaymentType(-1);
        putDestinationLatitude("");
        putDestinationLongitude("");
        putTimerMin("0");
        putTotalTimerMin("0");
        putTripTimerMin("0");
        putIsNavigate(false);
        putDistance((float) 0.0);
        putDistanceTime("0");
        // new DBHelper(context).deleteAllLocations();
    }

    public void Logout() {
        clearRequestData();
        putUserId(null);
        putSessionToken(null);
        putLoginBy(Constants.MANUAL);
        putSocialId(null);
        putClientDestination(null);
        //databaseAdapter.deleteUser();
//        new DBHelper(context).deleteUser();

    }

    public void putlang_short(String editval) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LANG_SHORT, editval);
        edit.commit();
    }

    public String getlang_long() {
        return app_prefs.getString(LANG_LONG, null);
    }

    public void putlang_long(String editval) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LANG_LONG, editval);
        edit.commit();
    }

    public void putDistanceTime(String Time) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(SHARE_TIME, Time);
        edit.commit();
    }


    public void putTimerMin(String Time) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PRE_MIN, Time);
        edit.commit();
    }

    public void putTotalTimerMin(String Time) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(TOTAL_TRIP_PRE_MIN, Time);
        edit.commit();
    }

    public void putTripTimerMin(String Time) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(TRIP_PRE_MIN, Time);
        edit.commit();
    }

//from pandiyan

    public long getTimerPause() {
        return app_prefs.getLong(TIMER_PAUSE, 0);
    }

    public void putTimerPause(long sec) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putLong(TIMER_PAUSE, sec);
        edit.commit();
    }


    public long getStartTime() {
        return app_prefs.getLong(TIMER_START, 0);
    }

    public void putStartTime(long sec) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putLong(TIMER_START, sec);
        edit.commit();
    }

//********************************************************************************


    public void putClientId(String clientId){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(CLIENT_ID, clientId);
        edit.commit();
    }

    public String getClientid(){return app_prefs.getString(CLIENT_ID,null);}


    public void putFirstName(String firstName){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(FIRSTNAME, firstName);
        edit.commit();
    }

    public String getFirstName(){return app_prefs.getString(FIRSTNAME, null); }

    public void putLastName(String lastName){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LASTNAME, lastName);
        edit.commit();
    }

    public String getLastName(){return app_prefs.getString(LASTNAME,null   );}


    public void putPhone(String phone){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PHONE, phone);
        edit.commit();
    }
     public String getPhone(){return  app_prefs.getString(PHONE,null);}

    public void putBirthdate(String birthdate) {
         SharedPreferences.Editor edit = app_prefs.edit();
         edit.putString(BIRTHDATE, birthdate);
         edit.commit();
    }
    public String getBirthdate(){return app_prefs.getString(BIRTHDATE,null);}


    public void putGender(String gender) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(GENDER, gender);
        edit.commit();
    }
    public String getGender(){return app_prefs.getString(GENDER,null);}

    public void putItin(String itin) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(ITIN, itin);
        edit.commit();
    }
    public String getItin(){return app_prefs.getString(ITIN,null);}


    public void putCompany(String company) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(COMPANY, company);
        edit.commit();
    }
    public String getCompany(){return app_prefs.getString(COMPANY,null);}






}
