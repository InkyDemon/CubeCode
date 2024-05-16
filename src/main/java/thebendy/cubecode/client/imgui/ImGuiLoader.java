package thebendy.cubecode.client.imgui;

import imgui.ImFontAtlas;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.minecraft.client.MinecraftClient;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.lwjgl.glfw.GLFW.*;

public class ImGuiLoader {

    private static final ImGuiImplGl3 IMGUI_GL3 = new ImGuiImplGl3();

    public static final ImGuiImplGlfw IMGUI_GLFW = new ImGuiImplGlfw();

    private static final ConcurrentLinkedQueue<View> RENDERSTACK = new ConcurrentLinkedQueue<>();

    public static void onGlfwInit(long handle) {
        ImGui.createContext();
        final ImGuiIO io = ImGui.getIO();
        final ImFontAtlas fontAtlas = io.getFonts();

        fontAtlas.addFontDefault();
        io.setIniFilename(null);

        IMGUI_GLFW.init(handle, true);
        IMGUI_GL3.init();
    }

    public static void onFrameRender() {
        IMGUI_GLFW.newFrame();
        ImGui.newFrame();

        RENDERSTACK.forEach(view -> {
            MinecraftClient.getInstance().getProfiler()
                    .push(String.format("Section [%s]", view.getName()));
            view.getTheme().preRender();
            view.loop();
            view.getTheme().postRender();
            MinecraftClient.getInstance().getProfiler().pop();
        });

        ImGui.render();
        endFrameRender();
    }

    private static void endFrameRender() {
        IMGUI_GL3.renderDrawData(ImGui.getDrawData());

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    public static void addRender(View view) {
        RENDERSTACK.add(view);
    }

    public static void removeRender(View view) {
        RENDERSTACK.remove(view);
    }

    public static void clearRenderStack() {
        RENDERSTACK.clear();
    }
}
