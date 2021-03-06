package minestrapteam.extradims.biome.aerius;

import minestrapteam.extracore.world.gen.WorldGenRanged;
import minestrapteam.extradims.lib.aerius.ABlocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeCorruption extends BiomeAerius
{
	public BiomeCorruption(int id)
	{
		super(id);

		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.treesPerChunk = 1;

		this.topMetadata = 2;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return new WorldGenRanged(ABlocks.plantBlocks, 4);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return getTreeGenForTree(random, random.nextBoolean() ? WILLOW : CLOUDROOT, false);
	}
}
