package thebendy.cubecode.client.imgui.views;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import thebendy.cubecode.client.imgui.ImGuiLoader;
import thebendy.cubecode.client.imgui.Renderable;
import thebendy.cubecode.client.imgui.Theme;

public class MainView implements Renderable {

    @Override
    public String getName() {
        return "Main";
    }

    @Override
    public Theme getTheme() {
        return new Theme() {
            @Override
            public void preRender() {

            }

            @Override
            public void postRender() {
                //code...
            }
        };
    }

    @Override
    public void render() {
        ImGui.setNextWindowSize(ImGui.getIO().getDisplaySizeX(), 0);
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
