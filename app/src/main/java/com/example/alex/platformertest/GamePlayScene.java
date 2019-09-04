package com.example.alex.platformertest;

import android.graphics.Canvas;
import android.view.MotionEvent;

import javax.microedition.khronos.opengles.GL10;

public class GamePlayScene implements Scene {

    private RoomManager roomManager;

    private boolean rFD = false;

    public GamePlayScene() {

        //TODO set app read files permissions

        roomManager = new RoomManager();
    }

    public void reset() {
    }

    @Override
    public void terminate() {

    }

    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
    }

    public void drawCanvas(Canvas canvas){

    }

    public boolean isRFD() {
        return (roomManager.isRFD() && rFD);
    }

    public void readyForDraw(GL10 gl) {
        roomManager.readyForDraw(gl);
        rFD = true;
    }

    public void draw(GL10 gl) {
        //System.out.println("GamePlayScene.draw Called");
        roomManager.draw(gl);
    }

    public void update() {
        roomManager.update();
    }

}