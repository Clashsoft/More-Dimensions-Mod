package clashsoft.mods.moredimensions.world.gen;

import java.util.Random;

import clashsoft.clashsoftapi.util.CSWorld;
import clashsoft.mods.moredimensions.addons.MDMHeaven;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class HeavenGenBuildings
{
	public static HeavenGenBuildings	instance	= new HeavenGenBuildings();
	
	/**
	 * The WorldGen for a little hut
	 * 
	 * @author CS
	 */
	public class HeavenGenBuilding1 extends WorldGenerator
	{
		public HeavenGenBuilding1()
		{
			
		}
		
		public HeavenGenBuilding1(boolean par1)
		{
			super(par1);
		}
		
		@Override
		public boolean generate(World world, Random rand, int x, int y, int z)
		{
			return generateN(world, rand, x, y, z);
		}
		
		public boolean generateN(World world, Random rand, int x, int y, int z)
		{
			// Heavencobble frame
			CSWorld.setFrame(world, x, y, z, x + 6, y + 4, z + 6, MDMHeaven.stoneBlocks.blockID, 1);
			// Walls
			CSWorld.setCube(world, x, y + 1, z + 1, x, y + 3, z + 5, MDMHeaven.woodBlocks.blockID, 0);
			CSWorld.setCube(world, x + 1, y + 1, z, x + 5, y + 3, z, MDMHeaven.woodBlocks.blockID, 0);
			CSWorld.setCube(world, x + 6, y + 1, z + 1, x + 6, y + 3, z + 5, MDMHeaven.woodBlocks.blockID, 0);
			CSWorld.setCube(world, x + 1, y + 1, z + 6, x + 5, y + 3, z + 6, MDMHeaven.woodBlocks.blockID, 0);
			// Floor
			CSWorld.setCube(world, x + 1, y, z + 1, x + 5, y, z + 5, MDMHeaven.heavenLog.blockID, 0);
			// Door
			CSWorld.setCube(world, x + 3, y + 1, z, x + 3, y + 2, z, 0, 0);
			// Windows
			CSWorld.setBlock(world, x + 3, y + 2, z + 6, Block.thinGlass.blockID, 0);
			CSWorld.setBlock(world, x + 3, y, z, Block.thinGlass.blockID, 0);
			CSWorld.setBlock(world, x, y, z + 3, Block.thinGlass.blockID, 0);
			CSWorld.setBlock(world, x + 6, y, z + 3, Block.thinGlass.blockID, 0);
			return false;
		}
	}
}
