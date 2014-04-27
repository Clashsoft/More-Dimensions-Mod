package clashsoft.mods.moredimensions.item.tools;

import clashsoft.mods.moredimensions.addons.MDMItems;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSwordMDM extends Item
{
	public float				weaponDamage;
	public final ToolMaterial	toolMaterial;
	public float				efficiencyOnProperMaterial	= 1F;
	
	public ItemSwordMDM(float weaponDamage, ToolMaterial material)
	{
		this(weaponDamage, 1F, material);
	}
	
	public ItemSwordMDM(float weaponDamage, float efficiencyMultiplier, ToolMaterial material)
	{
		super();
		this.maxStackSize = 1;
		this.toolMaterial = material;
		this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial() * efficiencyMultiplier;
		
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(MDMItems.tabTools);
		this.weaponDamage = weaponDamage + material.getDamageVsEntity();
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if (block == Blocks.web)
		{
			return 15.0F;
		}
		else
		{
			Material mat = block.getMaterial();
			if (mat != Material.plants && mat != Material.vine && mat != Material.coral && mat != Material.leaves && mat != Material.gourd)
			{
				return this.efficiencyOnProperMaterial * 1.5F;
			}
			return this.efficiencyOnProperMaterial;
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase attacker, EntityLivingBase living)
	{
		stack.damageItem(1, living);
		return true;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		if (block.getBlockHardness(world, x, y, z) != 0.0D)
		{
			stack.damageItem(2, living);
		}
		
		return true;
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}
	
	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		return block == Blocks.web;
	}
	
	@Override
	public int getItemEnchantability()
	{
		return this.toolMaterial.getEnchantability();
	}
	
	public String getToolMaterialName()
	{
		return this.toolMaterial.toString();
	}
	
	@Override
	public boolean getIsRepairable(ItemStack item, ItemStack stack)
	{
		return this.toolMaterial.customCraftingMaterial == stack.getItem() || super.getIsRepairable(item, stack);
	}
	
	@Override
	public Multimap getAttributeModifiers(ItemStack stack)
	{
		Multimap multimap = super.getAttributeModifiers(stack);
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.weaponDamage, 0));
		return multimap;
	}
	
	// --------------------
	
	public static class ItemDagger extends ItemSwordMDM
	{
		public ItemDagger(ToolMaterial material)
		{
			super(4.5F, 0.75F, material);
		}
	}
	
	public static class ItemScimitar extends ItemSwordMDM
	{
		public ItemScimitar(ToolMaterial material)
		{
			super(5F, 0.5F, material);
		}
	}
	
	public static class ItemRapier extends ItemSwordMDM
	{
		public ItemRapier(ToolMaterial material)
		{
			super(4.75F, 0.75F, material);
		}
	}
	
	public static class ItemLongsword extends ItemSwordMDM
	{
		public ItemLongsword(ToolMaterial material)
		{
			super(4.5F, 1.2F, material);
		}
	}
	
	public static class ItemHalberd extends ItemSwordMDM
	{
		public ItemHalberd(ToolMaterial material)
		{
			super(4F, 1.3F, material);
		}
	}
	
	public static class ItemSpear extends ItemSwordMDM
	{
		public ItemSpear(ToolMaterial material)
		{
			super(3.75F, 1.3F, material);
		}
	}
	
	public static class ItemClaws extends ItemSwordMDM
	{
		public ItemClaws(ToolMaterial material)
		{
			super(5F, 1.25F, material);
		}
	}
	
	// Throwable
	
	public static class ItemThrowableKnife extends ItemSwordMDM
	{
		public ItemThrowableKnife(ToolMaterial material)
		{
			super(4F, 0.7F, material);
		}
	}
	
	public static class ItemNinjaStar extends ItemSwordMDM
	{
		public ItemNinjaStar(ToolMaterial material)
		{
			super(3.5F, 0.666F, material);
		}
	}
	
	public static class ItemJavelin extends ItemSwordMDM
	{
		public ItemJavelin(ToolMaterial material)
		{
			super(3.5F, 1.3F, material);
		}
	}
	
	public static class ItemDart extends ItemSwordMDM
	{
		public ItemDart(ToolMaterial material)
		{
			super(3F, 0.666F, material);
		}
	}
}