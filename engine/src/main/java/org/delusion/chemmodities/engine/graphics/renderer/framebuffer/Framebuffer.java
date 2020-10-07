package org.delusion.chemmodities.engine.graphics.renderer.framebuffer;

import org.delusion.chemmodities.engine.graphics.renderer.texture.Texture;

import static org.lwjgl.opengl.GL46.*;

public class Framebuffer {

    private int handle;

    public Framebuffer() {
        handle = glGenFramebuffers();
        bind();
        unbind();
    }

    public void checkCompletion() {
        bind();
        if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
            throw new IllegalStateException("Framebuffer Creation Failed (FRAMEBUFFER::INCOMPLETE)");
        }
        unbind();
    }

    public void bindTextureTo(Texture texture) {
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, texture.getHandle(), 0);
    }



    public void bindRenderbufferTo(Renderbuffer renderbuffer) {
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, renderbuffer.getHandle());
    }

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER,handle);
    }

    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }
}
