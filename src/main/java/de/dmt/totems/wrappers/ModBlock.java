package de.dmt.totems.wrappers;


import de.dmt.totems.Totems;
import de.dmt.totems.util.Log;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.Function;

public class ModBlock {

    public final String id;
    public final RegistryKey<ItemGroup> itemGroup;

    private final Function<AbstractBlock.Settings, Block> factory;
    private final AbstractBlock.Settings settings;

    public final Identifier identifier;
    public final RegistryKey<Block> registryKey;
    private Item registeredItem;
    private Block registeredBlock;

    public ModBlock(String id, RegistryKey<ItemGroup> itemGroup, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        this.id = id;
        this.itemGroup = itemGroup;

        this.factory = factory;
        this.settings = settings;

        this.identifier = Identifier.of(Totems.MOD_ID, id);
        this.registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        this.registeredItem = null;
        this.registeredBlock = null;
    }

    public synchronized void register() {
        if (registeredBlock != null) {
            Log.warnf("Tried to register block \"%s\" multiple times. Ignoring...", identifier.toString());
            return;
        }

        this.registeredBlock = Blocks.register(registryKey, factory, settings);
        this.registeredItem = Items.register(registeredBlock);
        registerEvents();
    }

    private void registerEvents() {
        if(registeredBlock == null) {
            Log.warnf("Tried to register events for block \"%s\" before registering the block itself. Ignoring...", identifier.toString());
            return;
        }

        ItemGroupEvents.modifyEntriesEvent(itemGroup).register((itemGroup) -> itemGroup.add(registeredBlock));
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register((itemGroup) -> itemGroup.add(registeredItem));
    }

    public Optional<Item> getRegisteredItem() {
        return Optional.ofNullable(registeredItem);
    }

    public Optional<Block> getRegisteredBlock() {
        return Optional.ofNullable(registeredBlock);
    }
}
