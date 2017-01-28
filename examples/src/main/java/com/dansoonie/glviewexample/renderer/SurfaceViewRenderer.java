package com.dansoonie.glviewexample.renderer;

import com.dansoonie.glviewexample.Triangle;
import com.dansoonie.lib.glview.GLSurfaceView;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dansoonie on 1/28/17.
 */

public class SurfaceViewRenderer implements GLSurfaceView.Renderer {
  private Triangle triangle;

  // mvpMatrix is an abbreviation for "Model View Projection Matrix"
  private final float[] mvpMatrix = new float[16];
  private final float[] projectionMatrix = new float[16];
  private final float[] viewMatrix = new float[16];
  private float[] mRotationMatrix = new float[16];

  @Override
  public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    triangle = new Triangle();
  }

  @Override
  public void onSurfaceChanged(GL10 gl, int width, int height) {
    GLES20.glViewport(0, 0, width, height);
    float ratio = (float) width / height;

    // this projection matrix is applied to object coordinates
    // in the onDrawFrame() method
    Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
  }

  @Override
  public void onDrawFrame(GL10 gl) {
    float[] scratch = new float[16];

    GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    // Set the camera position (View matrix)
    Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

    // Calculate the projection and view transformation
    Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

    // Create a rotation transformation for the triangle
    long time = SystemClock.uptimeMillis() % 4000L;
    float angle = 0.090f * ((int) time);
    Matrix.setRotateM(mRotationMatrix, 0, angle, 0, 0, -1.0f);

    // Combine the rotation matrix with the projection and camera view
    // Note that the mMVPMatrix factor *must be first* in order
    // for the matrix multiplication product to be correct.
    Matrix.multiplyMM(scratch, 0, mvpMatrix, 0, mRotationMatrix, 0);

    // Draw shape
    triangle.draw(scratch);
    fpsCounter.logFrame();
  }

  public static int loadShader(int type, String shaderCode){

    // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
    // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
    int shader = GLES20.glCreateShader(type);

    // add the source code to the shader and compile it
    GLES20.glShaderSource(shader, shaderCode);
    GLES20.glCompileShader(shader);

    return shader;
  }

  public class FPSCounter {
    long startTime = System.nanoTime();
    int frames = 0;

    public void logFrame() {
      frames++;
      if(System.nanoTime() - startTime >= 1000000000) {
        Log.d("FPSCounter", "fps: " + frames);
        frames = 0;
        startTime = System.nanoTime();
      }
    }
  }

  private FPSCounter fpsCounter = new FPSCounter();
}

