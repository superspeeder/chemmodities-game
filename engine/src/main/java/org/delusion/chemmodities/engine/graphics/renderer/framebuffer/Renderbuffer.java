package org.delusion.chemmodities.engine.graphics.renderer.framebuffer;

import static org.lwjgl.opengl.GL46.*;

public class Renderbuffer {
    private int handle;

    public Renderbuffer() {
        handle = glGenRenderbuffers();
        bind();
        glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH24_STENCIL8, 800, 600);

    }

    public void bind() {
        glBindRenderbuffer(GL_RENDERBUFFER, handle);
    }

    public void unbind() {
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
    }

    public int getHandle() {
        return handle;
    }
}
