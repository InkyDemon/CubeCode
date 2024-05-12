package thebendy.cubecode.api.script.code.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import thebendy.cubecode.api.script.code.ScriptVector;

public class ScriptEntity<T extends Entity> {
    protected T entity;

    public static ScriptEntity create(Entity entity) {
        if (entity instanceof PlayerEntity) {
            return new ScriptPlayer((PlayerEntity) entity);
        } else if (entity != null) {
            return new ScriptEntity<Entity>(entity);
        }

        return null;
    }

    protected ScriptEntity(T entity) {
        this.entity = entity;
    }

    public T getMinecraftEntity() {
        return this.entity;
    }

    public void setPosition(double x, double y, double z) {
        this.entity.setPosition(x, y, z);
        if (this.entity instanceof LivingEntity) {
            this.entity.teleport(x, y, z);
        }
    }

    public ScriptVector getPosition() {
        return new ScriptVector(
                this.entity.getX(),
                this.entity.getY(),
                this.entity.getZ()
        );
    }

    public void setRotations(float pitch, float yaw, float headYaw) {
        this.entity.setPitch(pitch);
        this.entity.setYaw(yaw);
        this.entity.setHeadYaw(headYaw);
    }

    public void setRotations(float pitch, float yaw, float headYaw, float bodyYaw) {
        this.setRotations(pitch, yaw, headYaw);
        this.entity.setBodyYaw(bodyYaw);
    }

    public ScriptVector getRotations() {
        return new ScriptVector(
                this.entity.getPitch(),
                this.entity.getYaw(),
                this.entity.getHeadYaw()
        );
    }

    public float getBodyYaw() {
        return this.entity.getBodyYaw();
    }

    public void addVelocity(double x, double y, double z) {
        this.entity.addVelocity(x, y, z);
    }

    public void setVelocity(double x, double y, double z) {
        this.entity.setVelocity(x, y, z);
    }

    public ScriptVector getVelocity(double x, double y, double z) {
        return new ScriptVector(this.entity.getVelocity());
    }

    public void kill() {
        this.entity.kill();
    }
}
