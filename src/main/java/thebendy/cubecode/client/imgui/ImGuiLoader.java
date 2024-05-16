package thebendy.cubecode.client.imgui;

import com.google.common.collect.ConcurrentHashMultiset;
import imgui.ImFontAtlas;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.minecraft.client.MinecraftClient;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class ImGuiLoader {

    private static final ImGuiImplGl3 IMGUI_GL3 = new ImGuiImplGl3();

    public static final ImGuiImplGlfw IMGUI_GLFW = new ImGuiImplGlfw();

    private static final ConcurrentHashMultiset<Renderable> RENDERSTACK = ConcurrentHashMultiset.create();

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

        RENDERSTACK.forEach(renderable -> {
            MinecraftClient.getInstance().getProfiler()
                    .push(String.format("Section [%s]", renderable.getName()));
            renderable.getTheme().preRender();
            renderable.render();
            renderable.getTheme().postRender();
            MinecraftClient.getInstance().getProfiler().pop();
        });

        ImGui.render();
        endFrame();
    }

    private static void endFrame() {
        IMGUI_GL3.renderDrawData(ImGui.getDrawData());

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    public static ConcurrentHashMultiset<Renderable> getRenderStack() {
        return RENDERSTACK;
    }

    public static void pushRenderable(Renderable renderable) {
        RENDERSTACK.add(renderable);
    }

    public static void pushRenderables(Renderable... renderables) {
        RENDERSTACK.addAll(Arrays.asList(renderables));
    }

    public static void pullRenderable(Renderable renderable) {
        RENDERSTACK.remove(renderable);
    }

    public static void pullRenderables(Renderable... renderables) {
        RENDERSTACK.removeAll(Arrays.asList(renderables));
    }

}
