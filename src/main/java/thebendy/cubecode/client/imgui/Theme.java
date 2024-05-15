package thebendy.cubecode.client.imgui;

public interface Theme {

    /**
     * Pre initialization {@link imgui.ImGui} styles
     */
    void preRender();

    /**
     * Post initialization {@link imgui.ImGui} styles
     */
    void postRender();

}
