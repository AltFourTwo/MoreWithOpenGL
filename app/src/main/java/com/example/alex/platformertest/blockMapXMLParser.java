package com.example.alex.platformertest;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class blockMapXMLParser {


    //private static final String KEY_BLOCKMAPID = "id";
    private static final String KEY_BLOCK = "block";
    private static final String KEY_BLOCKTYPE = "blockType";
    private static final String KEY_SOLID = "Solid";
    private static final String KEY_SEMI_SOLID = "SemiSolid";
    //private static final String KEY_SOLID = "Solid";
    //private static final String KEY_SOLID = "Solid";
    //private static final String KEY_SOLID = "Solid";
    //private static final String KEY_SOLID = "Solid";
    //private static final String KEY_SOLID = "Solid";
    private static final String KEY_BLOCK_DIMENSIONS = "blockDimensions";
    private static final String KEY_BLOCK_COORDS = "blockCoords";
    private static final String KEY_TILESETCOORDS = "tileSetCoords";
    private static final String KEY_BLOCKPROPERTIES = "blockProperties";
    private static final String KEY_COLLISIONTYPE = "collisionType";
    private static final String KEY_ISFLOOR = "isFloor";
    private static final String KEY_VISIBLE = "visible";
    private static final String KEY_BREAKABLE = "breakable";
    //private static final String KEY_SECONDARYTEXTURE = "fullSolid";
    private static final String KEY_ANIMATION = "animation";
    //static final String KEY_ISDOOR = "isDoor";
    private static final String KEY_DOOR = "door";
    private static final String KEY_DOORID = "doorId";
    private static final String KEY_LOCKEDBY = "lockedBy";



    public static ArrayList<Block> getBlockMap(Context context, int blockMapID) {

        ArrayList<Block> blocks = new ArrayList<Block>();
        String readData = "";
        String id = "";

        try {
            // Get factory and pullParser
            XmlPullParserFactory reader = XmlPullParserFactory.newInstance();
            XmlPullParser readBlockMap = reader.newPullParser();

            if(blockMapID < 10) id = "0"+blockMapID;

            // Open InputStream and Reader of the file
            InputStream blockDataInput = context.getAssets().open("BlockMaps/blockmap"+id+".xml");
            BufferedReader blockDataReader = new BufferedReader(new InputStreamReader(blockDataInput));

            // point the parser to the file
            readBlockMap.setInput(blockDataReader);

            // get initial eventType
            int eventType = readBlockMap.getEventType();

            // boolean guides when reading xml
            boolean newBlock = false;
            boolean properties = false;
            boolean door = false;

            Block curBlock = new SolidBlock();

            // Loop through pull events until we reach END_DOCUMENT

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = readBlockMap.getName();
                if (tagName == null) tagName="";

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase(KEY_BLOCK)) {
                            newBlock = true;
                        }else if (tagName.equalsIgnoreCase(KEY_BLOCKTYPE)) {
                            if (readBlockMap.getAttributeValue(0).equalsIgnoreCase(KEY_SOLID)) {
                                curBlock = new SolidBlock();
                                //blocks.add(new SolidBlock());
                            } else if (readBlockMap.getAttributeValue(0).equalsIgnoreCase(KEY_SEMI_SOLID)) {
                                curBlock = new SemiSolidBlock();
                                //blocks.add(new SemiSolidBlock());
                            }
                        } else if (tagName.equalsIgnoreCase(KEY_BLOCK_DIMENSIONS)) {
                            curBlock.setBlockWidth(toInt(readBlockMap.getAttributeValue(null, "width")));
                            curBlock.setBlockHeight(toInt(readBlockMap.getAttributeValue(null, "height")));
                        } else if (tagName.equalsIgnoreCase(KEY_BLOCK_COORDS)) {
                            curBlock.setBlockX(toInt(readBlockMap.getAttributeValue(null, "x"))*32);
                            curBlock.setBlockY(toInt(readBlockMap.getAttributeValue(null, "y"))*32);
                        } else if (tagName.equalsIgnoreCase(KEY_TILESETCOORDS)) {
                            curBlock.setTextureX(toInt(readBlockMap.getAttributeValue(null, "x")));
                            curBlock.setTextureY(toInt(readBlockMap.getAttributeValue(null, "y")));
                            curBlock.setTextureWidth(toInt(readBlockMap.getAttributeValue(null, "width")));
                            curBlock.setTextureHeight(toInt(readBlockMap.getAttributeValue(null, "height")));
                        } else if (tagName.equalsIgnoreCase(KEY_BLOCKPROPERTIES)) {
                            properties = true;
                        } else if (tagName.equalsIgnoreCase(KEY_DOOR)) {
                            door = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        readData = readBlockMap.getText();

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase(KEY_BLOCK)) {
                            //System.out.println(curBlock.toString());
                            blocks.add(curBlock);
                            newBlock = false;
                        } else if (newBlock) {
                            if (properties) {
                                if (tagName.equalsIgnoreCase(KEY_COLLISIONTYPE)) {
                                    curBlock.resolveCollisionKey(readData);
                                } else if (tagName.equalsIgnoreCase(KEY_ISFLOOR)) {
                                    curBlock.setFloor(Boolean.parseBoolean(readData));
                                } else if (tagName.equalsIgnoreCase(KEY_VISIBLE)) {
                                    // TODO
                                    //blocks.get(blocks.size()-1).setVisible(Boolean.parseBoolean(readData));
                                } else if (tagName.equalsIgnoreCase(KEY_BREAKABLE)) {
                                    //blocks.get(blocks.size()-1).setBreakable(Boolean.parseBoolean(readData));
                                } else if (tagName.equalsIgnoreCase(KEY_ANIMATION)) {
                                    //blocks.get(blocks.size()-1).setAnimation(Integer.parseInt(readData));
                                }
                            } else if (door) {
                                if (tagName.equalsIgnoreCase(KEY_DOORID)) {
                                    //blocks.get(blocks.size()-1).setAnimation(Integer.parseInt(readData));
                                } else if (tagName.equalsIgnoreCase(KEY_LOCKEDBY)) {
                                    //blocks.get(blocks.size()-1).setAnimation(Integer.parseInt(readData));
                                }
                            }
                        }
                        break;

                    default : break;
                }
                eventType = readBlockMap.next();
            }

            readBlockMap = null;
            blockDataInput.close();
            blockDataReader.close();
        } catch (Exception e) { e.printStackTrace();}

        return blocks;
    }

    public static int toInt(String in) {
        return (in != null ? Integer.parseInt(in):1);
    }
}
