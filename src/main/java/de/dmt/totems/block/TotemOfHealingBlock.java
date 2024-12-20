package de.dmt.totems.block;

import de.dmt.totems.wrappers.ModBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;
import net.minecraft.sound.BlockSoundGroup;

public class TotemOfHealingBlock extends ModBlock {

    public TotemOfHealingBlock() {
        super("totem_of_healing", ItemGroups.REDSTONE, Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK));
    }

}
