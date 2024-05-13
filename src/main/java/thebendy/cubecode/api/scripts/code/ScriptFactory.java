package thebendy.cubecode.api.scripts.code;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import thebendy.cubecode.api.scripts.code.blocks.ScriptBlockState;

public class ScriptFactory {
    public ScriptBlockState createBlockState(String blockId) {
        Block block = Registries.BLOCK.get(new Identifier(blockId));

        return ScriptBlockState.create(block.getDefaultState());
    }

    public String getClassName(Object value) {
        String classes = value.getClass().toString();
        int beginIndex = classes.lastIndexOf(".") + 1;

        return classes.substring(beginIndex);
    }
}
