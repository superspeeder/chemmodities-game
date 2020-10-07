package org.delusion.chemmodities.engine.graphics.renderer;

import org.delusion.chemmodities.engine.graphics.renderer.buffer.VertexBuffer;

import static org.lwjgl.opengl.GL46.*;

public class Renderer2d {


    public void fillColor(float r, float g, float b, float a) {
        glClearColor(r,g,b,a);
    }

    public void fillScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
    }

    public static void drawArrays_lowlevel(int mode, int first, int count) {
        glDrawArrays(mode, first, count);
    }

    public static void drawArraysTris_lowlevel(int first, int count) {
        drawArrays_lowlevel(GL_TRIANGLES, first, count);
    }

    public static void drawVBO(int mode, VertexBuffer vbo) {
        drawArraysTris_lowlevel(0,vbo.verticies());
    }
}
