package thebendy.cubecode.client.imgui.views;

import imgui.ImGui;
import imgui.extension.texteditor.TextEditor;
import imgui.extension.texteditor.TextEditorLanguageDefinition;
import imgui.type.ImBoolean;
import thebendy.cubecode.client.imgui.ImGuiLoader;
import thebendy.cubecode.client.imgui.Renderable;
import thebendy.cubecode.client.imgui.Theme;

public class ScriptView implements Renderable {

    private final TextEditor CODE_EDITOR = new TextEditor();
    private final ImBoolean CLOSE = new ImBoolean(true);
    private final TextEditorLanguageDefinition JAVA_SCRIPT = TextEditorLanguageDefinition.angelScript();
    private final String KEYWORD_PATTERN = "break|case|catch|const|continue|default|delete|do|else|false|finally|for|function|if|in|instanceof|let|new|null|return|switch|this|throw|try|true|typeof|var|while|with)";
    private final String NUMBER_PATTERN = "\\b\\d+(\\.\\d+)?\\b";
    private final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'";
    private final String COMMENT_PATTERN = "//.*?$|/\\*(?:.|\n)*?\\*/";

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
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
        if (ImGui.begin(getName(), CLOSE)) {
            if (!CLOSE.get()) {
                ImGuiLoader.pullRenderable(this);
            }

            String[] keywords = new String[]{"break", "case", "catch", "const", "continue", "default", "delete", "do", "else", "false", "finally", "for", "function", "if", "in", "instanceof", "let", "new", "null", "return", "switch", "this", "throw", "try", "true", "typeof", "var", "while", "with"};
            JAVA_SCRIPT.setName("JavaScript");
            JAVA_SCRIPT.setKeywords(keywords);

            CODE_EDITOR.setLanguageDefinition(JAVA_SCRIPT);
            CODE_EDITOR.setShowWhitespaces(false);
            CODE_EDITOR.setTabSize(2);

            CODE_EDITOR.render("Code editor");
        }
        ImGui.end();
    }

}
