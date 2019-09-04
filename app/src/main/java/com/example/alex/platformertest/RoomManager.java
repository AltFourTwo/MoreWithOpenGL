package com.example.alex.platformertest;

import javax.microedition.khronos.opengles.GL10;

public class RoomManager {

    private Room room;
    private CollisionMap clMap;

    private boolean rFD = false;

    public RoomManager() {
        room = roomXMLParser.getMapData(Constants.CURRENT_CONTEXT, 1);
        room.setBlocks(blockMapXMLParser.getBlockMap(Constants.CURRENT_CONTEXT, 1));
        room.setBlocksID();
        clMap = new CollisionMap(room.getBlocks());




    }

    public void update() {
        room.update();
    }

    public void changeRoom(int id) {
        rFD = false;
        room = roomXMLParser.getMapData(Constants.CURRENT_CONTEXT, id);
        room.setBlocks(blockMapXMLParser.getBlockMap(Constants.CURRENT_CONTEXT, id));
        room.setBlocksID();
        clMap = new CollisionMap(room.getBlocks());

    }

    public boolean isRFD() {
        return (room.isRFD() && rFD);
    }

    public void readyForDraw(GL10 gl) {
        room.readyForDraw(gl);
        rFD = true;
    }

    public void draw(GL10 gl) {
        //TODO stuff

        //System.out.println("RoomManager.draw Called");
        room.draw(gl);
        //if (Constants.debug) clMap.draw(gl);
    }
}
