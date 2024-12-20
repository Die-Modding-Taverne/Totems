package de.dmt.totems.system;

import de.dmt.totems.item.base.ItemBase;
import de.dmt.totems.util.Log;

import java.util.List;

public class ItemSystem extends SystemBase {

    public static final List<ItemBase> ITEMS = List.of();

    public ItemSystem() {
        super("Items");
    }

    @Override
    protected void doInitialize() throws Exception {
        for (ItemBase item : ITEMS) {
            Log.infof("Registering item: \"%s\"", item.identifier.toString());
            item.register();
        }
    }
}
