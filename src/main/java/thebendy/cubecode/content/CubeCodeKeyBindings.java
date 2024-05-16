package thebendy.cubecode.content;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import thebendy.cubecode.client.imgui.screens.DashboardScreen;

import java.util.Arrays;

public final class CubeCodeKeyBindings {

    public static final String CUBECODE_CATEGORY = "CubeCode";
    public static final KeyBinding SCRIPTS_MENU = new KeyBinding("Scripts Menu", GLFW.GLFW_KEY_EQUAL, CUBECODE_CATEGORY);

    public static void init() {
        registerKeyBindings(SCRIPTS_MENU);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (SCRIPTS_MENU.wasPressed()) {
                client.setScreen(new DashboardScreen());
            }
        });
    }

    private static void registerKeyBindings(KeyBinding... keyBindings) {
        Arrays.stream(keyBindings).forEach(KeyBindingHelper::registerKeyBinding);
    }

}
