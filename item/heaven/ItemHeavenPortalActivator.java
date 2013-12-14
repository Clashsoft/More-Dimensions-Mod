package clashsoft.mods.moredimensions.item.heaven;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHeavenPortalActivator extends Item
{
	public ItemHeavenPortalActivator(int itemID)
	{
		super(itemID);
		this.maxStackSize = 1;
		this.setMaxDamage(64);
	}
	
	public void addInformation(ItemStack stack, List list)
	{
		list.add("Activates the portal to heaven");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.rare;
	}
}
