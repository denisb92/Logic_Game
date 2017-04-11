package com.example.denis.assignment3.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.denis.assignment3.R;
import com.example.denis.assignment3.model.AccountDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 3/19/2017.
 */

public class Utils {

    public static String LEVEL = "";
    //Holds the value for LEVEL
    public  static String CURRENTUSER ="Current_user";
    public static int LEVELONEATTEMPTS = 0;
    public static int LEVELTWOATTEMPTS = 0;
    public static int LEVELTHREEATTEMPTS = 0;
    public static int LEVELFOURATTEMPTS =0;
    public static int LEVELFIVEATTEMPTS =0;
    public static int LEVELSIXATTEMPTS=0;
    public static int TOTALFAILEDATTEMPTS;
    public static int CURRENTUSERID;
    public static final String SHARED_PREF_FILENAME="com.example.denis.assignment3.SHAREDFILE1";
    public static List<String> levels = new ArrayList<>();

    public static AccountDbHelper mDbHelper;




}
