package club.worldofcat.chemmodities;

import org.delusion.chemmodities.engine.application.Application;
import org.delusion.chemmodities.engine.application.ApplicationSettings;
import org.delusion.chemmodities.engine.graphics.camera.Camera;
import org.delusion.chemmodities.engine.graphics.renderer.shader.ShaderProgram;
import org.delusion.chemmodities.engine.graphics.renderer.texture.Texture;
import org.delusion.chemmodities.engine.graphics.sprite.BasicSprite;
import org.delusion.chemmodities.engine.utils.GlUtils;
import org.joml.Vector2f;
import org.lwjgl.Version;
import org.lwjgl.system.SharedLibrary;

public class ChemmoditiesApplication extends Application {
    private BasicSprite sprite0,sprite1;
    private Texture tex0,tex1;
    private ShaderProgram shader;
    private Camera camera;

    public ChemmoditiesApplication(ApplicationSettings settings) {
        super(settings);

    }


    @Override
    protected void create() {

        GlUtils.enableDebug();
        renderer.fillColor(1,1,0,1);

        shader = new ShaderProgram("shaders/vertex.glsl", "shaders/fragment.glsl");

        tex0 = new Texture("textures/testimage0.png");
        sprite0 = new BasicSprite(new Vector2f(0,0),new Vector2f(400,800), tex0);

        tex1 = new Texture("textures/testimage1.png");
        sprite1 = new BasicSprite(new Vector2f(400,0),new Vector2f(400,800), tex1);

        camera = new Camera(800,800,1);
        camera.update();

    }

    @Override
    protected void render() {
        renderer.fillScreen();

        shader.use();
        shader.uniformMat4("mvp", camera.getCombined().mul(sprite0.getModelMatrix()));
        sprite0.render();
        shader.uniformMat4("mvp", camera.getCombined().mul(sprite1.getModelMatrix()));
        sprite1.render();
    }
}
