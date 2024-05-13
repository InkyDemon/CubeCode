package thebendy.cubecode.api.scripts.code;

import com.mojang.brigadier.StringReader;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.EntitySelectorReader;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thebendy.cubecode.api.scripts.code.blocks.ScriptBlockState;
import thebendy.cubecode.api.scripts.code.entities.ScriptEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScriptWorld {
    private World world;

    public ScriptWorld(World world) {
        this.world = world;
    }

    public World getMinecraftWorld() {
        return this.world;
    }

    public ScriptBlockState getBlock(int x, int y, int z) {
        return ScriptBlockState.create(this.world.getBlockState(new BlockPos(x, y, z)));
    }

    public ScriptBlockState getBlock(ScriptVector vector) {
        return ScriptBlockState.create(this.world.getBlockState(vector.toBlockPos()));
    }

    public void setBlock(ScriptBlockState scriptBlockState, int x, int y, int z) {
        this.world.setBlockState(new BlockPos(x, y, z), scriptBlockState.getMinecraftBlockState());
    }

    public ScriptServer getServer() {
        return new ScriptServer(this.world.getServer());
    }

    public List<ScriptEntity<?>> getEntities(String selector) {
        List<ScriptEntity<?>> entities = new ArrayList<>();
        try {
            EntitySelector entitySelector = new EntitySelectorReader(new StringReader(selector)).read();

            entitySelector.getEntities(this.world.getServer().getCommandSource()).forEach((entity -> entities.add(ScriptEntity.create(entity))));
        } catch (Exception ignored) {}

        return entities;
    }

    public ScriptEntity<?> getEntity(String uuid) {
        return ScriptEntity.create(((ServerWorld)this.world).getEntity(UUID.fromString(uuid)));
    }
}