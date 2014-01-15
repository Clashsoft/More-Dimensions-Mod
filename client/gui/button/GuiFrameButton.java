package clashsoft.mods.moredimensions.client.gui.button;

import org.lwjgl.opengl.GL11;

import clashsoft.cslib.minecraft.client.gui.GuiBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiFrameButton extends GuiButton
{
	public GuiFrameButton(int buttonID, int x, int y, int width, int height, String text)
	{
		super(buttonID, x, y, width, height, text);
	}

	public GuiFrameButton(int buttonID, int x, int y, String text)
	{
		super(buttonID, x, y, text);
	}
	
	/**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.drawButton)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(GuiPOCButton.buttonTextures);
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            
            this.field_82253_i = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.field_82253_i);
            
            this.mouseDragged(mc, mouseX, mouseY);
            
            int l = 14737632;
            
            if (!this.enabled)
            {
                l = -6250336;
            }
            else if (this.field_82253_i)
            {
                l = 16777120;
            }
            
            GuiBuilder.global.drawHoveringFrame(this.xPosition, this.yPosition, this.width, this.height, l);
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
        }
    }
    
    @Override
    public void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int color)
    {
    	fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string) / 2, y, color);
    }
}
