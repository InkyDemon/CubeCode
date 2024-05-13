package thebendy.cubecode.api.scripts;

import org.jetbrains.annotations.Nullable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScriptManager {
    private final Map<String, File> scripts = new HashMap<>();
    private final File scriptsFolder;

    public ScriptManager(File scriptsFolder) {
        this.scriptsFolder = scriptsFolder;
        this.scriptsFolder.mkdirs();

        this.updateScripts();
    }

    public void executeScript(String scriptId, @Nullable Map<String, Object> properties) throws Exception {
        String code = Files.readString(scripts.get(scriptId).toPath(), Charset.defaultCharset());

        evalCode(code, 0, scriptId, properties);
    }

    public void updateScripts() {
        Arrays.stream(scriptsFolder.listFiles()).forEach(file -> {
            if (file.getName().endsWith(".js")) {
                scripts.put(file.getName(), file);
            }
        });
    }

    public static void evalCode(String code, int line, String sourceName, @Nullable Map<String, Object> properties) throws Exception {
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