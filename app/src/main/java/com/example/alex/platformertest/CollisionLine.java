package com.example.alex.platformertest;

import javax.microedition.khronos.opengles.GL10;

public class CollisionLine {

    CollisionPoint pointA;
    CollisionPoint pointB;

    CollisionLine(CollisionPoint pointA, CollisionPoint pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    CollisionLine(int ptAX, int ptAY,int ptBX,int ptBY) {
        this.pointA = new CollisionPoint(ptAX,ptAY);
        this.pointB = new CollisionPoint(ptBX,ptBY);
    }

    public void update() {

    }

    public void draw(GL10 gl) {
        //canvas.drawLine(pointA.x,pointA.y+40,pointB.x,pointB.y+40, Constants.debugColor);
        //pointA.draw(canvas);
        //pointB.draw(canvas);
    }
}
