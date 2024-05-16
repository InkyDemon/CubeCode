package thebendy.cubecode.client.imgui.views;

import imgui.ImGui;
import imgui.internal.flag.ImGuiDockNodeFlags;
import thebendy.cubecode.client.imgui.ImGuiLoader;
import thebendy.cubecode.client.imgui.View;

public class MainView extends View {
    @Override
    public void render() {
        if (ImGui.beginMainMenuBar()) {
            if (ImGui.beginMenu("Windows")) {
                 if (ImGui.menuItem("Add ScriptView")) {
                     ImGuiLoader.pushRenderable(new ScriptView());
                 }
                 ImGui.endMenu();
            }
        }
        ImGui.endMainMenuBar();

        ImGui.dockSpaceOverViewport(ImGui.getWindowViewport(), ImGuiDockNodeFlags.PassthruCentralNode | ImGuiDockNodeFlags.NoCentralNode);
    }
}
