package thebendy.cubecode.client.gui;

public interface Renderable {
    String getName();
    Theme getTheme();

    void render();
}