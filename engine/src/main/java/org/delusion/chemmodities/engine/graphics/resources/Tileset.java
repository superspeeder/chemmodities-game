package org.delusion.chemmodities.engine.graphics.resources;

import org.delusion.chemmodities.engine.graphics.renderer.texture.Texture;
import org.joml.Vector4f;

import java.io.IOException;

// Author andy
// Created 8:42 PM
public class Tileset {

    private Texture texture;
    private int tile_size;

    private int tiles_per_row;
    private int rows;
    private float tile_u,tile_v;

    public Tileset(int tile_size, String path) throws IOException {
        this.tile_size = tile_size;
        this.texture = new Texture(path);
        tiles_per_row = texture.getWidth() / tile_size;
        rows = (int)Math.ceil(texture.getHeight() / (float)tile_size);

        tile_u = texture.getWidth()/(float)tile_size;
        tile_v = texture.getHeight()/(float)tile_size;
    }

    public Tileset(int tile_size, Texture mainTs) {
        this.tile_size = tile_size;
        this.texture = mainTs;

        tiles_per_row = texture.getWidth() / tile_size;
        rows = (int)Math.ceil(texture.getHeight() / (float)tile_size);

        tile_u = texture.getWidth()/(float)tile_size;
        tile_v = texture.getHeight()/(float)tile_size;
    }

    public Vector4f getTileUV(int id) {
        int row = id / tiles_per_row;
        int col = id % tiles_per_row;
        if (id > tiles_per_row * rows) {
            throw new IllegalArgumentException("ID is greater than max ids");
        }

        return texture.uvs(col*tile_size,row*tile_size,(col+1)*tile_size,(row+1)*tile_size);
    }

    public Texture getTexture() {
        return texture;
    }

    public int getTS() {
        return tile_size;
    }
}
