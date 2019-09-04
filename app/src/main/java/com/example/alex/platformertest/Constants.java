package com.example.alex.platformertest;

import android.content.Context;
import android.graphics.Paint;

import javax.microedition.khronos.opengles.GL10;

public class Constants {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static int TARGET_WIDTH = 640;
    public static int TARGET_HEIGHT = 360;

    public static Context CURRENT_CONTEXT;
    public static GL10 CURRENT_GL;

    public static boolean debug = true;
    public static Paint debugColor = new Paint();



    public static long INIT_TIME;

}
