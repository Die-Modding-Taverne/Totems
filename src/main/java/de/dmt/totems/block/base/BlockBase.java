package de.dmt.totems.block.base;


import de.dmt.totems.Totems;
import de.dmt.totems.util.Log;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class BlockBase extends Block {

    public final String id;
    public final Boolean shouldRegisterItem;
    public final RegistryKey<ItemGroup> itemGroup;

    public final Identifier identifier;
    private Item registeredBlockItem;
    private Block registeredBlock;

    public BlockBase(String id, boolean shouldRegisterItem, RegistryKey<ItemGroup> itemGroup, AbstractBlock.Settings settings) {
        super(settings);
        Log.info("TEST");
        this.id = id;
        this.shouldRegisterItem = shouldRegisterItem;
        this.itemGroup = itemGroup;

        this.identifier = Identifier.of(Totems.MOD_ID, id);
        this.registeredBlock = null;
    }

    public synchronized void register() {
        if (registeredBlock != null) {
            Log.warnf("Tried to register block \"%s\" multiple times. Ignoring...", identifier.toString());
            return;
        }

        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(this, new Item.Settings());
            this.registeredBlockItem = Registry.register(Registries.ITEM, identifier, blockItem);
        }

        this.registeredBlock = Registry.register(Registries.BLOCK, identifier, this);
        registerEvents();
    }

    private void registerEvents() {
        if(registeredBlock == null) {
            Log.warnf("Tried to register Events for block \"%s\" before registering the block itself. Ignoring...", identifier.toString());
            return;
        }

        if (shouldRegisterItem) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register((itemGroup) -> itemGroup.add(registeredBlockItem));
        }
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register((itemGroup) -> itemGroup.add(registeredBlock));
    }

    public Optional<Item> getRegisteredBlockItem() {
        return Optional.ofNullable(registeredBlockItem);
    }

    public Optional<Block> getRegisteredBlock() {
        return Optional.ofNullable(registeredBlock);
    }
}
