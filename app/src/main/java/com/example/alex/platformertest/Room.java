package com.example.alex.platformertest;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Room {

    private int id;
    private String roomName;
    private int globalX;
    private int globalY;
    private int blockMapId;
    private Background background;
    private Bitmap tileSet;
    private int roomWidth;
    private int roomHeight;
    private List<Block> blocks;

    private boolean rFD = false;

    Room() {
        id = 0;
        roomName = "Error";
        globalX = 0;
        globalY = 0;
        blockMapId = 0;
        roomWidth = 20;
        roomHeight = 10;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getGlobalX() {
        return globalX;
    }

    public void setGlobalX(int globalX) {
        this.globalX = globalX;
    }

    public int getGlobalY() {
        return globalY;
    }

    public void setGlobalY(int globalY) {
        this.globalY = globalY;
    }

    public int getBlockMapId() {
        return blockMapId;
    }

    public void setBlockMapId(int blockMapId) {
        this.blockMapId = blockMapId;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(String backgroundFile) {
        AssetManager assetManager = Constants.CURRENT_CONTEXT.getAssets();
        try {
            InputStream istr = assetManager.open("Backgrounds/"+ backgroundFile +".png");
            this.background = new Background(BitmapFactory.decodeStream(istr));
            istr.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    public Bitmap getTileSet() {
        return tileSet;
    }

    public void setTileSet(String tileSetFile) {
        AssetManager assetManager = Constants.CURRENT_CONTEXT.getAssets();
        try {
            InputStream istr = assetManager.open("TileSets/"+ tileSetFile +".png");
            this.tileSet = BitmapFactory.decodeStream(istr);
            istr.close();
        } catch (IOException e) {e.printStackTrace();}

    }

    public void setRoomWidth(int roomWidth) { this.roomWidth = roomWidth; }
    public void setRoomHeight(int roomHeight) { this.roomHeight = roomHeight; }
    public int getRoomWidth() { return roomWidth; }
    public int getRoomHeight() { return roomHeight; }

    public void addBlock(Block block) { this.blocks.add(block); }
    public void setBlocks(List<Block> blocks) { this.blocks = new ArrayList<Block>(blocks); }
    public List<Block> getBlocks() { return blocks; }

    public int getBlocksInRoom() { return blocks.size(); }

    public void setBlocksID() {
        for(int i = 0; i < blocks.size(); i++) {
            blocks.get(i).setBlockID(i);
        }
    }

    public void readyForDraw(GL10 gl) {
        background.readyForDraw(gl);
        for(Block b:blocks) {
            b.readyForDraw(gl, tileSet);
        }
        rFD = true;
    }

    public void update() {
        background.update();
        //for(Block b:blocks) {
        //    b.update();
        //}
    }

    public boolean isRFD() {
        return (background.isRFD() && rFD);
    }

    public void draw(GL10 gl) {
        //System.out.println("Room.draw Called");
        //System.out.println("GlobalX:" + globalX + "|||GlobalY:" + globalY);
        background.draw(gl);
        for(Block b:blocks) {
            //System.out.println(b.toString());
            b.draw(gl);
        }
    }
}
