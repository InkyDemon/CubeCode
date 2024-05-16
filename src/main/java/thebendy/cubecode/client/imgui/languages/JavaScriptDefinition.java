package thebendy.cubecode.client.imgui.languages;

import imgui.extension.texteditor.TextEditorLanguageDefinition;

import java.util.HashMap;

public class JavaScriptDefinition {

    private static final String KEYWORD_PATTERN = "break|case|catch|const|continue|default|delete|do|else|false|finally|for|function|if|in|instanceof|let|new|null|return|switch|this|throw|try|true|typeof|var|while|with)";
    private static final String NUMBER_PATTERN = "\\b\\d+(\\.\\d+)?\\b";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'";
    private static final String COMMENT_PATTERN = "//.*?$|/\\*(?:.|\n)*?\\*/";

    @SuppressWarnings("ExtractMethodRecommender")
    public static TextEditorLanguageDefinition build() {
        String[] keywords = new String[]{
                "break", "continue", "switch", "case", "default", "try",
                "catch", "delete", "do", "while", "else", "finally", "if",
                "else", "for", "each", "in", "instanceof",
                "new", "throw", "typeof", "with", "yield", "return"
        };

        String[] secondaryKeywords = new String[]{
                "const", "function", "var", "let", "prototype", "Math", "JSON", "mappet"
        };

        String[] specials = new String[]{
                "this", "arguments"
        };

        String[] typeKeywords = new String[]{
                "true", "false", "null", "undefined"
        };

        HashMap<String, Integer> map = new HashMap<>();

        int index = 0;

        for (String secondaryKeyword : secondaryKeywords) {
            map.put(secondaryKeyword, index);
            index++;
        }

        for (String special : specials) {
            map.put(special, index);
            index++;
        }

        for (String typeKeyword : typeKeywords) {
            map.put(typeKeyword, index);
            index++;
        }

        TextEditorLanguageDefinition base = new TextEditorLanguageDefinition();

        base.setName("JavaScript");
        base.setCommentStart("/*");
        base.setCommentEnd("*/");
        base.setSingleLineComment("//");

        base.setKeywords(keywords);
        base.setTokenRegexStrings(map);

        return base;
    }

}
