package com.example.alex.platformertest;

import android.graphics.Canvas;
import android.view.MotionEvent;

import javax.microedition.khronos.opengles.GL10;

public interface Scene {
    public void update();
    public void drawCanvas(Canvas canvas);
    public boolean isRFD();
    public void readyForDraw(GL10 gl);
    public void draw(GL10 gl);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
