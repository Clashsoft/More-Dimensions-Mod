package minestrapteam.extradims.item.armor;

import minestrapteam.extracore.ExtraCore;
import minestrapteam.extracore.cape.Capes;
import minestrapteam.extracore.item.ItemCustomArmor;
import minestrapteam.extradims.api.ICape;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ItemCape extends ItemCustomArmor implements ICape
{
	public static String[] capeNames = { "pro", "blue", "green", "red", "yellow", "invisibility", "minecon_2011",
		"minecon_2012", "minecon_2013" };

	static
	{
		for (String capeName : capeNames)
		{
			ResourceLocation location = new ResourceLocation("ed_aerius", "textures/capes/" + capeName + ".png");
			Capes.addCape(capeName, location);
		}
	}

	public IIcon[] icons;

	public ItemCape()
	{
		super(ArmorMaterial.CLOTH, 0, ArmorTypes.CAPE);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.icons = new IIcon[capeNames.length];
		for (int i = 0; i < capeNames.length; i++)
		{
			this.icons[i] = iconRegister.registerIcon("ed_aerius:armor/cape_" + capeNames[i]);
		}
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return this.icons[stack.getItemDamage()];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return "item.cape." + capeNames[stack.getItemDamage() % capeNames.length];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List list)
	{
		for (int i = 0; i < capeNames.length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public void updateCape(EntityPlayer player, ItemStack stack)
	{
		if (!player.worldObj.isRemote)
		{
			int metadata = stack.getItemDamage();

			if (metadata == 5)
			{
				player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 2, 0));
			}
			else
			{
				ExtraCore.getNetHandler().sendCapePacket(player, capeNames[metadata]);
			}
		}
	}
}
