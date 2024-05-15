package thebendy.cubecode.client.imgui;

import imgui.ImFontAtlas;
import imgui.ImFontConfig;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;

public class ImGuiLoader {
    private static final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    private static final ArrayList<Renderable> renderstack = new ArrayList<>();
    public static final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();

    private static long windowHandle;

    public static void onGlfwInit(long handle) {
        ImGui.createContext();
        final ImGuiIO io = ImGui.getIO();

        io.setIniFilename(null);
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard | ImGuiConfigFlags.DockingEnable);
        io.setConfigViewportsNoTaskBarIcon(true);

        final ImFontAtlas fontAtlas = io.getFonts();
        final ImFontConfig fontConfig = new ImFontConfig();

        fontConfig.setGlyphRanges(fontAtlas.getGlyphRangesCyrillic());

        fontAtlas.addFontDefault();

        fontConfig.setMergeMode(true);
        fontConfig.setPixelSnapH(true);

        fontConfig.destroy();

        if (io.hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final ImGuiStyle style = ImGui.getStyle();
            style.setWindowRounding(0.0f);
            style.setColor(ImGuiCol.WindowBg, ImGui.getColorU32(ImGuiCol.WindowBg, 1));
        }

        imGuiGlfw.init(handle,true);
        imGuiGl3.init();
        windowHandle = handle;
    }

    public static void onFrameRender() {
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        for (Renderable renderable: renderstack) {
            MinecraftClient.getInstance().getProfiler().push("ImGui Render/"+renderable.getName());
            renderable.getTheme().preRender();
            renderable.render();
            renderable.getTheme().postRender();
            MinecraftClient.getInstance().getProfiler().pop();
        }

        ImGui.render();
        endFrame(windowHandle);
    }

    private static void endFrame(long windowPtr) {
        imGuiGl3.renderDrawData(ImGui.getDrawData());

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    public static Renderable pushRenderable(Renderable renderable) {
        renderstack.add(renderable);
        return renderable;
    }

    public static Renderable pullRenderable(Renderable renderable) {
        renderstack.remove(renderable);
        return renderable;
    }
}
