package thebendy.cubecode.client.imgui.languages;

import imgui.extension.texteditor.TextEditorLanguageDefinition;

import java.util.HashMap;

public class JavaScriptDefinition {

    public static TextEditorLanguageDefinition build() {
        String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'";
        String NUMBER_PATTERN = "\\b\\d+(\\.\\d+)?\\b";

        String[] keywords = new String[]{
                "break", "continue", "switch", "case", "default", "try",
                "catch", "delete", "do", "while", "else", "finally", "if",
                "else", "for", "each", "in", "instanceof",
                "new", "throw", "typeof", "with", "yield", "return"
        };

        String[] secondaryKeywords = new String[]{
                "const", "function", "var", "let", "prototype"
        };

        String[] specials = new String[]{
                "this", "arguments"
        };

        String[] typeKeywords = new String[]{
                "true", "false", "null", "undefined"
        };

        HashMap<String, Integer> regex = new HashMap<>();

        regex.put(STRING_PATTERN, 3);
        regex.put(NUMBER_PATTERN, 2);
        regex.put(collectRegex(keywords), 4);
        regex.put(collectRegex(secondaryKeywords), 4);
        regex.put(collectRegex(specials), 1);
        regex.put(collectRegex(typeKeywords), 2);

        TextEditorLanguageDefinition base = new TextEditorLanguageDefinition();

        base.setName("JavaScript");
        base.setTokenRegexStrings(regex);
        base.setCommentStart("/*");
        base.setCommentEnd("*/");
        base.setSingleLineComment("//");
        return base;
    }

    private static String collectRegex(String[] objects) {
        StringBuilder builder = new StringBuilder();
        builder.append("\\b(");
        for (String object : objects) {
            builder.append(object + "|");
        }
        builder.replace(builder.length() - 1, builder.length(), "");
        builder.append(")\\b");
        return builder.toString();
    }

    private static int c(int r, int g, int b, int a) {
        return (a << 24) | (b << 16) | (g << 8) | r;
    }

    public static int[] buildPallet() {
        return new int[]{
                /* 0  Default code */                     c(248, 248, 242, 255),
                /* 1  Keyword (custom) */                 c(180, 120, 40, 255),
                /* 2  Number */                           c(255, 0, 255, 255),
                /* 3  String */                           c(230, 219, 116, 255),
                /* 4  Char literal (custom) */            c(255,143,128, 220),
                /* 5  Punctuation */                      -1,
                /* 6  Preprocessor */                     -12550016,
                /* 7  Identifier */                       -5592406,
                /* 8  Known identifier */                 -6568371,
                /* 9  Preproc identifier */               -4177760,
                /* 10 Comment (single line) */           c(180, 180, 180, 100),
                /* 11 Comment (multi line) */            c(166, 226, 46, 200),
                /* 12 Background */                      c(39, 40, 34, 200),
                /* 13 Cursor */                          -2039584,
                /* 14 Selection */                       -2136973280,
                /* 15 ErrorMarker */                     -2147475201,
                /* 16 ControlCharacter */                1089503232,
                /* 17 Breakpoint */                      -9408512,
                /* 18 Line number */                     c(60, 60, 60, 220),
                /* 19 Current line fill */               c(80, 80, 80, 220),
                /* 20 Current line fill (inactive) */    c(100, 100, 100, 220),
                /* 21 Current line edge */               c(150, 150, 150, 220),
        };
    }

}
