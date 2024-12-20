package de.dmt.totems.wrappers;

import de.dmt.totems.Totems;
import de.dmt.totems.util.Log;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.Function;

public class ModItem {

    public final String id;
    public final RegistryKey<ItemGroup> itemGroup;

    private final Function<Item.Settings, Item> factory;
    private final Item.Settings settings;

    public final Identifier identifier;
    public final RegistryKey<Item> registryKey;
    private Item registeredItem;

    public ModItem(String id, RegistryKey<ItemGroup> itemGroup, Function<Item.Settings, Item> factory, Item.Settings settings) {
        this.id = id;
        this.itemGroup = itemGroup;

        this.factory = factory;
        this.settings = settings;

        this.identifier = Identifier.of(Totems.MOD_ID, id);
        this.registryKey = RegistryKey.of(RegistryKeys.ITEM, identifier);
        this.registeredItem = null;
    }

    public synchronized void register() {
        if (registeredItem != null) {
            Log.warnf("Tried to register item \"%s\" multiple times. Ignoring...", identifier.toString());
            return;
        }

        this.registeredItem = Items.register(registryKey, factory, settings);
        registerEvents();
    }

    private void registerEvents() {
        if(registeredItem == null) {
            Log.warnf("Tried to register Events for block \"%s\" before registering the block itself. Ignoring...", identifier.toString());
            return;
        }

        ItemGroupEvents.modifyEntriesEvent(itemGroup).register((itemGroup) -> itemGroup.add(registeredItem));
    }

    public Optional<Item> getRegisteredItem() {
        return Optional.ofNullable(registeredItem);
    }

}
