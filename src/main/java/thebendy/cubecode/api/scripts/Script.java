package thebendy.cubecode.api.scripts;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Script {
    public String code;

    public Script(String code) {
        this.code = code;
    }

    public void execute(String sourceName, @Nullable Map<String, Object> properties) {
        ScriptManager.evalCode(code, 0, sourceName, properties);
    }
}
