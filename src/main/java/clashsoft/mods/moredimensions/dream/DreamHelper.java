package clashsoft.mods.moredimensions.dream;

import java.util.Random;

import clashsoft.cslib.util.CSLog;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class DreamHelper
{
	public static void onPlayerStartedSleeping(EntityPlayer player, int x, int y, int z, long worldTime)
	{
		Random rand = player.getRNG();
		rand.setSeed(worldTime * -3014739720835286983L);
		
		int randomNumber = rand.nextInt(128);
		if (randomNumber < 12)
		{
			startNightmare(player, x, y, z);
		}
		else if (randomNumber < 32)
		{
			startDream(player, x, y, z);
		}
	}
	
	public static void startDream(EntityPlayer player, int x, int y, int z)
	{
		CSLog.info("Starting dream");
		player.addChatMessage(new ChatComponentTranslation("dreams.start.dream"));
		start(new Dream(), player, x, y, z);
	}
	
	public static void startNightmare(EntityPlayer player, int x, int y, int z)
	{
		CSLog.info("Starting nightmare");
		player.addChatMessage(new ChatComponentTranslation("dreams.start.nightmare"));
		start(new Nightmare(), player, x, y, z);
	}
	
	public static void start(Dream dream, EntityPlayer player, int x, int y, int z)
	{
		dream.setPlayer(player);
		dream.setBedLocation(player.worldObj, x, y, z);
		dream.start();
	}
}
