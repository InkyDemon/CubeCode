package thebendy.cubecode.client;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import thebendy.cubecode.client.gui.TestGui;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class CubeCodeCommandClient {
    public CubeCodeCommandClient(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("client").then(guiSubCommand()));
    }

    private LiteralArgumentBuilder<FabricClientCommandSource> guiSubCommand() {
        return literal("gui").executes(context -> {
            context.getSource().getClient().send(() -> context.getSource().getClient().setScreen(new TestGui()));

            return Command.SINGLE_SUCCESS;
        });
    }
}
