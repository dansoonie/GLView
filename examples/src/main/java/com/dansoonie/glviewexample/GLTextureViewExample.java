package com.dansoonie.glviewexample;

import com.dansoonie.glviewexample.renderer.TextureViewRenderer;
import com.dansoonie.lib.glview.GLTextureView;

import android.app.Activity;
import android.os.Bundle;

public class GLTextureViewExample extends Activity {

  private GLTextureView textureView;
  private TextureViewRenderer renderer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    textureView = new GLTextureView(this);
    textureView.setEGLContextClientVersion(2);
    setContentView(textureView);

    renderer = new TextureViewRenderer();
    textureView.setRenderer(renderer);
    textureView.setRenderMode(GLTextureView.RENDERMODE_CONTINUOUSLY);
  }

  @Override
  protected void onResume() {
    super.onResume();
    textureView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    textureView.onPause();
  }
}
