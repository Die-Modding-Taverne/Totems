package de.dmt.totems.system;

import de.dmt.totems.util.Log;
import de.dmt.totems.wrappers.ModItem;

import java.util.List;

public class ItemSystem extends SystemBase {

    public static final List<ModItem> ITEMS = List.of();

    public ItemSystem() {
        super("Items");
    }

    @Override
    protected void doInitialize() throws Exception {
        for (ModItem item : ITEMS) {
            Log.infof("Registering item: \"%s\"", item.identifier.toString());
            item.register();
        }
    }
}
