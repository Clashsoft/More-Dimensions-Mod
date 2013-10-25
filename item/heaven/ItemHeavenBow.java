package clashsoft.mods.moredimensions.item.heaven;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemHeavenBow extends ItemBow
{
	public ItemHeavenBow(int par1)
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(384);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
	/**
	 * called when the player releases the use item button. Args: itemstack,
	 * world, entityplayer, itemInUseCount
	 */
	
	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
		
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		var6 = event.charge;
		
		boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		
		if (var5 || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID))
		{
			float var7 = var6 / 20.0F;
			var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
			
			if (var7 < 0.1D)
			{
				return;
			}
			
			if (var7 > 1.0F)
			{
				var7 = 1.0F;
			}
			
			EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, var7 * 2.0F);
			if (var7 == 1.0F)
			{
				var8.setIsCritical(true);
			}
			
			int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
			
			if (var9 > 0)
			{
				var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);
			}
			
			int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
			
			if (var10 > 0)
			{
				var8.setKnockbackStrength(var10);
			}
			
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
			{
				var8.setFire(100);
			}
			
			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
			
			if (var5)
			{
				var8.canBePickedUp = 2;
			}
			else
			{
				par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
			}
			
			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(var8);
			}
		}
	}
	
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		return par1ItemStack;
	}
	
	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}
	
	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return event.result;
		}
		
		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID))
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
		
		return par1ItemStack;
	}
	
	/**
	 * Return the enchantability factor of the item, most of the time is based
	 * on material.
	 */
	@Override
	public int getItemEnchantability()
	{
		return 1;
	}
	
	/**
	 * Player, Render pass, and item usage sensitive version of getIconIndex.
	 * 
	 * @param stack
	 *            The item stack to get the icon for. (Usually this, and
	 *            usingItem will be the same if usingItem is not null)
	 * @param renderPass
	 *            The pass to get the icon for, 0 is default.
	 * @param player
	 *            The player holding the item
	 * @param usingItem
	 *            The item the player is actively using. Can be null if not
	 *            using anything.
	 * @param useRemaining
	 *            The ticks remaining for the active item.
	 * @return The icon index
	 */
	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return this.getIcon(stack, renderPass);
	}
}
