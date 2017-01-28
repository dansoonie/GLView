package com.dansoonie.glviewexample;

import com.dansoonie.lib.glview.GLSurfaceView;

import android.app.Activity;
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

  @Override
  protected void onResume() {
    super.onResume();
    surfaceView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    surfaceView.onPause();
  }
}
