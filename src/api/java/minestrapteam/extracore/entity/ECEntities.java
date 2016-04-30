package minestrapteam.extracore.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import minestrapteam.extracore.util.Constants;
import minestrapteam.extracore.util.ReflectionUtils;
import minestrapteam.extracore.util.logging.ECLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ECEntities
{
	private static Map<String, Class>   STRING_CLASS_MAPPING = (Map<String, Class>) EntityList.stringToClassMapping;
	private static Map<Class, String>   CLASS_STRING_MAPPING = (Map<Class, String>) EntityList.classToStringMapping;
	private static Map<Integer, Class>  ID_CLASS_MAPPING     = (Map<Integer, Class>) EntityList.IDtoClassMapping;
	private static Map<Class, Integer>  CLASS_ID_MAPPING     = ReflectionUtils.getValue(
		Constants.FIELD_EntityList_classToIDMapping, (Object) null);
	private static Map<String, Integer> STRING_ID_MAPPING    = ReflectionUtils.getValue(
		Constants.FIELD_EntityList_stringToIDMapping, (Object) null);

	public static List<EntityProperties> properties = new ArrayList<>();

	private static void register_do(String name, int id, Class<? extends Entity> entityClass)
	{
		STRING_CLASS_MAPPING.put(name, entityClass);
		CLASS_STRING_MAPPING.put(entityClass, name);
		ID_CLASS_MAPPING.put(id, entityClass);
		CLASS_ID_MAPPING.put(entityClass, id);
		STRING_ID_MAPPING.put(name, id);
	}

	/**
	 * Registers an entity of type {@code entityClass} with the given {@link String} {@code name} and the given {@code
	 * id}.
	 *
	 * @param name
	 * 	the name
	 * @param id
	 * 	the id
	 * @param entityClass
	 * 	the entity class
	 */
	public static void register(String name, int id, Class<? extends Entity> entityClass)
	{
		register_do(name, id, entityClass);
	}

	/**
	 * Registers an entity of type {@code entityClass} with the given {@link String} {@code name} and the given {@code
	 * id} and sets it's spawn egg colors to {@code eggColor1} and {@code eggColor2}.
	 *
	 * @param name
	 * 	the name
	 * @param id
	 * 	the id
	 * @param eggColor1
	 * 	the first egg color
	 * @param eggColor2
	 * 	the second egg color
	 * @param entityClass
	 * 	the entity class
	 */
	public static void register(String name, int id, Class<? extends Entity> entityClass, int eggColor1, int eggColor2)
	{
		EntityRegistry.registerGlobalEntityID(entityClass, name, id, eggColor1, eggColor2);
	}

	/**
	 * Replaced the given entity type {@code oldClass} with the new entity type {@code newClass}. This ensures that all
	 * entities of type {@code oldClass} will now spawn or load as an instance of {@code newClass}.
	 *
	 * @param oldClass
	 * 	the old entity class to replace
	 * @param newClass
	 * 	the new entity class
	 */
	public static void replace(Class<? extends Entity> oldClass, Class<? extends Entity> newClass)
	{
		register_do(CLASS_STRING_MAPPING.get(oldClass), CLASS_ID_MAPPING.get(oldClass), newClass);
	}

	public static void registerProperties(String name, Class<? extends IExtendedEntityProperties> entityClass)
	{
		properties.add(new EntityProperties(name, entityClass));
	}

	public static void registerProperties(EntityProperties props)
	{
		if (props == null)
		{
			throw new NullPointerException("Cannot use null EntityProperties");
		}
		properties.add(props);
	}

	public static boolean hasProperties(String name, Entity entity)
	{
		return entity.getExtendedProperties(name) != null;
	}

	public static <T extends IExtendedEntityProperties> T getProperties(String name, Entity entity)
	{
		T properties = (T) entity.getExtendedProperties(name);
		if (properties != null)
		{
			return properties;
		}
		return initProperties(name, entity);
	}

	public static <T extends IExtendedEntityProperties> T initProperties(String name, Entity entity)
	{
		for (EntityProperties eps : properties)
		{
			if (name.equals(eps.name))
			{
				return (T) eps.loadProperties(entity);
			}
		}
		return null;
	}

	/**
	 * Should not be used from outside this library.
	 */
	public static void loadProperties(Entity entity)
	{
		for (EntityProperties ep : properties)
		{
			if (ep.canApply(entity))
			{
				ep.loadProperties(entity);
			}
		}
	}

	public static class EntityProperties
	{
		public String                                     name;
		public Class<? extends IExtendedEntityProperties> propertyClass;

		public EntityProperties(String name, Class<? extends IExtendedEntityProperties> propertyClass)
		{
			if (propertyClass == null)
			{
				throw new NullPointerException("Cannot use null property class");
			}

			this.name = name;
			this.propertyClass = propertyClass;
		}

		public boolean canApply(Entity entity)
		{
			return true;
		}

		public IExtendedEntityProperties loadProperties(Entity entity)
		{
			IExtendedEntityProperties properties = entity.getExtendedProperties(this.name);
			if (properties == null)
			{
				properties = this.createProperties(entity);
				if (properties != null)
				{
					entity.registerExtendedProperties(this.name, properties);
				}
			}
			return properties;
		}

		public IExtendedEntityProperties createProperties(Entity entity)
		{
			try
			{
				Constructor c = this.propertyClass.getConstructor(Entity.class);
				return (IExtendedEntityProperties) c.newInstance(entity);
			}
			catch (Exception ex)
			{
				ECLog.error("Failed to load Entity Properties (" + this.propertyClass + "): " + ex.getMessage());
				return null;
			}
		}
	}

	public static class PlayerProperties extends EntityProperties
	{
		public PlayerProperties(String name, Class<? extends IExtendedEntityProperties> propertyClass)
		{
			super(name, propertyClass);
		}

		@Override
		public boolean canApply(Entity entity)
		{
			return entity instanceof EntityPlayer;
		}
	}
}
