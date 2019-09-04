package com.example.alex.platformertest;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class CollisionMap {

    private ArrayList<ArrayList<CollisionLine>> lines;

    public CollisionMap(List<Block> blocks) {
        lines = new ArrayList<ArrayList<CollisionLine>>();
        for (Block bl: blocks) {
            lines.add(bl.resolveCollisionType());
        }
    }

    public void update() {

    }

    public void draw(GL10 gl) {
        for (ArrayList<CollisionLine> clLinegp:lines) {
            for (CollisionLine clLine: clLinegp) {
                clLine.draw(gl);
                //TODO stuff
            }
        }
    }
}
