package thebendy.cubecode.api.scripts.code.entities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import thebendy.cubecode.api.scripts.code.ScriptVector;
import thebendy.cubecode.api.scripts.code.ScriptWorld;

public class ScriptPlayer extends ScriptEntity<PlayerEntity> {
    public ScriptPlayer(PlayerEntity entity) {
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

    public void executeCommand(String command) {
        this.entity.getServer().getCommandManager().executeWithPrefix(this.entity.getCommandSource(), command);
    }

    public String getGameMode() {
        return ((ServerPlayerEntity)this.entity).interactionManager.getGameMode().getName();
    }

    public void setGameMode(String gameMode) {
        ((ServerPlayerEntity)this.entity).changeGameMode(GameMode.byName(gameMode));
    }

    public void setSpawnPoint() {
        ((ServerPlayerEntity)this.entity).setSpawnPoint(this.entity.getWorld().getRegistryKey(), this.entity.getBlockPos(), 0, true, false);
    }

    public void setSpawnPoint(int x, int y, int z, float angle) {
        ((ServerPlayerEntity)this.entity).setSpawnPoint(this.entity.getWorld().getRegistryKey(), new BlockPos(x, y, z), angle, true, false);
    }

    public void setSpawnPoint(ScriptWorld world, int x, int y, int z, float angle) {
        ((ServerPlayerEntity)this.entity).setSpawnPoint(world.getMinecraftWorld().getRegistryKey(), new BlockPos(x, y, z), angle, true, false);
    }

    public ScriptVector getSpawnPoint() {
        BlockPos pos = ((ServerPlayerEntity)this.entity).getSpawnPointPosition();
        return new ScriptVector(pos == null ? this.entity.getWorld().getSpawnPos() : pos);
    }

    public boolean isFlying() {
        return this.entity.getAbilities().flying;
    }

    public void setFlySpeed(float flySpeed) {
        this.entity.getAbilities().setFlySpeed(flySpeed);
    }

    public float getWalkSpeed() {
        return this.entity.getAbilities().getWalkSpeed();
    }

    public void setWalkSpeed(float walkSpeed) {
        this.entity.getAbilities().setWalkSpeed(walkSpeed);
    }

    public void resetWalkSpeed() {
        this.setWalkSpeed(0.1F);
    }
}