package de.dmt.totems;

import de.dmt.totems.system.BlockSystem;
import de.dmt.totems.system.ItemSystem;
import de.dmt.totems.system.SystemBase;
import de.dmt.totems.util.Log;
import de.dmt.totems.util.StringUtil;
import net.fabricmc.api.ModInitializer;

import java.util.List;

public class Totems implements ModInitializer {
    public static final String MOD_ID = "totems";

    public static final ItemSystem ITEM_SYSTEM = new ItemSystem();
    public static final BlockSystem BLOCK_SYSTEM = new BlockSystem();
    public static final List<SystemBase> SYSTEMS = List.of(
            ITEM_SYSTEM,
            BLOCK_SYSTEM
    );

    @Override
    public void onInitialize() {
        for (SystemBase systemBase : SYSTEMS) {
            try {
                systemBase.initialize();
            } catch (Exception exception) {
                Log.errorf("Error while initializing system \"%s\":\n%s", systemBase.name, StringUtil.fromException(exception));
                throw new RuntimeException(exception);
            }
        }
    }
}
