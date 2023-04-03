package com.example.quran.utils;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;


        import com.example.quran.model.user;

        import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context _context;

    public static final String IS_LOGGED_IN = "isLogginIn";
    public static final String ID_USER = "idUser";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public Context get_context() {return _context;}

    //constructor
    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(user user)
    {
        editor.putBoolean(IS_LOGGED_IN,true);
        editor.putString(ID_USER,user.getId());
        editor.putString(USERNAME,user.getUsername());
        editor.putString(PASSWORD,user.getPassword());

    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(ID_USER,sharedPreferences.getString(ID_USER,null));
        user.put(USERNAME,sharedPreferences.getString(USERNAME,null));
        user.put(PASSWORD,sharedPreferences.getString(PASSWORD,null));
        return  user;

    }

    public void logoutUser(){
        //clearing all data from shared Preferences
        editor.clear();
        editor.commit();
    }

    public boolean isLogginIN(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }

}