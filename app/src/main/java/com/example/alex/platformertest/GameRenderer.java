package com.example.alex.platformertest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameRenderer implements GLSurfaceView.Renderer {

    GamePanel gamePanel;
    SceneManager manager;

    private Triangle triangle;

    private int pos = 5;
    private int skipframe = 0;
    private boolean left;

    private boolean drawInSetup = false;

    public void setGamePanel(GamePanel gp) {
        gamePanel = gp;
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        //System.out.println("GameRendered.OnDrawFrame Called");
        gl.glClearColor(0.8f,0.0f,0.0f,1.0f);
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);

        if (gamePanel.isRFD()) {
            gamePanel.draw(gl);
            drawInSetup = false;

            if (pos > 10) {
                pos = 0;
                left = !left;
            }

            if (skipframe == 0) {
                gl.glTranslatef((left)? -10f:10f,0,0);
                pos++;
            }

            skipframe++;
            if (skipframe == 4) skipframe = 0;
        }
        else {
            gamePanel.readyForDraw(gl);
            drawInSetup = true;
        }




        triangle.draw(gl);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Constants.CURRENT_GL = gl;
        triangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, Constants.TARGET_WIDTH, 0, Constants.TARGET_HEIGHT, -1f, 1);
        //gl.glDepthFunc(GL10.GL_DEPTH_BUFFER_BIT);
        //gl.glDepthFunc(GL10.GL_MAX_PROJECTION_STACK_DEPTH);

        //gl.glFrustumf(0, Constants.TARGET_WIDTH, 0, Constants.TARGET_HEIGHT, -1f, 1f);
        //GLU.gluOrtho2D(gl , 0, Constants.TARGET_WIDTH, 0, Constants.TARGET_HEIGHT);
        //GLU.gluPerspective(gl, 90.0f, 16f/9f, -1f, 1f);
        //gl.glMatrixMode(GL10.GL_MODELVIEW);
        //gl.glLoadIdentity();

        triangle = new Triangle();
    }


}
