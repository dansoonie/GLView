package com.dansoonie.glviewexample;

import com.dansoonie.glviewexample.renderer.Renderer;
import com.dansoonie.lib.glview.GLSurfaceView;
import com.dansoonie.lib.glview.GLView;

import android.app.Activity;
import android.os.Bundle;

public class GLSurfaceViewExample extends Activity {

  private GLSurfaceView surfaceView;
  private GLView.Renderer renderer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    surfaceView = new GLSurfaceView(this);
    surfaceView.setEGLContextClientVersion(2);
    setContentView(surfaceView);

    renderer = new Renderer();
    surfaceView.setRenderer(renderer);
    surfaceView.setRenderMode(GLView.RENDERMODE_CONTINUOUSLY);
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
