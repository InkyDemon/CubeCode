package thebendy.cubecode.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.mozilla.javascript.EcmaError;
import thebendy.cubecode.api.script.ScriptManager;

import java.util.HashMap;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class CubeCodeCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("cubecode").then(scriptSubCommand()));
    }

    private static LiteralArgumentBuilder<ServerCommandSource> scriptSubCommand() {
        return literal("script")
                .then(literal("eval").then(argument("script", StringArgumentType.string()).executes(context -> {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("Player", context.getSource().getPlayer().networkHandler.);
                    String code = StringArgumentType.getString(context, "script");

                    try {
                        ScriptManager.evalCode(code, 1, "eval", properties);
                    }
                    catch (Exception error) {
                        context.getSource().sendError(Text.of(error.getLocalizedMessage()));
                    }

                    return 1;
                })))
                .then(literal("exec"));
    }
}
