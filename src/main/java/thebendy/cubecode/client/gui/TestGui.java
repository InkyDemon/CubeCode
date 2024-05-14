package thebendy.cubecode.client.gui;

import imgui.ImGui;
import imgui.ImGuiInputTextCallbackData;
import imgui.callback.ImGuiInputTextCallback;
import imgui.flag.ImGuiInputTextFlags;
import imgui.type.ImBoolean;
import imgui.type.ImString;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import thebendy.cubecode.client.CubeCodeClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestGui extends Screen {
    public TestGui() {
        super(Text.of("TEST"));

        CubeCodeClient.pushRenderable(this.contextRender);
    }

    private static final ImString STR = new ImString();
    private static final StringBuilder OUTPUT = new StringBuilder();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static final ImGuiInputTextCallback CALLBACK = new ImGuiInputTextCallback() {
        @Override
        public void accept(final ImGuiInputTextCallbackData data) {
            final char c = (char) data.getEventChar();
            OUTPUT.append(DATE_FORMAT.format(LocalDateTime.now())).append(" :: Typed: ").append(c).append('\n');
        }
    };

    private final Renderable contextRender = new Renderable() {
        @Override
        public String getName() {
            return TestGui.super.title.toString();
        }

        @Override
        public Theme getTheme() {
            return new Theme() {
                @Override
                public void preRender() {
                    //code...
                }

                @Override
                public void postRender() {
                    //code...
                }
            };
        }

        @Override
        public void render() {
            if (ImGui.begin("Input Text Callback Demo", new ImBoolean(true))) {
                ImGui.alignTextToFramePadding();
                ImGui.text("Try to input \"Hello World!\":");
                ImGui.sameLine();
                ImGui.inputText("##input", STR, ImGuiInputTextFlags.CallbackCharFilter, CALLBACK);
                ImGui.text(OUTPUT.toString());
            }

            ImGui.end();
        }
    };

    @Override
    public void close() {
        super.close();
        CubeCodeClient.pullRenderable(this.contextRender);
    }
}
