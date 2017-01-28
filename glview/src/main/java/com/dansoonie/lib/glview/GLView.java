package com.dansoonie.lib.glview;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dansoonie on 1/29/17.
 */

public interface GLView {

  boolean LOG_ATTACH_DETACH = false;
  boolean LOG_THREADS = false;
  boolean LOG_PAUSE_RESUME = false;
  boolean LOG_SURFACE = false;
  boolean LOG_RENDERER = false;
  boolean LOG_RENDERER_DRAW_FRAME = false;
  boolean LOG_EGL = false;

  int RENDERMODE_WHEN_DIRTY = 0;
  int RENDERMODE_CONTINUOUSLY = 1;

  int DEBUG_CHECK_GL_ERROR = 1;
  int DEBUG_LOG_GL_CALLS = 2;

  interface Renderer {
    void onSurfaceCreated(GL10 gl, EGLConfig config);

    void onSurfaceChanged(GL10 gl, int width, int height);

    void onDrawFrame(GL10 gl);
  }

  interface GLWrapper {
    GL wrap(GL gl);
  }
}
