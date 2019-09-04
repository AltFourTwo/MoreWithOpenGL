package com.example.alex.platformertest;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    private int activeScene;

    private boolean rFD = false;

    public SceneManager() {
        activeScene = 0;
        scenes.add(new GamePlayScene());
    }

    public int getActiveScene() {
        return activeScene;
    }
    public void setActiveScene(int activeScene) {
        this.activeScene = activeScene;
    }


    public void receiveTouch(MotionEvent event) {
        scenes.get(activeScene).receiveTouch(event);
    }

    public void update() {
        scenes.get(activeScene).update();
    }

    public void drawCanvas(Canvas canvas) {
        scenes.get(activeScene).drawCanvas(canvas);
    }

    public boolean isRFD() {
        return (scenes.get(activeScene).isRFD() && rFD);
    }

    public void readyForDraw(GL10 gl) {
        scenes.get(activeScene).readyForDraw(gl);
        rFD = true;
    }

    public void draw(GL10 gl) {
        //System.out.println("SceneManager.draw Called");
        scenes.get(activeScene).draw(gl);
    }
}