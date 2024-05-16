package thebendy.cubecode.client.imgui.views;

import imgui.ImGui;
import imgui.extension.texteditor.TextEditor;
import imgui.extension.texteditor.TextEditorLanguageDefinition;
import imgui.type.ImBoolean;
import net.minecraft.util.math.random.RandomSeed;
import thebendy.cubecode.client.imgui.ImGuiLoader;
import thebendy.cubecode.client.imgui.View;
import thebendy.cubecode.client.imgui.Theme;
import thebendy.cubecode.client.imgui.languages.JavaScriptDefinition;
import thebendy.cubecode.client.imgui.themes.DefaultTheme;

public class ScriptView extends View {

    private final TextEditor CODE_EDITOR = new TextEditor();
    private final ImBoolean CLOSE = new ImBoolean(true);

    @Override
    public String getName() {
        return String.format("Script##%s", uniqueID);
    }

    @Override
    public void init() {
        ImGui.setNextWindowSize(500, 400);
        CODE_EDITOR.setPalette(JavaScriptDefinition.buildPallet());
        CODE_EDITOR.setLanguageDefinition(JavaScriptDefinition.build());
        CODE_EDITOR.setShowWhitespaces(false);
        CODE_EDITOR.setTabSize(2);
    }

    @Override
    public void render() {
        if (ImGui.begin(getName(), CLOSE)) {
            if (!CLOSE.get()) {
                ImGuiLoader.pullRenderable(this);
            }
            CODE_EDITOR.render("Code editor");
        }
        ImGui.end();
    }

}
