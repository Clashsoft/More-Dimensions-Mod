package clashsoft.mods.moredimensions.client.gui;

import org.lwjgl.opengl.GL11;

import clashsoft.mods.moredimensions.inventory.ContainerDamnationTable;
import clashsoft.mods.moredimensions.tileentity.TileEntityDamnationTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiDamnationTable extends GuiContainer
{
	private static final ResourceLocation	damnationTableTexture	= new ResourceLocation("moredimensions", "textures/gui/container/damnation_table.png");
	private TileEntityDamnationTable		damnationTable;
	
	public GuiDamnationTable(InventoryPlayer inventory, TileEntityDamnationTable damnationTable)
	{
		super(new ContainerDamnationTable(inventory, damnationTable));
		this.damnationTable = damnationTable;
	}
	
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String s = this.damnationTable.isInvNameLocalized() ? this.damnationTable.getInvName() : I18n.getString(this.damnationTable.getInvName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(damnationTableTexture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		
		int i1;
		
		if (this.damnationTable.isActive())
		{
			i1 = this.damnationTable.getEnergyTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 51, l + 50 - i1, 176, 12 - i1, 14, i1 + 2);
		}
		
		i1 = this.damnationTable.getProgressScaled(23);
		this.drawTexturedModalRect(k + 45, l + 19, 176, 14, i1, 16);
		
		i1 = this.damnationTable.getProgressScaled(29);
		this.drawTexturedModalRect(k + 95, l + 19, 176, 30, i1, 16);
		
		i1 = this.damnationTable.getProgressScaled(16);
		this.drawTexturedModalRect(k + 103, l + 35, 198, 0, 11, i1);
	}
}
