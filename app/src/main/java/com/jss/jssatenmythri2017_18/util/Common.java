package com.jss.jssatenmythri2017_18.util;

/**
 * Created by NgocTri on 4/11/2016.
 */
public class Common {

	public static final String BASE_URL = "http://35.202.2.74:3000";

    public static final String SERVICE_API_URL = BASE_URL + "/login/";
    public static final String SERVICE_SIGN_UP_URL = BASE_URL +"/add/";
    public static final String SERVICE_ADDGAME_URL = BASE_URL +"/addgame/";
    public static final String SERVICE_COUNT_URL = BASE_URL +"/count/";
    public static final String SERVICE_CHECK_URL = BASE_URL +"/check/";
    public static final String SERVICE_CHECK_VERSION_URL = BASE_URL +"/version/";
    public static final String SERVICE_USERGAMES = BASE_URL +"/usergames/";
    public static final String SERVICE_GAMEUSERS = BASE_URL +"/gameusers/";
    public static final String SERVICE_SETMATCH = BASE_URL +"/fixmatch/";
    public static final String SERVICE_GETMATCH = BASE_URL +"/findfixedmatch/";
    public static final String SERVICE_ADDTSHIRT_URL = BASE_URL +"/tshirt/";
    public static final String SERVICE_UPDATE_WINNER = BASE_URL +"/updatewinner/";
    public static final String SERVICE_UPDATE_LOOSER = BASE_URL +"/updatelooser/";

    public static final String SERVICE_ADMIN_ALL_FIXTURES = BASE_URL + "/admin/findfixedmatch/";
    public static final String SERVICE_ADMIN_REGISTER = BASE_URL + "/admin/add/user/";


    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_ERROR= 1;
    public static final int RESULT_USER_EXISTS = 2;

    /*
     "Badminton",
             "Table Tennis",
             "Carrom",
             "Tug Of War",
             "Cricket",
             "Football",
             "Volleyball",
             "Chess",
             "Basketball",
             "Shot Put",
             "Athelitics",
             "Kabaddi",
             "Fun Games"*/

    public static final String GAME_BADMINTON = "60";
    public static final String GAME_TT = "61";
    public static final String GAME_CARROM = "62";
    public static final String GAME_TOW = "63";
    public static final String GAME_CRICKET = "64";
    public static final String GAME_FOOTBALL = "65";
    public static final String GAME_VOLLEYBALL = "66";
    public static final String GAME_CHESS = "67";
    public static final String GAME_BASKETBALL = "68";
    public static final String GAME_SHOTPUT = "69";
    public static final String GAME_ATHELITICS = "70";
    public static final String GAME_KABADDI = "71";
    public static final String GAME_FUNGAMES = "72";

}
