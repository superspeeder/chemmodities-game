package org.delusion.chemmodities.engine.graphics.window;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;
import org.joml.Vector2d;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final int width;
    private final int height;
    private final String caption;
    private final long window;

    public Window(int width, int height, String caption) {
        this.width = width;
        this.height = height;
        this.caption = caption;

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(width,height,caption, NULL, NULL);
        if (window == 0) throw new IllegalStateException("Window is null");
        glfwMakeContextCurrent(window);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getCaption() {
        return caption;
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public void swapInterval(int interval) {
        glfwSwapInterval(interval);
    }

    public boolean isKeyPressed(int key) {
        return glfwGetKey(window,key) == GLFW_PRESS;
    }

    public boolean isButtonPressed(int button) {
        return glfwGetMouseButton(window, button) == GLFW_PRESS;
    }

    public Vector2d getMousePosition() {
        DoubleBuffer mx = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer my = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(window, mx, my);
        mx.rewind();
        my.rewind();

        return new Vector2d(mx.get(),my.get());
    }

    public void setMousePosition(double x, double y) {
        glfwSetCursorPos(window, x, y);
    }

    public void minimize() {
        glfwIconifyWindow(window);
    }

    public void maximize() {
        glfwMaximizeWindow(window);
    }

    public String getClipboard() {
        return glfwGetClipboardString(window);
    }

    public void setClipboard(String content) {
        glfwSetClipboardString(window, content);
    }

    public double getTime() {
        return glfwGetTime();
    }

    public boolean shouldClose() { return glfwWindowShouldClose(window); }

    public void terminate() { glfwTerminate(); }
}
