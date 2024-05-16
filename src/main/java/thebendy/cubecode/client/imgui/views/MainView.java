package thebendy.cubecode.client.imgui.views;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import thebendy.cubecode.client.imgui.ImGuiLoader;
import thebendy.cubecode.client.imgui.View;
import thebendy.cubecode.client.imgui.Theme;
import thebendy.cubecode.client.imgui.themes.DefaultTheme;

public class MainView extends View {

    @Override
    public void render() {
        ImGui.setNextWindowSize(ImGui.getIO().getDisplaySizeX(), -10);
        ImGui.setNextWindowPos(0, 0);

        if (ImGui.begin(getName(), ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoDecoration | ImGuiWindowFlags.MenuBar)) {
            if (ImGui.beginMenuBar()) {
                if (ImGui.beginMenu("Menu")) {
                    if (ImGui.menuItem("Add Script Menu")) {
                        ImGuiLoader.pushRenderable(new ScriptView());
                    }

                    ImGui.endMenu();
                }

                ImGui.endMenuBar();
            }
            ImGui.end();
        }
    }
}
