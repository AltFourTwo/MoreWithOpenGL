package com.example.alex.platformertest;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public abstract class Block {


    // Line Keys
    private static final int TOP_LINE               = 1;
    private static final int BOTTOM_LINE            = 2;
    private static final int LEFT_LINE              = 3;
    private static final int RIGHT_LINE             = 4;
    private static final int DIAGONAL_FORWARD_LINE  = 5;
    private static final int DIAGONAL_BACKWARD_LINE = 6;

    // Simple Collision Keys
    private static final int PASS_THROUGH        = 0;
    private static final int FULL_SOLID          = 1;
    private static final int TOP_ONLY            = 2;
    private static final int BOTTOM_ONLY         = 3;
    private static final int LEFT_ONLY           = 4;
    private static final int RIGHT_ONLY          = 5;
    private static final int TOP_LEFT_CORNER     = 6;
    private static final int TOP_RIGHT_CORNER    = 7;
    private static final int BOTTOM_LEFT_CORNER  = 8;
    private static final int BOTTOM_RIGHT_CORNER = 9;

    // Arc Shaped Collision Keys
    private static final int TOP_ARC    = 10;
    private static final int BOTTOM_ARC = 11;
    private static final int LEFT_ARC   = 12;
    private static final int RIGHT_ARC  = 13;

    // Slope Collision Keys
    private static final int SLOPE_FORWARD   = 14;
    private static final int SLOPE_BACKWARD  = 15;

    // Parallel Collision Keys
    private static final int PARALLEL_HORIZONTAL = 16;
    private static final int PARALLEL_VERTICAL   = 17;


    // Object data
    protected int blockX;
    protected int blockY;

    protected int blockWidth;
    protected int blockHeight;

    protected int textureX;
    protected int textureY;
    protected int textureWidth;
    protected int textureHeight;

    protected int collisionKey;

    protected boolean isFloor;

    private int blockID;
    private boolean rFD;
    private BlockDraw drawnForm;

    Block () {
        blockX = 0;
        blockY = 0;
        blockWidth = 1;
        blockHeight = 1;
        textureX = 0;
        textureY = 0;
        textureWidth = 1;
        textureHeight = 1;
        collisionKey = 1;
        isFloor = true;

        rFD = false;
    }

    public void setBlockX(int blockX) { this.blockX = blockX; }
    public void setBlockY(int blockY) { this.blockY = blockY; }
    public int getBlockX() { return blockX; }
    public int getBlockY() { return blockY; }

    public void setBlockWidth(int blockWidth) { this.blockWidth = blockWidth; }
    public void setBlockHeight(int blockHeight) { this.blockHeight = blockHeight; }
    public int getBlockWidth() { return blockWidth; }
    public int getBlockHeight() { return blockHeight; }

    public void setTextureX(int textureX) { this.textureX = textureX; }
    public void setTextureY(int textureY) { this.textureY = textureY; }
    public void setTextureWidth(int textureWidth) { this.textureWidth = textureWidth; }
    public void setTextureHeight(int textureHeight) { this.textureHeight = textureHeight; }
    public int getTextureX() { return textureX; }
    public int getTextureY() { return textureY; }
    public int getTextureWidth() { return textureWidth; }
    public int getTextureHeight() { return textureHeight; }

    public void setCollisionKey(int key) { this.collisionKey = key; }
    public int getCollisionKey() { return collisionKey; }

    public void setFloor(boolean floor) { isFloor = floor; }
    public boolean isFloor() { return isFloor; }

    public void setBlockID(int blockID) { this.blockID = blockID; }
    public int getBlockID() { return blockID; }

    public void readyForDraw(GL10 gl, Bitmap tileSet) {
        drawnForm = new BlockDraw(gl,this, tileSet);
        rFD = true;
    }

    public CollisionLine getTopLine() {
        return new CollisionLine(
                blockX,
                blockY,
                blockX+(blockWidth*32)-1,
                blockY);
    }

    public CollisionLine getBottomLine() {
        return new CollisionLine(
                blockX,
                blockY+(blockHeight*32)-1,
                blockX+(blockWidth*32)-1,
                blockY+(blockHeight*32)-1);
    }

    public CollisionLine getLeftLine() {
        return new CollisionLine(
                blockX,
                blockY,
                blockX,
                blockY+(blockHeight*32)-1);
    }

    public CollisionLine getRightLine() {
        return new CollisionLine(
                blockX+(blockWidth*32)-1,
                blockY,
                blockX+(blockWidth*32)-1,
                blockY+(blockHeight*32)-1);
    }

    public CollisionLine getDiagonalForwardLine() {
        return new CollisionLine(
                blockX,
                blockY+(blockHeight*32)-1,
                blockX+(blockWidth*32)-1,
                blockY);
    }

    public CollisionLine getDiagonalBackwardLine() {
        return new CollisionLine(
                blockX,
                blockY,
                blockX+(blockWidth*32)-1,
                blockY+(blockHeight*32)-1);
    }

    public CollisionLine getLine(int lineKey) {
        switch (lineKey) {
            case TOP_LINE:
                return getTopLine();
            case BOTTOM_LINE:
                return getBottomLine();
            case LEFT_LINE:
                return getLeftLine();
            case RIGHT_LINE:
                return getRightLine();
            case DIAGONAL_FORWARD_LINE:
                return getDiagonalForwardLine();
            case DIAGONAL_BACKWARD_LINE:
                return getDiagonalBackwardLine();
        }
        return null;
    }

    public ArrayList<CollisionLine> resolveCollisionType() {
        ArrayList<CollisionLine> lines = new ArrayList<CollisionLine>();

        switch (collisionKey) {
            case PASS_THROUGH:
                return null;
            case FULL_SOLID:
                lines.add(getTopLine());
                lines.add(getBottomLine());
                lines.add(getLeftLine());
                lines.add(getRightLine());
                break;
            case TOP_ONLY:
                lines.add(getTopLine());
                break;
            case BOTTOM_ONLY:
                lines.add(getBottomLine());
                break;
            case LEFT_ONLY:
                lines.add(getLeftLine());
                break;
            case RIGHT_ONLY:
                lines.add(getRightLine());
                break;
            case TOP_LEFT_CORNER:
                lines.add(getTopLine());
                lines.add(getLeftLine());
                break;
            case TOP_RIGHT_CORNER:
                lines.add(getTopLine());
                lines.add(getRightLine());
                break;
            case BOTTOM_LEFT_CORNER:
                lines.add(getBottomLine());
                lines.add(getLeftLine());
                break;
            case BOTTOM_RIGHT_CORNER:
                lines.add(getBottomLine());
                lines.add(getRightLine());
                break;
            case TOP_ARC:
                lines.add(getTopLine());
                lines.add(getLeftLine());
                lines.add(getRightLine());
                break;
            case BOTTOM_ARC:
                lines.add(getBottomLine());
                lines.add(getLeftLine());
                lines.add(getRightLine());
                break;
            case LEFT_ARC:
                lines.add(getLeftLine());
                lines.add(getTopLine());
                lines.add(getBottomLine());
                break;
            case RIGHT_ARC:
                lines.add(getRightLine());
                lines.add(getTopLine());
                lines.add(getBottomLine());
                break;
            case SLOPE_FORWARD:
                lines.add(getDiagonalForwardLine());
                if (isFloor) {
                    lines.add(getBottomLine());
                    lines.add(getRightLine());
                } else {
                    lines.add(getTopLine());
                    lines.add(getLeftLine());
                }
                break;
            case SLOPE_BACKWARD:
                lines.add(getDiagonalBackwardLine());
                if (isFloor) {
                    lines.add(getBottomLine());
                    lines.add(getLeftLine());
                } else {
                    lines.add(getTopLine());
                    lines.add(getRightLine());
                }
                break;
            case PARALLEL_HORIZONTAL:
                break;
            case PARALLEL_VERTICAL:
                break;
        }
        return lines;
    }

    public void resolveCollisionKey(String collisionKey) {
        switch (collisionKey) {
            case "FULL_SOLID"         : this.collisionKey = FULL_SOLID; break;
            case "PASS_THROUGH"       : this.collisionKey = PASS_THROUGH; break;
            case "TOP_ONLY"           : this.collisionKey = TOP_ONLY; break;
            case "BOTTOM_ONLY"        : this.collisionKey = BOTTOM_ONLY; break;
            case "LEFT_ONLY"          : this.collisionKey = LEFT_ONLY; break;
            case "RIGHT_ONLY"         : this.collisionKey = RIGHT_ONLY; break;
            case "TOP_LEFT_CORNER"    : this.collisionKey = TOP_LEFT_CORNER; break;
            case "TOP_RIGHT_CORNER"   : this.collisionKey = TOP_RIGHT_CORNER; break;
            case "BOTTOM_LEFT_CORNER" : this.collisionKey = BOTTOM_LEFT_CORNER; break;
            case "BOTTOM_RIGHT_CORNER": this.collisionKey = BOTTOM_RIGHT_CORNER; break;
            case "TOP_ARC"            : this.collisionKey = TOP_ARC; break;
            case "BOTTOM_ARC"         : this.collisionKey = BOTTOM_ARC; break;
            case "LEFT_ARC"           : this.collisionKey = LEFT_ARC; break;
            case "RIGHT_ARC"          : this.collisionKey = RIGHT_ARC; break;
            case "SLOPE_FORWARD"      : this.collisionKey = SLOPE_FORWARD; break;
            case "SLOPE_BACKWARD"     : this.collisionKey = SLOPE_BACKWARD; break;
            default : this.collisionKey = FULL_SOLID;
        }
    }

    public void update() {

    }

    public void draw(GL10 gl) {
        //System.out.println("Block.draw Called");
        if (rFD) drawnForm.draw(gl);
        /*
        for (int i = 0; i < blockHeight; i++) {
            for (int j = 0; j < blockWidth; j++) {
                //canvas.drawBitmap(tileSet[textureY[i][j]][textureX[i][j]], blockX+j*32, blockY+i*32+40, null);


            }
        } */
    }

    @Override
    @NonNull
    public String toString() {
        return "BlockX:" + blockX + "   " +
                "BlockY:" + blockY + "   " +
                "BlockWidth:" + blockWidth + "   " +
                "BlockHeight:" + blockHeight + "   " +
                "textureX:" + textureX + "   " +
                "textureY:" + textureY + "   " +
                "textureWidth:" + textureWidth + "   " +
                "textureHeight:" + textureHeight + "   " +
                "collision type:" + collisionKey + "   " +
                "BlockNumber :" + blockID + "   ";

    }
}
