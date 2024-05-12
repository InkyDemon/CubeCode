package thebendy.cubecode;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import thebendy.cubecode.content.CubeCodeCommand;

public class CubeCode implements ModInitializer {
    public static final String MOD_ID = "cubecode";

    @Override
    public void onInitialize() {
        MinecraftClient client = MinecraftClient.getInstance();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            CubeCodeCommand.register(dispatcher);
        });
    }
}