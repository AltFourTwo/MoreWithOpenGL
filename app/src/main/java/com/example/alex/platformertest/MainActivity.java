package com.example.alex.platformertest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    GamePanel gamePanel;
    GameRenderer gameRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
        decorView.setSystemUiVisibility(uiOptions);

        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        boolean supportES2 = (info.reqGlEsVersion >= 0x20000);

        if (supportES2) {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            Constants.SCREEN_WIDTH = dm.widthPixels;
            Constants.SCREEN_HEIGHT = dm.heightPixels;
            Constants.CURRENT_CONTEXT = this;
            Constants.debugColor.setColor(Color.RED);


            gameRenderer = new GameRenderer();

            gamePanel = new GamePanel(this);
            gamePanel.setEGLContextClientVersion(1);
            gamePanel.setRenderer(gameRenderer);
            gameRenderer.setGamePanel(gamePanel);

            this.setContentView(gamePanel);
        } else {
            Log.e("OpenGL ES", "Your device doesn't support OPEN GL ES. (" + info.reqGlEsVersion + ")");
        }
    }
}
