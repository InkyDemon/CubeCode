package thebendy.cubecode.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thebendy.cubecode.client.imgui.ImGuiLoader;

@Mixin(value = RenderSystem.class, remap = false)
public abstract class RenderSystemImGuiMixin {
    @Inject(at = @At("HEAD"), method="flipFrame")
    private static void runTickTail(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().push("ImGui Render");
        ImGuiLoader.onFrameRender();
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
