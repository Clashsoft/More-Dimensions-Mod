package clashsoft.mods.moredimensions.world.chunk_manager;

import java.util.List;

import clashsoft.cslib.minecraft.world.CustomChunkManager;
import clashsoft.mods.moredimensions.lib.WorldManager;
import clashsoft.mods.moredimensions.world.gen_layer.GenLayerAerius;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;

public class AeriusChunkManager extends CustomChunkManager
{
	public AeriusChunkManager(long seed, WorldType worldType)
	{
		super(seed, worldType);
	}

	@Override
	public void addBiomes(List<BiomeGenBase> list)
	{
		list.add(WorldManager.biomeHeaven);
		list.add(WorldManager.biomeHeavenJungle);
		list.add(WorldManager.biomeHeavenForest);
	}

	@Override
	public GenLayer[] getGenLayers(long seed, WorldType worldType)
	{
		return new GenLayerAerius().createWorld(seed, worldType);
	}
}