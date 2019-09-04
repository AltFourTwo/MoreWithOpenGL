package com.example.alex.platformertest;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class roomXMLParser {

    private static final String KEY_ROOM = "room";
    private static final String KEY_ID = "id";
    private static final String KEY_ROOMNAME = "roomName";
    private static final String KEY_GLOBALCOORDS = "globalCoords";
    private static final String KEY_BACKGROUND = "background";
    private static final String KEY_BLOCKMAPID = "blockMapId";
    private static final String KEY_TILESET = "tileSet";
    private static final String KEY_DIMENSIONS = "dimensions";
    private static final String KEY_IN_BLOCKS = "inBlocks";
    private static final String KEY_IN_SCREENS = "inScreens";
    //static final String KEY_DOORS = "doors";


    // change void later
    public static Room getMapData(Context context, int mapID) {

        Room room = null;
        String readData = null;
        String id = null;


        try {

            // Get factory and pullParser
            XmlPullParserFactory reader = XmlPullParserFactory.newInstance();
            XmlPullParser readMap = reader.newPullParser();

            if(mapID < 10) id = "0"+mapID;

            // Open InputStream and Reader of the file
            InputStream mapDataInput = context.getAssets().open("Rooms/room"+ id +".xml");
            BufferedReader  mapDataReader = new BufferedReader(new InputStreamReader(mapDataInput));

            // point the parser to the file
            readMap.setInput(mapDataReader);

            // get initial eventType
            int eventType = readMap.getEventType();

            // boolean guides when reading xml
            //boolean doors = false;
            //boolean doorPlayerSpawn = false;
            //boolean doorPSx = false;
            //boolean doorPSy = false;

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = readMap.getName();
                if (tagName == null) tagName="";

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase(KEY_ROOM)) {
                            // if loading a new room, creates a new ScreenBlockMap object
                            room = new Room();
                        } else if (tagName.equalsIgnoreCase(KEY_ID)) {
                            room.setId(Integer.parseInt(readMap.getAttributeValue(null, "id")));
                        } else if (tagName.equalsIgnoreCase(KEY_ROOMNAME)) {
                            room.setRoomName(readMap.getAttributeValue(null, "name"));
                        } else if (tagName.equalsIgnoreCase(KEY_GLOBALCOORDS)) {
                            room.setGlobalX(Integer.parseInt(readMap.getAttributeValue(null, "x")));
                            room.setGlobalY(Integer.parseInt(readMap.getAttributeValue(null, "y")));
                        } else if (tagName.equalsIgnoreCase(KEY_BACKGROUND)) {
                            room.setBackground(readMap.getAttributeValue(null, "name"));
                        } else if (tagName.equalsIgnoreCase(KEY_BLOCKMAPID)) {
                            room.setBlockMapId(Integer.parseInt(readMap.getAttributeValue(null, "id")));
                        } else if (tagName.equalsIgnoreCase(KEY_TILESET)) {
                            room.setTileSet(readMap.getAttributeValue(null, "name"));
                        } else if (tagName.equalsIgnoreCase(KEY_DIMENSIONS)) {
                            if (readMap.getAttributeValue(0).equalsIgnoreCase("preset")) {
                                if(readMap.getAttributeValue(1).equalsIgnoreCase("simple")) {
                                    room.setRoomWidth(20);
                                    room.setRoomWidth(10);
                                }
                            } else if (readMap.getAttributeValue(0).equalsIgnoreCase(KEY_IN_BLOCKS)) {
                                room.setRoomWidth(Integer.parseInt(readMap.getAttributeValue(null, "width")));
                                room.setRoomWidth(Integer.parseInt(readMap.getAttributeValue(null, "height")));
                            } else if (readMap.getAttributeValue(0).equalsIgnoreCase(KEY_IN_SCREENS)) {
                                room.setRoomWidth(Integer.parseInt(readMap.getAttributeValue(null, "width"))*20);
                                room.setRoomWidth(Integer.parseInt(readMap.getAttributeValue(null, "height"))*10);
                            }
                        }
                    break;

                    case XmlPullParser.TEXT:
                        readData = readMap.getText();
                    break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase(KEY_ROOM)) {

                        }
                    break;

                    default : break;
                }
                eventType = readMap.next();
            }
            readMap = null;
            mapDataInput.close();
            mapDataReader.close();

        } catch (Exception e) { e.printStackTrace();}


        return room;
    }
}
