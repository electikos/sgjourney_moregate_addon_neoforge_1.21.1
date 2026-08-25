package org.example.muc.moregate.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.povstalec.sgjourney.StargateJourney;
import net.povstalec.sgjourney.client.screens.dhd.AbstractDHDScreen;
import net.povstalec.sgjourney.client.screens.dhd.DHDCrystalScreen;
import net.povstalec.sgjourney.client.widgets.dhd.DHDBigButton;
import org.example.muc.moregate.menu.CameleonMenu;
import org.example.muc.moregate.menu.CrystalCameleonMenu;

public class CrystalCameleonDHDScreen extends DHDCrystalScreen<CrystalCameleonMenu> {
    public CrystalCameleonDHDScreen(CrystalCameleonMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title, StargateJourney.sgjourneyLocation("textures/gui/dhd/classic/classic_dhd_crystal_gui.png"));
    }
    private static final ResourceLocation CRYSTAL_SLOT = ResourceLocation.fromNamespaceAndPath("moregate", "textures/gui/dhd/crystal_slot.png");
    @Override
    protected void renderBg(
            GuiGraphics graphics,
            float partialTick,
            int mouseX,
            int mouseY)
    {
        super.renderBg(graphics, partialTick, mouseX, mouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(
                CRYSTAL_SLOT,
                x + 43,
                y + 34,
                0,
                0,
                18,
                18,
                18,
                18
        );

        this.itemHint(
                graphics,
                texture,
                x + 44,
                y + 35,
                CRYSTAL_HINT_OFFSET_X,
                HINT_OFFSET_Y,
                0
        );

    }
}
