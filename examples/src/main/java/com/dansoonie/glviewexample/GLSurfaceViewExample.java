package com.dansoonie.glviewexample;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLSurfaceViewExample extends Activity {

  private GLSurfaceView surfaceView;
  private Renderer renderer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    surfaceView = new GLSurfaceView(this);
    surfaceView.setEGLContextClientVersion(2);
    setContentView(surfaceView);

    renderer = new Renderer();
    surfaceView.setRenderer(renderer);
    surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

  }
}
