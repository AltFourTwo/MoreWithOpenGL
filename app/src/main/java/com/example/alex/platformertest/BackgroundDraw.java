package com.example.alex.platformertest;

import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class BackgroundDraw {

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;
    private FloatBuffer textureBuffer;

    private float vertices[];
    private short indices[];

    private int texture[];
    private float textureCoors[];



    public BackgroundDraw (GL10 gl, Background bg) {
        vertices = new float[] {
                0-640, bg.getImage().getHeight(), 1f,
                0-640, 0, 1f,
                bg.getImage().getWidth()+640, bg.getImage().getHeight(), 1f,
                bg.getImage().getWidth()+640, 0, 1f
        };
        indices = new short[] {0,1,2,3};

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

        if ( texture == null) {
            texture = new int[1];
        }

        textureCoors = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                3.0f, 0.0f,
                3.0f, 1.0f
        };

        gl.glGenTextures(1, texture, 0);

        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0]);

        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);

        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0, bg.getImage(),0);

        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoors.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        textureBuffer = tbb.asFloatBuffer();
        textureBuffer.put(textureCoors);
        textureBuffer.position(0);
    }

    public void draw(GL10 gl) {
        //System.out.println("BlockDraw.draw Called");
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);


        // Texture Code
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // Enable the texture state
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Enable Texture Alpha
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL10.GL_ALPHA_BITS);

        // Point to our buffers
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        //
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0]);

        //gl.glEnable(GL10.GL_DEPTH_TEST);

        // Finally drawing the triangles
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);




        // Resetting stuff to prevent a mess
        //indexBuffer.position(0);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);

        gl.glDisable(GL10.GL_BLEND);
        gl.glDisable(GL10.GL_ALPHA_BITS);

        //gl.glDisable(GL10.GL_DEPTH_TEST);

        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
