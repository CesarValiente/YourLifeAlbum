package com.cesar.yourlifealbum.application;

/**
 * 
 * @author cesar.valiente@gmail.com
 * 
 *         This class is used to have all the constants used in the app. It has
 *         different subclasses which have the different constants group by
 *         type.
 * 
 */
public class AppConstants {

    /**
     * 
     * @author cesar.valiente@gmail.com
     * 
     *         This class has the constants regarding the different development
     *         flags we use along the whole app
     */
    public class Development {

        public static final boolean DEBUG = true;
    }

    /**
     * 
     * @author Cesar Valiente Gordo (mail.cesar.valiente@gmail.com)
     * 
     *         Data regarding authentication
     */
    public class Eyeem {

        public static final String API_URL = "https://www.eyeem.com/api/v2";
        public static final String CLIENT_ID = "yscmYISGquEKyDDCykgOXRBhZfqd4hr8";
        public static final String CLIENT_SECRET = "EmSVgKrgQE210Wc1jReTx31EmPyWBo8x";

        // This is the code we've retrieved after the request to get the
        // authorization code (we get it in the callback url)
        public static final String AUTH_CODE = "244d0c72f374210eb8ebb4c95472c59be472c20c";
        // In each request we have to use the Bearer "access_token" in the
        // header to get the data, or add the "access_token" param at the end of
        // the query
        public static final String TOKEN_TYPE = "Bearer";
        public static final String ACCESS_TOKEN = "ac8a7609706cf836313f281716b2f3a290839838";
        public static final String GET_ALL_PHOTOS = "/users/me/photos";
        public static final String LIMIT_PHOTOS = "&limit=100";
        public static final String ACCESS_TOKEN_PARAM = "?access_token=";
    }

    /**
     * 
     * @author Cesar Valiente Gordo (cesar.valiente@gmail.com)
     * 
     *         Data regarding network
     */
    public class Network {
        public static final String TEXT_ENCODING = "UTF-8";
    }

    /**
     * 
     * @author cesarvaliente
     * 
     */
    public class Messages {
        public static final String GET_ALL_PHOTOS_MSG = "getAllPhotos";
        public static final String DAY_PHOTOS = "dayPhotos";
    }

    /**
     * 
     * @author cesar.valiente@gmail.com
     * 
     *         This class has the constants regarding data constants we use
     *         through the app
     */
    public class Data {

        // database name
        public static final String DB_NAME = "yourlifealbum.db";
        public static final int DB_VERSION = 1;

        // DB table names
        public static final String TABLE_PHOTOS = "Photos";

        // DB Columns
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_THUMB_URL = "thumbUrl";
        public static final String COLUMN_PHOTO_URL = "photoUrl";
        public static final String COLUMN_WEB_URL = "webUrl";
        public static final String COLUMN_WIDTH = "width";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_UPDATED = "updated";

        // Drop tables
        public static final String DROP_TABLE_PHOTOS = "DROP TABLE IF EXISTS `Photos` ;";

        /**
         * Create data table COUNTRIES
         */
        public static final String CREATE_TABLE_PHOTOS = "CREATE  TABLE IF NOT EXISTS `Photos` ("
                + "`id`  TEXT NOT NULL PRIMARY KEY ,"
                + "`thumbUrl` TEXT NOT NULL ,"
                + "`photoUrl` TEXT NOT NULL ,"
                + "`webUrl` TEXT NOT NULL ,"
                + "`width` INTEGER ,"
                + "`height` INTEGER ," + "`updated` TEXT NOT NULL);";
    }

    /**
     * 
     * @author Cesar Valiente Gordo
     * @mail cesar.valiente@gmail.com
     * 
     *       This class has the shared preferences constants.
     */
    public class Preferences {

        public static final String PREFS_NAME = "yourlifealbum_prefs";
        public static final String PREFS_FIRST_USE = "firstUse";
    }

}
