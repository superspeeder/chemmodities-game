package org.delusion.chemmodities.engine.application;

import org.delusion.chemmodities.engine.graphics.renderer.Renderer2d;
import org.delusion.chemmodities.engine.graphics.window.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.stb.STBImage;

public abstract class Application {

    private ApplicationSettings settings;
    protected Window window;
    protected Renderer2d renderer;

    public Application(ApplicationSettings settings) {

        this.settings = settings;
        renderer = new Renderer2d();
    }

    public void launch() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("GLFW couldnt be initialized");
        }
        STBImage.stbi_set_flip_vertically_on_load(true);

        window = new Window(settings.width,settings.height,settings.title);
        GL.createCapabilities(true);

        create();

        while (!window.shouldClose()) {
            window.pollEvents();

            render();

            window.swapBuffers();
        }
    }

    protected abstract void create();
    protected abstract void render();

}
