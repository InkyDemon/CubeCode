package thebendy.cubecode.api.script.code;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import thebendy.cubecode.api.script.code.blocks.ScriptBlockState;

public class ScriptFactory {
    public ScriptBlockState createBlockState(String blockId, int meta) {
        Block block = Registries.BLOCK.get(new Identifier(blockId));

        return ScriptBlockState.create(block.getDefaultState());
    }
}
