package thebendy.cubecode.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import thebendy.cubecode.CubeCode;
import thebendy.cubecode.api.script.ScriptManager;

import java.util.HashMap;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class CubeCodeCommand {
    public CubeCodeCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("cubecode").then(scriptSubCommand()));
    }

    private LiteralArgumentBuilder<ServerCommandSource> scriptSubCommand() {
        return literal("script")
                .then(literal("eval").then(argument("script", MessageArgumentType.message()).executes(context -> {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("Player", context.getSource().getPlayer());
                    String code = MessageArgumentType.getMessage(context, "script").getString();

                    try {
                        ScriptManager.evalCode(code, 1, "eval", properties);
                    }
                    catch (Exception error) {
                        context.getSource().sendError(Text.of(error.getLocalizedMessage()));
                    }

                    return 1;
                })))
                .then(literal("exec").then(argument("scriptId", MessageArgumentType.message()).executes(context -> {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("Player", context.getSource().getPlayer());
                    String scriptId = MessageArgumentType.getMessage(context, "scriptId").getString();

                    try {
                        CubeCode.scriptManager.updateScripts();
                        CubeCode.scriptManager.executeScript(scriptId, properties);
                    }
                    catch (Exception error) {
                        context.getSource().sendError(Text.of(error.getLocalizedMessage()));
                    }

                    return 1;
                })));
    }
}
