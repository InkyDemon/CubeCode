package thebendy.cubecode.api.script.code.entities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ScriptPlayer extends ScriptEntity<PlayerEntity> {
    protected ScriptPlayer(PlayerEntity entity) {
        super(entity);
    }

    public PlayerEntity getMinecraftPlayer() {
        return this.entity;
    }

    public void send(String message) {
        this.entity.sendMessage(Text.of(message));
    }

    @Override
    public void setRotations(float pitch, float yaw, float headYaw) {
        ((ServerPlayerEntity)this.entity).networkHandler.requestTeleport(
                this.getPosition().x,
                this.getPosition().y,
                this.getPosition().z,
                pitch,
                yaw
        );
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        super.setVelocity(x, y, z);
        ((ServerPlayerEntity)this.entity).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(this.entity));
    }

    @Override
    public void addVelocity(double x, double y, double z) {
        super.addVelocity(x, y, z);
        ((ServerPlayerEntity)this.entity).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(this.entity));
    }
}
