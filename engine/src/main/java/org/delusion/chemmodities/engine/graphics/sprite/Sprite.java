package org.delusion.chemmodities.engine.graphics.sprite;

import org.delusion.chemmodities.engine.graphics.renderer.Quad;
import org.delusion.chemmodities.engine.graphics.renderer.Renderer2d;
import org.delusion.chemmodities.engine.graphics.renderer.buffer.VertexArray;
import org.delusion.chemmodities.engine.graphics.renderer.buffer.VertexBuffer;
import org.delusion.chemmodities.engine.graphics.renderer.texture.Texture;
import org.joml.Matrix4f;
import org.joml.Rectanglef;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

// Author andy
// Created 11:29 AM

/**
 * A non-batchable renderable quad meant to be used for entities and player characters. Much more dynamic at the cost of draw calls.
 * Uses its own texture to allow for animated entities without packing multiple together.
 */
public abstract class Sprite {

    protected final VertexBuffer vbo;
    protected final VertexArray vao;
    protected Vector2f position = new Vector2f();

    public Sprite(Vector2f pos) {
        position.set(pos);

        vao = new VertexArray();
        vao.bind();
        vbo = new VertexBuffer(GL_STATIC_DRAW,6);
        vao.unbind();
        vbo.unbind();
    }

    protected void setupVAO() {
        vao.pointer(0,4,GL_FLOAT,false,6 * Float.BYTES, 0);
        vao.pointer(1,2,GL_FLOAT,false,6 * Float.BYTES, 4 * Float.BYTES);
        vao.enableAttr(0);
        vao.enableAttr(1);
        vao.unbind();
        vbo.unbind();
    }

    public abstract Quad getRenderQuad();
    public abstract Texture getTexture();
    public abstract Matrix4f getModelMatrix();
    public abstract Vector2f getSize();

    public Rectanglef getBoundingBox() {
        return new Rectanglef(getPosition(),getPosition().add(getSize()));
    }

    public Vector2f getPosition() {
        return new Vector2f(position);
    }

    public void setPosition(float x, float y) {
        position.set(x,y);
    }

    public void setPosition(Vector2f vector2f) {
        position.set(vector2f);
    }

    public void buildVBO() {
        float[] data = getRenderQuad().getVertF();
        vbo.bind();
        vbo.setData(data,GL_STATIC_DRAW);
        vbo.unbind();
    }

    public void render() {
        bindVBO();
        Renderer2d.drawVBO(GL_TRIANGLES,vbo);
        unbindVBO();

    }

    public void unbindVBO() {
        vao.unbind();
        vbo.unbind();
    }

    public void bindVBO() {
        vao.bind();
        vbo.bind();
    }
}
