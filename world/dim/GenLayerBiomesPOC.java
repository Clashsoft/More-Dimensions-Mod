package com.chaosdev.paradiseofchaos.world.dim;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import com.chaosdev.paradiseofchaos.ParadiseOfChaos;

public class GenLayerBiomesPOC extends GenLayer
{
	protected BiomeGenBase[]	allowedBiomes	= { ParadiseOfChaos.PlainsOfInsanityBiome, ParadiseOfChaos.CorruptedMountainsBiome, ParadiseOfChaos.DesertOfDrynessBiome };
	
	public GenLayerBiomesPOC(long seed, GenLayer genlayer)
	{
		super(seed);
		this.parent = genlayer;
	}
	
	public GenLayerBiomesPOC(long seed)
	{
		super(seed);
	}
	
	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);
		
		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				dest[(dx + dz * width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		return dest;
	}
}