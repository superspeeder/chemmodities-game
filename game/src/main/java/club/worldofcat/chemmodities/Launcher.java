package club.worldofcat.chemmodities;

import org.delusion.chemmodities.engine.application.ApplicationSettings;

public class Launcher {

    public static void main(String[] args) {
        ApplicationSettings settings = new ApplicationSettings();
        settings.width = 800;
        settings.height = 800;
        settings.title = "Hello";

        new ChemmoditiesApplication(settings).launch();
    }
}
