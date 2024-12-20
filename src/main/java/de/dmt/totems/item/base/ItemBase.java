package de.dmt.totems.item.base;

import de.dmt.totems.Totems;
import de.dmt.totems.util.Log;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public abstract class ItemBase extends Item {

    public final String id;
    public final RegistryKey<ItemGroup> itemGroup;

    public final Identifier identifier;
    private Item registeredItem;

    public ItemBase(String id, RegistryKey<ItemGroup> itemGroup, Item.Settings settings) {
        super(settings);

        this.id = id;
        this.itemGroup = itemGroup;

        this.identifier = Identifier.of(Totems.MOD_ID, id);
        this.registeredItem = null;
    }

    public synchronized void register() {
        if(registeredItem != null) {
            Log.warnf("Tried to register item \"%s\" multiple times. Ignoring...", identifier.toString());
            return;
        }

        this.registeredItem = Registry.register(Registries.ITEM, identifier, this);
        registerEvents();
    }

    private void registerEvents() {
        if(registeredItem == null) {
            Log.warnf("Tried to register events for item \"%s\" before registering the item itself. Ignoring...", identifier.toString());
            return;
        }

        ItemGroupEvents.modifyEntriesEvent(itemGroup).register((itemGroup) -> itemGroup.add(registeredItem));
    }

    public Optional<Item> getRegisteredItem() {
        return Optional.ofNullable(registeredItem);
    }
}