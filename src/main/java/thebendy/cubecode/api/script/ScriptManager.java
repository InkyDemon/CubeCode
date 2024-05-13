package thebendy.cubecode.api.script;

import org.jetbrains.annotations.Nullable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.ScriptableObject;

import java.io.File;
import java.util.Map;

public class ScriptManager {
    private final File scriptsFolder;

    public ScriptManager(File scriptsFolder) {
        this.scriptsFolder = scriptsFolder;
    }

    public static void evalCode(String code, int line, String sourceName, @Nullable Map<String, Object> properties) throws EcmaError {
        Context runContext = Context.enter();
        runContext.setLanguageVersion(Context.VERSION_ES6);
        ScriptableObject scope = runContext.initStandardObjects();

        if (properties != null) {
            for (var property : properties.entrySet()) {
                ScriptableObject.putProperty(scope, property.getKey(), Context.javaToJS(property.getValue(), scope));
            }
        }

        try {
            runContext.evaluateString(scope, code, sourceName, line, null);
        }
        finally {
            runContext.close();
        }
    }
}