package org.example.muc.moregate.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.povstalec.sgjourney.StargateJourney;
import net.povstalec.sgjourney.client.screens.dhd.AbstractDHDScreen;
import net.povstalec.sgjourney.client.widgets.dhd.DHDBigButton;
import net.povstalec.sgjourney.client.widgets.dhd.GenericDHDSymbolButton;
import org.example.muc.moregate.DHDVariant;
import org.example.muc.moregate.menu.CameleonMenu;
import org.example.muc.moregate.menu.CrystalCameleonMenu;

public class CameleonDHDScreen extends AbstractDHDScreen<CameleonMenu> {
    private DHDVariant Variant;

    public CameleonDHDScreen(CameleonMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title, menu.blockEntity.variant.getBackground());
        this.Variant = menu.blockEntity.variant;
    }

    @Override
    public void init()
    {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        super.init();
        this.addRenderableWidget(new CameleonBigButton(x + 69, y + 69, menu, button -> {engageStargate();onClose();}, menu.getVariant().getBigButton()));
        // Outer Buttons
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 8, y + 83, menu, width, height, 0, 0, GenericDHDSymbolButton.DefaultButton.BUTTON_0, button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 10, y + 58, menu, width, height, 1, 12, GenericDHDSymbolButton.DefaultButton.BUTTON_1,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 18, y + 35, menu, width, height, 2, 18, GenericDHDSymbolButton.DefaultButton.BUTTON_2,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 34, y + 18, menu, width, height, 3, 21, GenericDHDSymbolButton.DefaultButton.BUTTON_3,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 57, y + 10, menu, width, height, 4, 6, GenericDHDSymbolButton.DefaultButton.BUTTON_4,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 83, y + 8, menu, width, height, 5, 37, GenericDHDSymbolButton.DefaultButton.BUTTON_5,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 107, y + 10, menu, width, height, 6, 5, GenericDHDSymbolButton.DefaultButton.BUTTON_6,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 126, y + 18, menu, width, height, 7, 28, GenericDHDSymbolButton.DefaultButton.BUTTON_7,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));

        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 142, y + 35, menu, width, height, 8, 23, GenericDHDSymbolButton.DefaultButton.BUTTON_8,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 153, y + 58, menu, width, height, 9, 33, GenericDHDSymbolButton.DefaultButton.BUTTON_9,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 159, y + 83, menu, width, height, 10, 11, GenericDHDSymbolButton.DefaultButton.BUTTON_10,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 153, y + 107, menu, width, height, 11, 36, GenericDHDSymbolButton.DefaultButton.BUTTON_11,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 142, y + 125, menu, width, height, 12, 10, GenericDHDSymbolButton.DefaultButton.BUTTON_12,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 126, y + 141, menu, width, height, 13, 20, GenericDHDSymbolButton.DefaultButton.BUTTON_13,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 107, y + 153, menu, width, height, 14, 2, GenericDHDSymbolButton.DefaultButton.BUTTON_14,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 83, y + 159, menu, width, height, 15, 3, GenericDHDSymbolButton.DefaultButton.BUTTON_15,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));

        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 57, y + 153, menu, width, height, 16, 19, GenericDHDSymbolButton.DefaultButton.BUTTON_16,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 34, y + 141, menu, width, height, 17, 8, GenericDHDSymbolButton.DefaultButton.BUTTON_17,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 18, y + 125, menu, width, height, 18, 4, GenericDHDSymbolButton.DefaultButton.BUTTON_18,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 10, y + 107, menu, width, height, 19, 31, GenericDHDSymbolButton.DefaultButton.BUTTON_19,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        // Inner Buttons
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 35, y + 73, menu, width, height, 20, 14, GenericDHDSymbolButton.DefaultButton.BUTTON_20,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 41, y + 55, menu, width, height, 21, 34, GenericDHDSymbolButton.DefaultButton.BUTTON_21,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 52, y + 43, menu, width, height, 22, 29, GenericDHDSymbolButton.DefaultButton.BUTTON_22,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 68, y + 36, menu, width, height, 23, 15, GenericDHDSymbolButton.DefaultButton.BUTTON_23,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 87, y + 35, menu, width, height, 24, 27, GenericDHDSymbolButton.DefaultButton.BUTTON_24,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 102, y + 36, menu, width, height, 25, 9, GenericDHDSymbolButton.DefaultButton.BUTTON_25,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 113, y + 43, menu, width, height, 26, 32, GenericDHDSymbolButton.DefaultButton.BUTTON_26,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 121, y + 55, menu, width, height, 27, 38, GenericDHDSymbolButton.DefaultButton.BUTTON_27,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 127, y + 73, menu, width, height, 28, 25, GenericDHDSymbolButton.DefaultButton.BUTTON_28,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));

        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 128, y + 92, menu, width, height, 29, 22, GenericDHDSymbolButton.DefaultButton.BUTTON_29,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 124, y + 106, menu, width, height, 30, 17, GenericDHDSymbolButton.DefaultButton.BUTTON_30,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 117, y + 115, menu, width, height, 31, 13, GenericDHDSymbolButton.DefaultButton.BUTTON_31,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 108, y + 123, menu, width, height, 32, 16, GenericDHDSymbolButton.DefaultButton.BUTTON_32,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 97, y + 128, menu, width, height, 33, 1, GenericDHDSymbolButton.DefaultButton.BUTTON_33,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 77, y + 128, menu, width, height, 34, 24, GenericDHDSymbolButton.DefaultButton.BUTTON_34,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 59, y + 123, menu, width, height, 35, 35, GenericDHDSymbolButton.DefaultButton.BUTTON_35,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 46, y + 115, menu, width, height, 36, 7, GenericDHDSymbolButton.DefaultButton.BUTTON_36,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 38, y + 106, menu, width, height, 37, 26, GenericDHDSymbolButton.DefaultButton.BUTTON_37,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));

        this.addRenderableWidget(new CameleonDHDSymbolButton(x + 35, y + 92, menu, width, height, 38, 30, GenericDHDSymbolButton.DefaultButton.BUTTON_38,button -> encodeSymbol(((CameleonDHDSymbolButton) button).getSymbol()), this.Variant));
    }
}
