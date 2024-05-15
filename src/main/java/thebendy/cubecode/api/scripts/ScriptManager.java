package thebendy.cubecode.api.scripts;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScriptManager {
    private Map<String, Script> scripts = new HashMap<>();
    private final File scriptsFolder;

    public ScriptManager(File scriptsFolder) {
        this.scriptsFolder = scriptsFolder;
        this.scriptsFolder.mkdirs();

        updateScripts();
    }

    public void updateScripts() {
        Map<String, Script> newScripts = new HashMap<>();

        Arrays.stream(scriptsFolder.listFiles()).forEach(file -> {
            try {
                newScripts.put(file.getName(), new Script(FileUtils.readFileToString(file, Charset.defaultCharset())));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        scripts = newScripts;
    }

    public void executeScript(String scriptId, @Nullable Map<String, Object> properties) {
        scripts.get(scriptId).execute(scriptId, properties);
    }

    public static void evalCode(String code, int line, String sourceName, @Nullable Map<String, Object> properties) {
        Context runContext = Context.enter();
        runContext.setLanguageVersion(Context.VERSION_ES6);
        ScriptableObject scope = runContext.initStandardObjects();

        if (properties != null) {
            for (var property : properties.entrySet()) {
                ScriptableObject.putProperty(scope, property.getKey(), Context.javaToJS(property.getValue(), scope));
            }
        }

        runContext.evaluateString(scope, code, sourceName, line, null);
        runContext.close();
    }
}