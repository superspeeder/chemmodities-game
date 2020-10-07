package org.delusion.chemmodities.engine.graphics.sprite;

import org.delusion.chemmodities.engine.graphics.renderer.Quad;
import org.delusion.chemmodities.engine.graphics.renderer.Renderer2d;
import org.delusion.chemmodities.engine.graphics.renderer.texture.Texture;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL46.*;

public class BasicSprite extends Sprite {
    private Quad quad;
    private Vector2f size;
    private Texture texture;

    public BasicSprite(Vector2f pos, Vector2f size, Texture texture) {
        super(pos);
        this.size = size;
        this.texture = texture;
        quad = new Quad((int)pos.x,(int)pos.y,(int)size.x,(int)size.y,new Vector4f(0,0,1,1));
        vao.bind();
        vbo.bind();
        vbo.setData(quad.getVertF(),GL_STATIC_DRAW);
        setupVAO();
        vbo.unbind();
    }

    @Override
    public Quad getRenderQuad() {
        return quad;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Matrix4f getModelMatrix() {
        return quad.modelMat();
    }

    @Override
    public Vector2f getSize() {
        return size;
    }

    @Override
    public void render() {
        bindVBO();
        getTexture().bind(0);
        Renderer2d.drawVBO(GL_TRIANGLES, vbo);
        unbindVBO();
    }
}
