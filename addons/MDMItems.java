package clashsoft.mods.moredimensions.addons;

import static clashsoft.cslib.minecraft.util.CSItems.addItemWithRecipe;

import java.lang.reflect.Constructor;

import clashsoft.cslib.addon.Addon;
import clashsoft.cslib.minecraft.CustomCreativeTab;
import clashsoft.cslib.minecraft.CustomItem;
import clashsoft.cslib.minecraft.util.CSConfig;
import clashsoft.cslib.minecraft.util.CSItems;
import clashsoft.mods.moredimensions.item.alchemy.ItemMatter;
import clashsoft.mods.moredimensions.item.armor.ItemCape;
import clashsoft.mods.moredimensions.item.heaven.*;
import clashsoft.mods.moredimensions.item.poc.*;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Addon(modName = "MoreDimensionsMod", addonName = "Items")
public class MDMItems
{
	public static CustomCreativeTab			tabTools		= new CustomCreativeTab("MDMTools");
	
	public static CustomCreativeTab			tabPOCBlocks	= new CustomCreativeTab("POCBlocks");
	public static CustomCreativeTab			tabPOCItems		= new CustomCreativeTab("POCItems");
	public static CustomCreativeTab			tabPOCTools		= new CustomCreativeTab("POCTools");
	public static CustomCreativeTab			tabPOCArmor		= new CustomCreativeTab("POCArmor");
	
	public static CustomCreativeTab			tabAlchemy		= new CustomCreativeTab("AlchemyTab");
	
	public static CustomCreativeTab			tabHeavenBlocks	= new CustomCreativeTab("HeavenBlocks");
	public static CustomCreativeTab			tabHeavenItems	= new CustomCreativeTab("HeavenItems");
	public static CustomCreativeTab			tabHeavenTools	= new CustomCreativeTab("HeavenTools");
	public static CustomCreativeTab			tabHeavenArmor	= new CustomCreativeTab("HeavenArmor");
	
	public static int						pocItemID		= 25000;
	public static int						alchemyItemID	= 25500;
	public static int						heavenItemID	= 26000;
	
	// -- Paradise of Chaos --
	
	public static ItemStaff					staves;
	public static ItemManaStar				manaStar;
	
	public static CustomItem				pocMaterials;
	
	public static ItemStack					magicOakStick, willowStick, daeyaltGem, noviteGem, marmarosGem, kratoniumIngot;
	
	// -- Heaven --
	
	public static ItemHeavenMaterials		heavenMaterials;
	public static ItemHeavenFood			heavenApple;
	public static ItemLifeHeart				lifeHeart;
	public static ItemFireSword				fireSword;
	public static ItemIceHammer				icehammer;
	public static ItemHeavenSoul			heavenSouls;
	public static ItemHeavenPortalActivator	heavenPortalActivator;
	public static ItemCape					capes;
	public static ItemHeavenSeeds			heavenSeeds;
	public static CustomItem				specialItems;
	public static ItemIceStick				iceStick;
	
	public static ItemStack					heavenWoodStick, goldWoodStick, heavenArrow, shrekiteShard, clashiumIngot, holyiumIngot, energyOrb, proAlloy, bluriteDust;
	
	// -- Alchemy --
	
	public static ItemAlchemyGuide			alchemyGuide;
	public static ItemElixir				elixir;
	public static ItemElixirBottle			elixirBottle;
	public static ItemMatter				matter;
	
	public static void initialize()
	{
		// -- Paradise of Chaos --
		
		staves = (ItemStaff) new ItemStaff(CSConfig.getItem("Staves", pocItemID++)).setUnlocalizedName("staves").setCreativeTab(tabPOCTools);
		manaStar = (ItemManaStar) new ItemManaStar(CSConfig.getItem("Mana Star", pocItemID++)).setUnlocalizedName("manastar").setTextureName("moredimensions:manastar").setCreativeTab(tabPOCItems);
		pocMaterials = (CustomItem) new CustomItem(CSConfig.getItem("POC Materials", pocItemID++), new String[] { "Magic Oak Stick", "Willow Stick", "Daeyalt Gem", "Novite Gem", "Marmaros Gem", "Kratonium Ingot" }, new String[] { "moredimensions:magicoak_stick", "moredimensions:willow_stick", "moredimensions:daeyalt_gem", "moredimensions:novite_gem", "moredimensions:marmaros_gem", "moredimensions:kratonium_ingot" }).setUnlocalizedName("pocMaterials").setCreativeTab(tabPOCItems);
		{
			int i = -1;
			magicOakStick = new ItemStack(pocMaterials, 1, ++i);
			willowStick = new ItemStack(pocMaterials, 1, ++i);
			daeyaltGem = new ItemStack(pocMaterials, 1, ++i);
			noviteGem = new ItemStack(pocMaterials, 1, ++i);
			marmarosGem = new ItemStack(pocMaterials, 1, ++i);
			kratoniumIngot = new ItemStack(pocMaterials, 1, ++i);
			
		}
		
		// -- Heaven --
		
		heavenMaterials = (ItemHeavenMaterials) new ItemHeavenMaterials(CSConfig.getItem("Heaven Materials", heavenItemID++), new String[] { "Heaven Wood Stick", "Gold Wood Stick", "Heaven Arrow", "Shrekite Shard", "Clashium Ingot", "Holyium Ingot", "Energy Orb", "Pro Alloy", "Blurite Dust" }, new String[] { "moredimensions:heavenwood_stick", "moredimensions:goldwood_stick", "moredimensions:heaven_arrow", "moredimensions:shrekite_shard", "moredimensions:clashium_ingot", "moredimensions:holyium_ingot", "moredimensions:energy_orb", "moredimensions:pro_ingot", "moredimensions:blurite_dust" }).setUnlocalizedName("heavenMaterials").setCreativeTab(tabHeavenItems);
		{
			int i = -1;
			heavenWoodStick = new ItemStack(heavenMaterials, 1, ++i);
			goldWoodStick = new ItemStack(heavenMaterials, 1, ++i);
			heavenArrow = new ItemStack(heavenMaterials, 1, ++i);
			shrekiteShard = new ItemStack(heavenMaterials, 1, ++i);
			clashiumIngot = new ItemStack(heavenMaterials, 1, ++i);
			holyiumIngot = new ItemStack(heavenMaterials, 1, ++i);
			energyOrb = new ItemStack(heavenMaterials, 1, ++i);
			proAlloy = new ItemStack(heavenMaterials, 1, ++i);
			bluriteDust = new ItemStack(heavenMaterials, 1, ++i);
		}
		
		heavenApple = (ItemHeavenFood) (new ItemHeavenFood(CSConfig.getItem("Heaven Apple", heavenItemID++), 2, 0, false)).setUnlocalizedName("heaven_apple").setTextureName("moredimensions:heaven_apple");
		lifeHeart = (ItemLifeHeart) (new ItemLifeHeart(CSConfig.getItem("Life Heart", heavenItemID++))).setUnlocalizedName("life_heart").setTextureName("moredimensions:life_heart");
		fireSword = (ItemFireSword) (new ItemFireSword(CSConfig.getItem("Fire Sword", heavenItemID++))).setUnlocalizedName("fire_sword").setTextureName("moredimensions:fire_sword");
		icehammer = (ItemIceHammer) (new ItemIceHammer(CSConfig.getItem("Ice Hammer", heavenItemID++))).setUnlocalizedName("ice_hammer").setTextureName("moredimensions:ice_hammer");
		heavenSouls = (ItemHeavenSoul) (new ItemHeavenSoul(CSConfig.getItem("Soul", heavenItemID++))).setUnlocalizedName("soul");
		capes = (ItemCape) new ItemCape(CSConfig.getItem("Cape", heavenItemID++)).setUnlocalizedName("cape").setCreativeTab(tabHeavenArmor);
		heavenSeeds = (ItemHeavenSeeds) (new ItemHeavenSeeds(CSConfig.getItem("Heaven Seeds", heavenItemID++), new String[] { "Heaven Grass Seeds", "Mud Grass Seeds", "Corrupted Grass Seeds", "Hallowed Grass Seeds", "Mushroom Grass Seeds" }, new String[] { "moredimensions:heaven_grass_seeds", "moredimensions:mud_grass_seeds", "moredimensions:corrupted_grass_seeds", "moredimensions:hallowed_grass_seeds", "moredimensions:mushroom_grass_seeds" })).setUnlocalizedName("heavenSeeds");
		iceStick = (ItemIceStick) new ItemIceStick(CSConfig.getItem("Ice Stick", heavenItemID++)).setUnlocalizedName("ice_stick").setTextureName("moredimensions:ice_stick").setCreativeTab(tabHeavenItems);
		
		heavenPortalActivator = (ItemHeavenPortalActivator) (new ItemHeavenPortalActivator(CSConfig.getItem("Heaven Portal Activator", heavenItemID++))).setUnlocalizedName("heaven_portal_activator").setTextureName("moredimensions:heaven_portal_activator").setCreativeTab(tabHeavenTools);
		
		// -- Alchemy --
		
		alchemyGuide = (ItemAlchemyGuide) new ItemAlchemyGuide(CSConfig.getItem("Alchemy Guide", alchemyItemID++)).setUnlocalizedName("alchemy_guide").setTextureName("moredimensions:alchemy_guide").setCreativeTab(MDMItems.tabPOCItems);
		elixir = (ItemElixir) new ItemElixir(CSConfig.getItem("Elixir", alchemyItemID++)).setUnlocalizedName("elixir").setCreativeTab(tabAlchemy);
		elixirBottle = (ItemElixirBottle) new ItemElixirBottle(CSConfig.getItem("Elixir Bottle", alchemyItemID++)).setUnlocalizedName("elixirbottle").setCreativeTab(tabAlchemy);
		matter = (ItemMatter) new ItemMatter(CSConfig.getItem("Matter", alchemyItemID++)).setUnlocalizedName("matter").setCreativeTab(tabAlchemy);
	}
	
	public static void load()
	{
		// -- Paradise of Chaos --
		
		addItem(manaStar, "Mana Star", "Manastern");
		addItem(pocMaterials, "POCMaterials");
		
		// -- Heaven --
		
		addItem(heavenMaterials, "HeavenMaterials");
		addItem(heavenPortalActivator, "Heaven Portal Activator");
		addItem(heavenApple, "Heaven Apple");
		addItem(lifeHeart, "Life Heart");
		addItem(fireSword, "Fire Sword");
		addItemWithRecipe(icehammer, "Ice Hammer", 1, new Object[] { "IiI", "IiI", " i ", Character.valueOf('I'), Block.ice, Character.valueOf('i'), iceStick });
		addItem(capes, "Capes");
		addItem(heavenSeeds, "HeavenSeeds");
		addItem(iceStick, "Ice Stick");
		
		tabPOCBlocks.setIconItemStack(new ItemStack(MDMBlocks.pocGrassBlocks));
		tabPOCItems.setIconItemStack(new ItemStack(pocMaterials));
		
		tabAlchemy.setIconItemStack(new ItemStack(elixirBottle));
		
		tabHeavenBlocks.setIconItemStack(new ItemStack(MDMBlocks.heavenGrassBlocks));
		tabHeavenItems.setIconItemStack(shrekiteShard);
		
		// -- Alchemy --
		
		addItem(alchemyGuide, "Alchemy Guide", "Buch der Alchemie");
		addItem(elixir, "Elixir", "Elixier");
		addItem(elixirBottle, "Elixir Bottle", "Elixierflasche");
	}
	
	public static void addItem(Item item, String en, String de)
	{
		CSItems.addItem(item, en);
		if (de != null)
		{
			LanguageRegistry.instance().addNameForObject(item, "de_DE", de);
		}
	}
	
	public static void addItem(Item item, String name)
	{
		addItem(item, name, null);
	}
	
	public static <T extends Item> T addItem(Class<T> type, String name, Object... args)
	{
		Class[] classes = new Class[args.length + 1];
		
		for (int i = 0; i < args.length; i++)
		{
			if (args[i] != null)
			{
				classes[i] = args[i].getClass();
			}
			else
			{
				classes[i] = Object.class;
			}
		}
		return addItem(type, name, classes, args);
	}
	
	public static <T extends Item> T addItem(Class<T> type, String name, Class[] argsTypes, Object... args)
	{
		T item = null;
		
		try
		{
			Constructor<T> c = type.getConstructor(argsTypes);
			if (c != null)
			{
				item = c.newInstance(args);
			}
		}
		catch (Throwable ex)
		{
			throw new RuntimeException(ex);
		}
		
		return item;
	}
}
