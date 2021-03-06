package fr.minecraftforgefrance.ffmtlibs.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class FFMTGuiContainerSliderBase extends GuiContainer
{
	private GuiButton selectedButton;

	public FFMTGuiContainerSliderBase(Container container)
	{
		super(container);
	}

	protected void mouseClicked(int x, int y, int id)
	{
		super.mouseClicked(x, y, id);
		if(id == 0)
		{
			for(int l = 0; l < this.buttonList.size(); ++l)
			{
				GuiButton guibutton = (GuiButton)this.buttonList.get(l);

				if(guibutton.mousePressed(this.mc, x, y))
				{
					this.selectedButton = guibutton;
					this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
					this.actionPerformed(guibutton);
				}
			}
		}
	}

	protected void mouseMovedOrUp(int x, int y, int id)
	{
		super.mouseMovedOrUp(x, y, id);
		if(this.selectedButton != null && id == 0)
		{
			this.selectedButton.mouseReleased(x, y);
			this.selectedButton = null;
		}
	}

	public abstract void handlerSliderAction(int sliderId, float sliderValue);

	public abstract String getSliderName(int sliderId, float sliderValue);
}