package minestrapteam.extracore.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class CustomWorldGen extends WorldGenerator
{
	public int		flags;
	public Block	genBlock	= Blocks.stone;
	public int		genMetadata;
	
	public CustomWorldGen(boolean update)
	{
		super(update);
		this.flags = update ? 3 : 2;
	}
	
	public void setBlock(World world, int x, int y, int z, Random random)
	{
		world.setBlock(x, y, z, this.genBlock, this.genMetadata, this.flags);
	}
	
	public void setBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		world.setBlock(x, y, z, block, metadata, this.flags);
	}
	
	public final void drawLineX(World world, int x, int y, int z, int length, Random random)
	{
		for (int x1 = x; x1 < x + length; x1++)
		{
			this.setBlock(world, x1, y, z, random);
		}
	}
	
	public final void drawLineY(World world, int x, int y, int z, int length, Random random)
	{
		for (int y1 = y; y1 < y + length; y1++)
		{
			this.setBlock(world, x, y1, z, random);
		}
	}
	
	public final void drawLineZ(World world, int x, int y, int z, int length, Random random)
	{
		for (int z1 = z; z1 < z + length; z1++)
		{
			this.setBlock(world, x, y, z1, random);
		}
	}
	
	public final void drawPlaneX(World world, int x, int y, int z, int ySize, int zSize, Random random)
	{
		for (int y1 = y; y1 < y + ySize; y1++)
		{
			for (int z1 = z; z1 < z + zSize; z1++)
			{
				this.setBlock(world, x, y1, z1, random);
			}
		}
	}
	
	public final void drawPlaneY(World world, int x, int y, int z, int xSize, int zSize, Random random)
	{
		for (int x1 = x; x1 < x + xSize; x1++)
		{
			for (int z1 = z; z1 < z + zSize; z1++)
			{
				this.setBlock(world, x1, y, z1, random);
			}
		}
	}
	
	public final void drawPlaneZ(World world, int x, int y, int z, int xSize, int ySize, Random random)
	{
		for (int x1 = x; x1 < x + xSize; x1++)
		{
			for (int y1 = y; y1 < y + ySize; y1++)
			{
				this.setBlock(world, x1, y1, z, random);
			}
		}
	}
	
	public final void drawSquareTube(World world, int x, int y, int z, int xSize, int ySize, int zSize, int direction, Random random)
	{
		this.drawSolidBox(world, x, y, z, xSize, ySize, zSize, random);
		if (direction == 0 || direction == 2)
		{
			this.drawPlaneY(world, x, y, z, xSize, zSize, random);
			this.drawPlaneY(world, x, y + ySize - 1, z, xSize, zSize, random);
		}
		if (direction == 1 || direction == 2)
		{
			this.drawPlaneX(world, x, y, z, ySize, zSize, random);
			this.drawPlaneX(world, x + xSize - 1, y, z, ySize, zSize, random);
		}
		if (direction == 0 || direction == 1)
		{
			this.drawPlaneZ(world, x, y, z, xSize, ySize, random);
			this.drawPlaneZ(world, x, y, z + zSize - 1, xSize, ySize, random);
		}
	}
	
	public final void drawHollowBox(World world, int x, int y, int z, int xSize, int ySize, int zSize, Random random)
	{
		this.drawSolidBox(world, x + 1, y + 1, z + 1, xSize - 1, ySize - 1, zSize - 1, Blocks.air, 0);
		this.drawPlaneY(world, x, y, z, xSize, zSize, random);
		this.drawPlaneY(world, x, y + ySize - 1, z, xSize, zSize, random);
		this.drawPlaneX(world, x, y, z, ySize, zSize, random);
		this.drawPlaneX(world, x + xSize - 1, y, z, ySize, zSize, random);
		this.drawPlaneZ(world, x, y, z, xSize, ySize, random);
		this.drawPlaneZ(world, x, y, z + zSize - 1, xSize, ySize, random);
	}
	
	public final void drawSolidBox(World world, int x, int y, int z, int sizeX, int sizeY, int sizeZ, Random random)
	{
		for (int x1 = x; x1 < x + sizeX; x1++)
		{
			for (int y1 = y; y1 < y + sizeY; y1++)
			{
				for (int z1 = z; z1 < z + sizeZ; z1++)
				{
					this.setBlock(world, x1, y1, z1, random);
				}
			}
		}
	}
	
	public final void drawHollowSphere(World world, int x, int y, int z, int radius, Random random)
	{
		int sqradius = radius * radius;
		for (int i = -radius; i <= radius; i++)
		{
			for (int j = -radius; j <= radius; j++)
			{
				for (int k = -radius; k <= radius; k++)
				{
					if (i * i + j * j + k * k == sqradius)
					{
						this.setBlock(world, x + i, y + j, z + k, random);
					}
				}
			}
		}
	}
	
	public final void drawSolidSphere(World world, int x, int y, int z, int radius, Random random)
	{
		int sqradius = radius * radius;
		for (int i = -radius; i <= radius; i++)
		{
			for (int j = -radius; j <= radius; j++)
			{
				for (int k = -radius; k <= radius; k++)
				{
					if (i * i + j * j + k * k <= sqradius)
					{
						this.setBlock(world, x + i, y + j, z + k, random);
					}
				}
			}
		}
	}
	
	public final void drawLineX(World world, int x, int y, int z, int length, Block block, int metadata)
	{
		for (int x1 = x; x1 < x + length; x1++)
		{
			world.setBlock(x1, y, z, block, metadata, this.flags);
		}
	}
	
	public final void drawLineY(World world, int x, int y, int z, int length, Block block, int metadata)
	{
		for (int y1 = y; y1 < y + length; y1++)
		{
			world.setBlock(x, y1, z, block, metadata, this.flags);
		}
	}
	
	public final void drawLineZ(World world, int x, int y, int z, int length, Block block, int metadata)
	{
		for (int z1 = z; z1 < z + length; z1++)
		{
			world.setBlock(x, y, z1, block, metadata, this.flags);
		}
	}
	
	public final void drawPlaneX(World world, int x, int y, int z, int ySize, int zSize, Block block, int metadata)
	{
		for (int y1 = y; y1 < y + ySize; y1++)
		{
			for (int z1 = z; z1 < z + zSize; z1++)
			{
				world.setBlock(x, y1, z1, block, metadata, this.flags);
			}
		}
	}
	
	public final void drawPlaneY(World world, int x, int y, int z, int xSize, int zSize, Block block, int metadata)
	{
		for (int x1 = x; x1 < x + xSize; x1++)
		{
			for (int z1 = z; z1 < z + zSize; z1++)
			{
				world.setBlock(x1, y, z1, block, metadata, this.flags);
			}
		}
	}
	
	public final void drawPlaneZ(World world, int x, int y, int z, int xSize, int ySize, Block block, int metadata)
	{
		for (int x1 = x; x1 < x + xSize; x1++)
		{
			for (int y1 = y; y1 < y + ySize; y1++)
			{
				world.setBlock(x1, y1, z, block, metadata, this.flags);
			}
		}
	}
	
	public final void drawSquareTube(World world, int x, int y, int z, int xSize, int ySize, int zSize, int direction, Block block, int metadata)
	{
		this.drawSolidBox(world, x, y, z, xSize, ySize, zSize, block, metadata);
		if (direction == 0 || direction == 2)
		{
			this.drawPlaneY(world, x, y, z, xSize, zSize, block, metadata);
			this.drawPlaneY(world, x, y + ySize - 1, z, xSize, zSize, block, metadata);
		}
		if (direction == 1 || direction == 2)
		{
			this.drawPlaneX(world, x, y, z, ySize, zSize, block, metadata);
			this.drawPlaneX(world, x + xSize - 1, y, z, ySize, zSize, block, metadata);
		}
		if (direction == 0 || direction == 1)
		{
			this.drawPlaneZ(world, x, y, z, xSize, ySize, block, metadata);
			this.drawPlaneZ(world, x, y, z + zSize - 1, xSize, ySize, block, metadata);
		}
	}
	
	public final void drawHollowBox(World world, int x, int y, int z, int xSize, int ySize, int zSize, Block block, int metadata)
	{
		this.drawSolidBox(world, x + 1, y + 1, z + 1, xSize - 1, ySize - 1, zSize - 1, Blocks.air, 0);
		this.drawPlaneY(world, x, y, z, xSize, zSize, block, metadata);
		this.drawPlaneY(world, x, y + ySize - 1, z, xSize, zSize, block, metadata);
		this.drawPlaneX(world, x, y, z, ySize, zSize, block, metadata);
		this.drawPlaneX(world, x + xSize - 1, y, z, ySize, zSize, block, metadata);
		this.drawPlaneZ(world, x, y, z, xSize, ySize, block, metadata);
		this.drawPlaneZ(world, x, y, z + zSize - 1, xSize, ySize, block, metadata);
	}
	
	public final void drawSolidBox(World world, int x, int y, int z, int sizeX, int sizeY, int sizeZ, Block block, int metadata)
	{
		for (int x1 = x; x1 < x + sizeX; x1++)
		{
			for (int y1 = y; y1 < y + sizeY; y1++)
			{
				for (int z1 = z; z1 < z + sizeZ; z1++)
				{
					world.setBlock(x1, y1, z1, block, metadata, this.flags);
				}
			}
		}
	}
	
	public final void drawHollowSphere(World world, int x, int y, int z, int radius, Block block, int metadata)
	{
		int sqradius = radius * radius;
		for (int i = -radius; i <= radius; i++)
		{
			for (int j = -radius; j <= radius; j++)
			{
				for (int k = -radius; k <= radius; k++)
				{
					if (i * i + j * j + k * k == sqradius)
					{
						world.setBlock(x + i, y + j, z + k, block, metadata, this.flags);
					}
				}
			}
		}
	}
	
	public final void drawSolidSphere(World world, int x, int y, int z, int radius, Block block, int metadata)
	{
		int sqradius = radius * radius;
		for (int i = -radius; i <= radius; i++)
		{
			for (int j = -radius; j <= radius; j++)
			{
				for (int k = -radius; k <= radius; k++)
				{
					if (i * i + j * j + k * k <= sqradius)
					{
						world.setBlock(x + i, y + j, z + k, block, metadata, this.flags);
					}
				}
			}
		}
	}
}
