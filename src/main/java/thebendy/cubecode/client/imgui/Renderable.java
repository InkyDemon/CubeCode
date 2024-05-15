package thebendy.cubecode.client.imgui;

public interface Renderable {

    /**
     * @return optional parameter for rendering profiling
     */
    String getName();

    /**
     * @return the styling for {@link Renderable#render()}
     */
    Theme getTheme();

    /**
     * Stores the render implementation for the current task
     */
    void render();

}