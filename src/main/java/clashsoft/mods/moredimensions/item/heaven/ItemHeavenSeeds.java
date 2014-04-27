package clashsoft.mods.moredimensions.item.heaven;

import clashsoft.cslib.minecraft.item.CustomItem;
import clashsoft.cslib.util.CSArrays;
import clashsoft.mods.moredimensions.addons.MDMBlocks;
import clashsoft.mods.moredimensions.addons.MDMItems;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHeavenSeeds extends CustomItem
{
	public ItemHeavenSeeds(String[] names, String domain)
	{
		super(names, domain, CSArrays.create(MDMItems.tabHeavenItems));
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
			return false;
		
		Block block = world.getBlock(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		Block newBlock = null;
		int newMetadata = 0;
		
		switch (stack.getItemDamage())
		{
			case 0: // Heaven Grass Seeds
				if (block == MDMBlocks.heavenDirtBlocks && metadata == 0)
				{
					newBlock = MDMBlocks.heavenGrassBlocks;
					newMetadata = 0;
				}
			case 1: // Mud Grass Seeds
				if (block == MDMBlocks.heavenDirtBlocks && metadata == 1)
				{
					newBlock = MDMBlocks.heavenGrassBlocks;
					newMetadata = 1;
				}
			case 2: // Corrupted Grass Seeds
				if (block == Blocks.dirt)
				{
					newBlock = MDMBlocks.heavenGrassBlocks;
					newMetadata = 2;
				}
			case 3: // Hallowed Grass Seeds
				if (block == Blocks.dirt)
				{
					newBlock = MDMBlocks.heavenGrassBlocks;
					newMetadata = 3;
				}
			case 4: // Mushroom Grass Seeds
				if (block == MDMBlocks.heavenDirtBlocks && metadata == 1)
				{
					newBlock = MDMBlocks.heavenGrassBlocks;
					newMetadata = 4;
				}
		}
		
		if (newBlock != null)
		{
			world.setBlock(x, y, z, newBlock, newMetadata, 3);
			stack.stackSize--;
			return true;
		}
		return false;
	}
}