package thebendy.cubecode.api.script.code;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thebendy.cubecode.api.script.code.blocks.ScriptBlockState;

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

    public void setBlock(ScriptBlockState scriptBlockState, int x, int y, int z) {
        this.world.setBlockState(new BlockPos(x, y, z), scriptBlockState.getMinecraftBlockState());
    }
}
