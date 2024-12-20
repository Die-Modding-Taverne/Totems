package de.dmt.totems.system;

import de.dmt.totems.block.TotemOfHealingBlock;
import de.dmt.totems.util.Log;
import de.dmt.totems.wrappers.ModBlock;

import java.util.List;

public class BlockSystem extends SystemBase {

    public static final TotemOfHealingBlock TOTEM_OF_HEALING_BLOCK = new TotemOfHealingBlock();

    public static final List<ModBlock> BLOCKS = List.of(
            TOTEM_OF_HEALING_BLOCK
    );

    public BlockSystem() {
        super("Blocks");
    }

    @Override
    protected void doInitialize() throws Exception {
        for (ModBlock block : BLOCKS) {
            Log.infof("Registering block: \"%s\"", block.identifier.toString());
            block.register();
        }
    }
}
