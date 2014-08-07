package clashsoft.mods.moredimensions.client;

import java.util.HashMap;
import java.util.Map;

import clashsoft.mods.moredimensions.api.IMDMBoss;
import clashsoft.mods.moredimensions.client.gui.GuiBossChat;
import clashsoft.mods.moredimensions.client.gui.GuiPOCIngame;
import clashsoft.mods.moredimensions.client.gui.GuiTome;
import clashsoft.mods.moredimensions.client.gui.container.GuiAlchemyTable;
import clashsoft.mods.moredimensions.client.gui.container.GuiDamnationTable;
import clashsoft.mods.moredimensions.client.renderer.entity.RenderLich;
import clashsoft.mods.moredimensions.client.renderer.item.RenderPOCBows;
import clashsoft.mods.moredimensions.client.renderer.tileentity.RenderAlchemyTube;
import clashsoft.mods.moredimensions.client.sound.MDMSoundHandler;
import clashsoft.mods.moredimensions.common.MDMProxy;
import clashsoft.mods.moredimensions.entity.boss.EntityLich;
import clashsoft.mods.moredimensions.tileentity.TileEntityAlchemyTable;
import clashsoft.mods.moredimensions.tileentity.TileEntityAlchemyTube;
import clashsoft.mods.moredimensions.tileentity.TileEntityDamnationTable;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class MDMClientProxy extends MDMProxy
{
	public Map<String, Integer>	armorFiles	= new HashMap<String, Integer>();
	
	public RenderPOCBows		bowRenderer;
	
	public static int			tubeRenderType;
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == BOSS_CHAT_GUIID)
		{
			return new GuiBossChat(player, (IMDMBoss) world.getEntityByID(x));
		}
		else if (ID == TOME_GUIID)
		{
			return new GuiTome();
		}
		else if (ID == DAMNATION_TABLE_GUIID)
		{
			return new GuiDamnationTable(player.inventory, (TileEntityDamnationTable) world.getTileEntity(x, y, z));
		}
		else if (ID == ALCHEMY_TABLE_GUIID)
		{
			return new GuiAlchemyTable(player, (TileEntityAlchemyTable) world.getTileEntity(x, y, z));
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		// Event Handlers
		MinecraftForge.EVENT_BUS.register(new MDMClientEvents());
		MinecraftForge.EVENT_BUS.register(new GuiPOCIngame(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(MDMSoundHandler.instance);
		
		// Entity Renderers
		RenderingRegistry.registerEntityRenderingHandler(EntityLich.class, new RenderLich());
		
		// Item Renderers
		this.bowRenderer = new RenderPOCBows();
		MinecraftForgeClient.registerItemRenderer(Items.bow, this.bowRenderer);
		
		// Tile Entity Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemyTube.class, new RenderAlchemyTube());
		
		// Capes
	}
	
	@Override
	protected int getArmor(String name)
	{
		if (this.armorFiles.containsKey(name))
		{
			return this.armorFiles.get(name);
		}
		else
		{
			int result = RenderingRegistry.addNewArmourRendererPrefix(name);
			this.armorFiles.put(name, result);
			return result;
		}
	}
}
