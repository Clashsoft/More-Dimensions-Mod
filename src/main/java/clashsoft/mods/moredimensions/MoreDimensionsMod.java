package clashsoft.mods.moredimensions;

import clashsoft.cslib.minecraft.ClashsoftMod;
import clashsoft.cslib.minecraft.update.CSUpdate;
import clashsoft.cslib.minecraft.util.CSConfig;
import clashsoft.mods.moredimensions.addons.*;
import clashsoft.mods.moredimensions.common.MDMEventHandler;
import clashsoft.mods.moredimensions.common.MDMProxy;
import clashsoft.mods.moredimensions.network.MDMNetHandler;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

import net.minecraftforge.common.MinecraftForge;

@Mod(modid = MoreDimensionsMod.MODID, name = MoreDimensionsMod.NAME, version = MoreDimensionsMod.VERSION)
public class MoreDimensionsMod extends ClashsoftMod
{
	public static final String		MODID		= "moredimensions";
	public static final String		NAME		= "More Dimensions Mod";
	public static final String ACRONYM = "mdm";
	public static final String		VERSION		= CSUpdate.CURRENT_VERSION + "-1.0.0-dev";
	
	@Instance(MODID)
	public static MoreDimensionsMod	instance;
	
	@SidedProxy(clientSide = "clashsoft.mods.moredimensions.client.MDMClientProxy", serverSide = "clashsoft.mods.moredimensions.common.MDMProxy")
	public static MDMProxy			proxy;
	
	public MoreDimensionsMod()
	{
		super(proxy, MODID, NAME, ACRONYM, VERSION);
		this.eventHandler = new MDMEventHandler();
		this.netHandlerClass = MDMNetHandler.class;
	}
	
	@Override
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
	}
	
	@Override
	@EventHandler
	public void init(FMLInitializationEvent event)
	{	
		super.init(event);
		
		CSConfig.loadConfig(this.configFile);
		
		MDMBlocks.initialize();
		MDMItems.initialize();
		MDMTools.initialize();
		
		MDMBlocks.load();
		MDMItems.load();
		MDMTools.load();
		
		MDMEntitys.load();
		MDMRecipes.load();
		MDMWorld.load();
		MDMInventory.load();
		MDMLocalizations.load();
		
		CSConfig.saveConfig();
	}
	
	@Override
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
}