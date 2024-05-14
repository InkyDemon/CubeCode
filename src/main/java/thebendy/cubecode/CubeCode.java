package thebendy.cubecode;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.WorldSavePath;
import thebendy.cubecode.api.scripts.ScriptManager;
import thebendy.cubecode.state.ServerState;
import thebendy.cubecode.content.CubeCodeCommand;

import java.io.File;

public class CubeCode implements ModInitializer {
    public static final String MOD_ID = "cubecode";

    public static ScriptManager scriptManager;

    @Override
    public void onInitialize() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            new CubeCodeCommand(dispatcher);
        });

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            File cubecodeDir = new File(server.getSavePath(WorldSavePath.ROOT).getParent().toFile(), MOD_ID);
            cubecodeDir.mkdirs();

            scriptManager = new ScriptManager(new File(cubecodeDir, "scripts"));
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            ServerState.getServerState(server).markDirty();
        });
    }
}