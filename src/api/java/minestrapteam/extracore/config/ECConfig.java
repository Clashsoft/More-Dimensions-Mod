package minestrapteam.extracore.config;

import java.io.File;

import minestrapteam.extracore.world.gen.OreGen;
import minestrapteam.extracore.util.StringUtils;
import minestrapteam.extracore.util.IParsable;

import net.minecraftforge.common.config.Configuration;

/**
 * The global class for loading configuration files.
 * 
 * @author Clashsoft
 */
public class ECConfig
{
	public static final boolean					THREADSAVE		= true;
	
	public static File							configFile;
	public static String						configName;
	public static boolean						enableComments	= true;
	
	protected static Configuration				config;
	protected static ThreadLocal<Configuration>	localConfig		= new ThreadLocal();
	
	/**
	 * Loads a config from the given {@link File} {@code file} and names it with
	 * the given {@link String} {@code configName}.
	 * 
	 * @param configFile
	 *            the config file
	 * @param configName
	 *            the name
	 */
	public static void loadConfig(File configFile, String configName)
	{
		Configuration config = new Configuration(configFile);
		
		ECConfig.configFile = configFile;
		ECConfig.configName = configName;
		ECConfig.config = config;
		
		if (THREADSAVE)
		{
			localConfig.set(config);
		}
	}
	
	/**
	 * Loads a config from the given {@link File} {@code file}.
	 * 
	 * @param configFile
	 *            the config file
	 */
	public static void loadConfig(File configFile)
	{
		loadConfig(configFile, configFile.getName());
	}
	
	/**
	 * Saves the current config to the disk.
	 */
	public static void saveConfig()
	{
		config().save();
	}
	
	protected static Configuration config()
	{
		if (THREADSAVE)
		{
			return localConfig.get();
		}
		return config;
	}
	
	/**
	 * Returns the default description for the given {@link String} {@code key}
	 * and the given {@link Object} {@code _default}.<br>
	 * The description always has the format
	 * <p>
	 * [key]. Default: [_default]
	 * 
	 * @param key
	 *            the key
	 * @param _default
	 *            the default value
	 * @return the description
	 */
	public static String getDefaultDesc(String key, Object _default)
	{
		if (enableComments)
		{
			return key + ". Default: " + _default;
		}
		return null;
	}
	
	// Default getters
	
	public static int getInt(String category, String key, int _default)
	{
		return getInt(category, key, getDefaultDesc(key, _default), _default);
	}
	
	public static float getFloat(String category, String key, float _default)
	{
		return getFloat(category, key, getDefaultDesc(key, _default), _default);
	}
	
	public static double getDouble(String category, String key, double _default)
	{
		return getDouble(category, key, getDefaultDesc(key, _default), _default);
	}
	
	public static boolean getBool(String category, String key, boolean _default)
	{
		return getBool(category, key, getDefaultDesc(key, _default), _default);
	}
	
	public static String getString(String category, String key, String _default)
	{
		return getString(category, key, getDefaultDesc(key, _default), _default);
	}
	
	public static String getString(String category, String key, Object _default)
	{
		return getString(category, key, String.valueOf(_default));
	}
	
	public static <T extends IParsable> T getObject(String category, String key, T _default)
	{
		return getObject(category, key, getDefaultDesc(key, _default), _default);
	}
	
	// Description getters
	
	public static int getInt(String category, String key, String desc, int _default)
	{
		return config().get(category, StringUtils.identifier(key), _default, desc).getInt(_default);
	}
	
	public static float getFloat(String category, String key, String desc, float _default)
	{
		return (float) config().get(category, StringUtils.identifier(key), _default, desc).getDouble(_default);
	}
	
	public static double getDouble(String category, String key, String desc, double _default)
	{
		return config().get(category, StringUtils.identifier(key), _default, desc).getDouble(_default);
	}
	
	public static boolean getBool(String category, String key, String desc, boolean _default)
	{
		return config().get(category, StringUtils.identifier(key), _default, desc).getBoolean(_default);
	}
	
	public static String getString(String category, String key, String desc, String _default)
	{
		return config().get(category, StringUtils.identifier(key), _default, desc).getString();
	}
	
	public static <T extends IParsable> T getObject(String category, String key, String desc, T _default)
	{
		return (T) _default.parse(getString(category, key, desc, _default.toString()));
	}
	
	@Deprecated
	public static int getItem(String key, int _default)
	{
		if (!key.contains("Item ID"))
		{
			key += " Item ID";
		}
		return getInt("item", key, _default);
	}
	
	@Deprecated
	public static int getTerrainBlock(String key, int _default)
	{
		if (!key.contains("Block ID"))
		{
			key += " Block ID";
		}
		return getInt("terrainblock", key, _default);
	}
	
	@Deprecated
	public static int getBlock(String key, int _default)
	{
		if (!key.contains("Block ID"))
		{
			key += " Block ID";
		}
		return getInt("block", key, _default);
	}
	
	public static int getTileEntity(String key, int _default)
	{
		if (!key.contains("TileEntity ID"))
		{
			key += " TileEntity ID";
		}
		return getInt("tileentity", key, _default);
	}
	
	public static int getDimension(String key, int _default)
	{
		if (!key.contains("Dimension ID"))
		{
			key += " Dimension ID";
		}
		return getInt("dim", key, _default);
	}
	
	public static int getBiome(String key, int _default)
	{
		if (!key.contains("Biome ID"))
		{
			key += " Biome ID";
		}
		return getInt("biome", key, _default);
	}
	
	public static OreGen getOreGen(String key, OreGen _default)
	{
		if (_default == null)
		{
			_default = new OreGen();
		}
		
		_default.amount = getInt(key, "Number Of Blocks", null, _default.amount);
		_default.veigns = getInt(key, "Veigns Per Chunk", null, _default.veigns);
		_default.minY = getInt(key, "Min Y", null, _default.minY);
		_default.maxY = getInt(key, "Max Y", null, _default.maxY);
		
		return _default;
	}
}
