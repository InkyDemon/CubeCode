package thebendy.cubecode.content;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import thebendy.cubecode.api.scripts.code.ScriptFactory;
import thebendy.cubecode.api.scripts.code.ScriptServer;
import thebendy.cubecode.api.scripts.code.ScriptWorld;
import thebendy.cubecode.api.scripts.code.entities.ScriptEntity;
import thebendy.cubecode.CubeCode;
import thebendy.cubecode.api.scripts.ScriptManager;
import thebendy.cubecode.utils.CubeCodeException;

import java.util.HashMap;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class CubeCodeCommand {
    public CubeCodeCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("cubecode").then(scriptSubCommand()));
    }

    private LiteralArgumentBuilder<ServerCommandSource> scriptSubCommand() {
        return literal("script")
                .then(literal("eval").then(argument("code", MessageArgumentType.message()).executes(context -> {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("Player", ScriptEntity.create(context.getSource().getPlayer()));
                    properties.put("Server", new ScriptServer(context.getSource().getServer()));
                    properties.put("World", new ScriptWorld(context.getSource().getWorld()));
                    properties.put("CubeCode", new ScriptFactory());
                    String code = MessageArgumentType.getMessage(context, "code").getString();

                    try {
                        ScriptManager.evalCode(code, 1, "eval", properties);
                    }
                    catch (CubeCodeException exception) {
                        context.getSource().sendError(Text.of(exception.getLocalizedMessage()));
                    }

                    return Command.SINGLE_SUCCESS;
                })))
                .then(literal("exec").then(argument("scriptId", MessageArgumentType.message()).executes(context -> {
                    HashMap<String, Object> properties = new HashMap<>();
                    properties.put("Player", ScriptEntity.create(context.getSource().getPlayer()));
                    properties.put("Server", new ScriptServer(context.getSource().getServer()));
                    properties.put("World", new ScriptWorld(context.getSource().getWorld()));
                    properties.put("CubeCode", new ScriptFactory());
                    String scriptId = MessageArgumentType.getMessage(context, "scriptId").getString();

                    try {
                        CubeCode.scriptManager.updateScripts();
                        CubeCode.scriptManager.executeScript(scriptId, properties);
                    }
                    catch (CubeCodeException exception) {
                        context.getSource().sendError(Text.of(exception.getLocalizedMessage()));
                    }

                    return Command.SINGLE_SUCCESS;
                })));
    }
}
