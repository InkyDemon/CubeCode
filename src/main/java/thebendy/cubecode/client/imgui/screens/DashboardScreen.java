package thebendy.cubecode.client.imgui.screens;

import imgui.*;
import imgui.flag.ImGuiConfigFlags;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import thebendy.cubecode.client.imgui.views.MainView;
import thebendy.cubecode.client.imgui.ImGuiLoader;
import thebendy.cubecode.client.imgui.View;

public class DashboardScreen extends Screen {

    public DashboardScreen() {
        super(Text.of("Dashboard"));
        ImGui.getIO().addConfigFlags(ImGuiConfigFlags.DockingEnable);
        ImGuiLoader.pushRenderable(new MainView());
    }

    @Override
    public void close() {
        super.close();
        ImGui.getIO().clearInputKeys();
        ImGuiLoader.pullRenderables(ImGuiLoader.getRenderStack().toArray(new View[]{}));
    }

}