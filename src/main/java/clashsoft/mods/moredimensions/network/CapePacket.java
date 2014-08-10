package clashsoft.mods.moredimensions.network;

import java.io.IOException;

import clashsoft.cslib.minecraft.network.CSPacket;
import clashsoft.mods.moredimensions.MoreDimensionsMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;

public class CapePacket extends CSPacket
{
	public String username;
	public String cape;
	
	public CapePacket()
	{
		
	}
	
	public CapePacket(EntityPlayer player, String name)
	{
		
	}
	
	@Override
	public void write(PacketBuffer buf)
	{
		try
		{
			buf.writeStringToBuffer(this.username);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void read(PacketBuffer buf)
	{
		try
		{
			this.username = buf.readStringFromBuffer(0xFFFF);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			this.cape = buf.readStringFromBuffer(0xFFFF);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleClient(EntityPlayer player)
	{
		EntityPlayer player1 = MoreDimensionsMod.proxy.findPlayer(this.username);
		MoreDimensionsMod.proxy.setCape(player1, this.cape);
	}
	
	@Override
	public void handleServer(EntityPlayerMP player)
	{
		MoreDimensionsMod.instance.netHandler.sendToAll(this);
	}
}