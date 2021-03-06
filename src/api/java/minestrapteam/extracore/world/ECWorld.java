package minestrapteam.extracore.world;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * The Class ECWorld.
 * <p>
 * This class adds several utils for world editing.
 * 
 * @author Clashsoft
 */
public class ECWorld
{
	/** The Constant sideMap. */
	public static final int[][]	sideMap			= new int[][] { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };
	
	/** The Constant oppositeSideMap. */
	public static final int[]	oppositeSideMap	= new int[] { 1, 0, 3, 2, 5, 4 };
	
	/**
	 * Gets the block id at the given side of the given coordinates.
	 * 
	 * @see World#getBlockId(int, int, int)
	 * @param world
	 *            the world
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param side
	 *            the side
	 * @return the block at side
	 */
	public static Block getBlockAtSide(IBlockAccess world, int x, int y, int z, int side)
	{
		int[] map = sideMap[side];
		return world.getBlock(x + map[0], y + map[1], z + map[2]);
	}
	
	/**
	 * Gets the block metadata at the given side of the given coordinates.
	 * 
	 * @see World#getBlockMetadata(int, int, int)
	 * @param world
	 *            the world
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param side
	 *            the side
	 * @return the block metadata at side
	 */
	public static int getBlockMetadataAtSide(IBlockAccess world, int x, int y, int z, int side)
	{
		int[] map = sideMap[side];
		return world.getBlockMetadata(x + map[0], y + map[1], z + map[2]);
	}
	
	/**
	 * Gets the block at the given side of the given coordinates.
	 * 
	 * @see World#getBlockTileEntity(int, int, int)
	 * @param <T>
	 *            the generic type
	 * @param world
	 *            the world
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param side
	 *            the side
	 * @return the block tile entity at side
	 */
	public static <T extends TileEntity> T getTileEntityAtSide(IBlockAccess world, int x, int y, int z, int side)
	{
		int[] map = sideMap[side];
		return (T) world.getTileEntity(x + map[0], y + map[1], z + map[2]);
	}
	
	/**
	 * Sets the block with the given metadata at the given side of the given
	 * coordinates.
	 * 
	 * @param world
	 *            the world
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param side
	 *            the side
	 * @param block
	 *            the block
	 * @param metadata
	 *            the meta
	 */
	public static void setBlockAtSide(World world, int x, int y, int z, int side, Block block, int metadata)
	{
		int[] map = sideMap[side];
		world.setBlock(x + map[0], y + map[1], z + map[2], block, metadata, 3);
	}
	
	/**
	 * Sets the block tile entity at the given side of the given coordinates.
	 * 
	 * @param world
	 *            the world
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param side
	 *            the side
	 * @param tileentity
	 *            the tileentity
	 */
	public static void setBlockTileEntityAtSide(World world, int x, int y, int z, int side, TileEntity tileentity)
	{
		int[] map = sideMap[side];
		world.setTileEntity(x + map[0], y + map[1], z + map[2], tileentity);
	}
	
	public static boolean isBoxSolid(World world, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
	{
		for (int x1 = x; x1 < x + sizeX; x1++)
		{
			for (int y1 = y; y1 < y + sizeY; y1++)
			{
				for (int z1 = z; z1 < z + sizeZ; z1++)
				{
					if (world.isAirBlock(x1, y1, z1))
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean isBoxEmpty(World world, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
	{
		for (int k1 = x; k1 < x + sizeX; k1++)
		{
			for (int l1 = y; l1 < y + sizeY; l1++)
			{
				for (int i2 = z; i2 < z + sizeZ; i2++)
				{
					if (!world.isAirBlock(k1, l1, i2))
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
