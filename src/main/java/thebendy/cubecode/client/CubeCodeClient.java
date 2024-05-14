package thebendy.cubecode.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import thebendy.cubecode.client.gui.Renderable;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class CubeCodeClient implements ClientModInitializer {
    public static ArrayList<Renderable> renderstack = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            new CubeCodeCommandClient(dispatcher);
        });
    }

    public static Renderable pushRenderable(Renderable renderable) {
        renderstack.add(renderable);
        return renderable;
    }

    public static Renderable pullRenderable(Renderable renderable) {
        renderstack.remove(renderable);
        return renderable;
    }
}
