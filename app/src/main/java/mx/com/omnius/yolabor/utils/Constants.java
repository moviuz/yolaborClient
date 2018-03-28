package mx.com.omnius.yolabor.utils;

/**
 * Created by UDIaz on 03/02/18.
 */

public class Constants {
    public static final String PREF_NAME = "Yolabor";
    public static final String MANUAL = "manual";
    public static final String URL = "url";

    // no request
    public static final int NO_REQUEST = -1;
    public static final int NO_TIME = -1;


    public static final int MAP_ZOOM = 16;




    public class ServiceType {


        private static final String HOST_URL = "http://omnius.com.mx:7080/";
        public static final String LOGIN = HOST_URL + "yolabor/webresources/service/loginClient?";
        public static final String JOB_TYPE = HOST_URL + "yolabor/webresources/service/getAllJobType?";
        public static final String SINGIN = HOST_URL + "yolabor/webresources/service/setClient?";
        public static final String HISTORY = HOST_URL + "yolabor/webresources/service/getAllJobItemByClient?";
        public static final String ALLCOMPANY = HOST_URL + "yolabor/webresources/service/getAllCompany?";
        public static final String SUPPORT = HOST_URL + "yolabor/webresources/service/setSupport?";


    }

    public class ServiceCode {

        public static final int LOGIN = 1;
        public static final int JOB_TYPE = 2;
        public static final int NEW_CLIENT = 3;
        public static final int HISTORY_CLIENT= 4;
        public static final int ALLCOMPANY = 5;
        public static final int SUPPORT = 6;
    }

    // webservice key constants
    public class Params {

        public static final String PICTURE = "picture";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String FIRSTNAME = "firstname";
        public static final String LASTNAME = "lastname";
        public static final String BIRTHDATE = "birthdate";
        public static final String ITIN = "itin";
        public static final String LOGINMETHOD = "loginMethod";
        public static final String PHONE = "phone";
        public static final String IDCLIENT = "idClient";
        public static final String HISTORY = "history";
        public static final String TYPE = "type";
        public static final String SOURCE = "source";
        public static final String IDUSER = "idUser";
        public static final String MESSAGE = "message";
        public static final String GENDER = "gender";
        public static final String COMPANY = "idCompany";
        public static final String PARTNER = "idPartner";





    }
}
