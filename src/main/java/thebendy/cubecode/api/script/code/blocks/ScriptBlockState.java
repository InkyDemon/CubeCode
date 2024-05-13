package thebendy.cubecode.api.script.code.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import thebendy.cubecode.api.script.code.ScriptWorld;

public class ScriptBlockState {
    public static final BlockState AIR = Blocks.AIR.getDefaultState();

    private BlockState blockState;

    public static ScriptBlockState create(BlockState blockState)
    {
        if (blockState == AIR || blockState == null) {
            return new ScriptBlockState(AIR);
        }

        return new ScriptBlockState(blockState);
    }

    private ScriptBlockState(BlockState state) {
        this.blockState = state;
    }

    public BlockState getMinecraftBlockState() {
        return this.blockState;
    }

    public String getBlockId() {
        return Registries.BLOCK.getId(this.blockState.getBlock()).toString();
    }

    public boolean isSame(ScriptBlockState state) {
        return this.equals(state);
    }

    public boolean isSameBlock(ScriptBlockState state) {
        return this.blockState.getBlock() == state.getMinecraftBlockState().getBlock();
    }

    public boolean isOpaque() {
        return this.blockState.isOpaque();
    }

    public boolean hasCollision(ScriptWorld world, int x, int y, int z) {
        return this.blockState.getCollisionShape(world.getMinecraftWorld(), new BlockPos(x, y, z)) != null;
    }

    public boolean isAir() {
        return this.isSame(new ScriptBlockState(AIR));
    }
}
