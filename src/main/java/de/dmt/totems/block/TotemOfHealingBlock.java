package de.dmt.totems.block;

import de.dmt.totems.block.base.BlockBase;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.ItemGroups;
import net.minecraft.sound.BlockSoundGroup;

public class TotemOfHealingBlock extends BlockBase {

    public TotemOfHealingBlock() {
        super("totem_of_healing", false, ItemGroups.REDSTONE, AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK));
    }

}
