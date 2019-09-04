package com.example.alex.platformertest;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import javax.microedition.khronos.opengles.GL10;

public class GamePanel extends GLSurfaceView {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    public static final int MOVESPEED = -5;

    private MainThread thread;
    //GameRenderer gameRenderer;

    protected SceneManager manager;

    private boolean rFD = false;

    public GamePanel(Context context) {
        super(context);


        Constants.CURRENT_CONTEXT = context;
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);

        manager = new SceneManager();

        thread = new MainThread(this);
        Constants.INIT_TIME = System.currentTimeMillis();



        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        super.surfaceChanged(holder, format, width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);

        /*
        boolean retry = true;
        int counter = 0;
        while (retry && counter < 1000) {
            counter++;
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;
            } catch(Exception e) {e.printStackTrace();}
        }
        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //Constants.CURRENT_GL.glTranslatef(event.getX(), event.getY(),0);
                    //playerPoint.set((int)event.getX(), (int)event.getY());
                break;
        }



        //MOVED INTO GamePlayScene.receiveTouch(); METHOD.

        manager.receiveTouch(event);

        return true;
        //return super.onTouchEvent(event);
    }

    //public boolean onTouchEvent(MotionEvent event) {return true;}

    public void update() {
        manager.update();
    }


    public void drawCanvas(Canvas canvas) {
        super.draw(canvas);

        // TODO I sense some resolution problems



    /*

        final float scaleFactorX = Constants.SCREEN_WIDTH/(WIDTH*1.f);
        final float scaleFactorY = Constants.SCREEN_HEIGHT/(HEIGHT*1.f);
        if (canvas != null ) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            manager.draw(canvas);
            canvas.restoreToCount(savedState);

        }

    */
    }

    public boolean isRFD() {
        return (manager.isRFD() && rFD);
    }

    public void readyForDraw(GL10 gl) {
        manager.readyForDraw(gl);
        rFD = true;
    }

    public void draw(GL10 gl) {
        //System.out.println("GamePanel.draw Called");
        manager.draw(gl);
    }




}
