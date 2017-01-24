package com.dansoonie.glviewexample;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by dansoonie on 17/1/24/.
 */

public class Triangle {

  private FloatBuffer vertexBuffer;
  private final int program;
  private int mvpMatrixHandle;

  // number of coordinates per vertex in this array
  static final int COORDS_PER_VERTEX = 3;
  static float triangleCoords[] = {   // in counterclockwise order:
      0.0f,  0.622008459f, 0.0f, // top
      -0.5f, -0.311004243f, 0.0f, // bottom left
      0.5f, -0.311004243f, 0.0f  // bottom right
  };

  // Set color with red, green, blue and alpha (opacity) values
  float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

  private final String vertexShaderCode =
      "uniform mat4 uMVPMatrix;" +
      "attribute vec4 vPosition;" +
          "void main() {" +
          "  gl_Position = uMVPMatrix * vPosition;" +
          "}";

  private final String fragmentShaderCode =
      "precision mediump float;" +
          "uniform vec4 vColor;" +
          "void main() {" +
          "  gl_FragColor = vColor;" +
          "}";

  private int positionHandle;
  private int colorHandle;

  private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
  private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

  public Triangle() {
    // initialize vertex byte buffer for shape coordinates
    ByteBuffer bb = ByteBuffer.allocateDirect(
        // (number of coordinate values * 4 bytes per float)
        triangleCoords.length * 4);
    // use the device hardware's native byte order
    bb.order(ByteOrder.nativeOrder());

    // create a floating point buffer from the ByteBuffer
    vertexBuffer = bb.asFloatBuffer();
    // add the coordinates to the FloatBuffer
    vertexBuffer.put(triangleCoords);
    // set the buffer to read the first coordinate
    vertexBuffer.position(0);

    int vertexShader = Renderer.loadShader(GLES20.GL_VERTEX_SHADER,
        vertexShaderCode);
    int fragmentShader = Renderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
        fragmentShaderCode);

    // create empty OpenGL ES Program
    program = GLES20.glCreateProgram();

    // add the vertex shader to program
    GLES20.glAttachShader(program, vertexShader);

    // add the fragment shader to program
    GLES20.glAttachShader(program, fragmentShader);

    // creates OpenGL ES program executables
    GLES20.glLinkProgram(program);
  }

  public void draw(float[] mvpMatrix) {
    // Add program to OpenGL ES environment
    GLES20.glUseProgram(program);

    // get handle to vertex shader's vPosition member
    positionHandle = GLES20.glGetAttribLocation(program, "vPosition");

    // Enable a handle to the triangle vertices
    GLES20.glEnableVertexAttribArray(positionHandle);

    // Prepare the triangle coordinate data
    GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
        GLES20.GL_FLOAT, false,
        vertexStride, vertexBuffer);

    // get handle to fragment shader's vColor member
    colorHandle = GLES20.glGetUniformLocation(program, "vColor");

    // Set color for drawing the triangle
    GLES20.glUniform4fv(colorHandle, 1, color, 0);

    // get handle to shape's transformation matrix
    mvpMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");

    // Pass the projection and view transformation to the shader
    GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);

    // Draw the triangle
    GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

    // Disable vertex array
    GLES20.glDisableVertexAttribArray(positionHandle);
  }


}