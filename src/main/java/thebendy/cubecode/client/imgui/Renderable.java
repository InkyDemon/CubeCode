package thebendy.cubecode.client.imgui;

public interface Renderable {
    String getName();
    Theme getTheme();

    void render();
}