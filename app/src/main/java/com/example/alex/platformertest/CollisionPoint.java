package com.example.alex.platformertest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import javax.microedition.khronos.opengles.GL10;

public class CollisionPoint extends Point {

    CollisionPoint(int x, int y) {
        super.x = x;
        super.y = y;
    }


    public void update() {

    }

    public void draw(GL10 gl) {
        Rect pt = new Rect(x-2,y+38,x+2,y+42);
        //canvas.drawRect(pt, Constants.debugColor);
        //TODO stuff
    }
}
