package minestrapteam.extradims.common;

import minestrapteam.extracore.proxy.BaseProxy;
import minestrapteam.extradims.ExtraDimensions;
import minestrapteam.extradims.api.IChatEntity;
import minestrapteam.extradims.inventory.ContainerAlchemyTable;
import minestrapteam.extradims.inventory.ContainerBossChat;
import minestrapteam.extradims.inventory.ContainerDamnationTable;
import minestrapteam.extradims.inventory.ContainerTome;
import minestrapteam.extradims.tileentity.TileEntityAlchemyTable;
import minestrapteam.extradims.tileentity.TileEntityDamnationTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EDProxy extends BaseProxy
{
	public static int	BOSS_CHAT_GUIID			= 20;
	public static int	TOME_GUIID				= 21;
	public static int	DAMNATION_TABLE_GUIID	= 22;
	public static int	ALCHEMY_TABLE_GUIID		= 23;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == BOSS_CHAT_GUIID)
		{
			return new ContainerBossChat(player, (IChatEntity) world.getEntityByID(x));
		}
		else if (ID == TOME_GUIID)
		{
			return new ContainerTome();
		}
		else if (ID == DAMNATION_TABLE_GUIID)
		{
			return new ContainerDamnationTable(player.inventory, (TileEntityDamnationTable) world.getTileEntity(x, y, z));
		}
		else if (ID == ALCHEMY_TABLE_GUIID)
		{
			return new ContainerAlchemyTable(player.inventory, (TileEntityAlchemyTable) world.getTileEntity(x, y, z));
		}
		else
		{
			return null;
		}
	}
	
	public static int getArmorIndex(String name)
	{
		return ExtraDimensions.proxy.getArmor(name);
	}
	
	protected int getArmor(String name)
	{
		return 0;
	}
}
