package org.example.muc.moregate.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.povstalec.sgjourney.StargateJourney;
import net.povstalec.sgjourney.client.screens.SGJourneyContainerScreen;
import net.povstalec.sgjourney.client.widgets.dhd.GenericDHDSymbolButton;
import net.povstalec.sgjourney.common.config.ClientDHDConfig;
import net.povstalec.sgjourney.common.misc.ColorUtil;
import net.povstalec.sgjourney.common.sgjourney.PointOfOrigin;
import net.povstalec.sgjourney.common.sgjourney.Symbols;
import org.example.muc.moregate.DHDVariant;
import org.example.muc.moregate.menu.CameleonMenu;

public class CameleonDHDSymbolButton extends GenericDHDSymbolButton {

    public static final int[] CANON_SYMBOLS = {0, 12, 18, 21, 6, 37, 5, 28, 23, 33, 11, 36, 10, 20, 2, 3, 19, 8, 4, 31, 14, 34, 29, 15, 27, 9, 32, 38, 25, 22, 17, 13, 16, 1, 24, 35, 7, 26, 30};

    public static final ResourceLocation CLASSIC_BUTTONS = StargateJourney.sgjourneyLocation("textures/gui/dhd/classic/classic_dhd_buttons.png");
    public static final ResourceLocation CLASSIC_BUTTONS_OVERLAY = StargateJourney.sgjourneyLocation("textures/gui/dhd/classic/classic_dhd_buttons_overlay.png");

    protected final int canonSymbol;

    public CameleonDHDSymbolButton(int x, int y, int width, int height, CameleonMenu menu, int screenWidth, int screenHeight,
                                   float xCenter, float yCenter, int textureX, int textureY, int symbol, int canonSymbol, Position position,Button.OnPress onPress, DHDVariant variant)
    {
        super(x, y, width, height, menu, symbol, screenWidth, screenHeight, menu.getVariant().getButton(), menu.getVariant().getButtonOverlay(), xCenter, yCenter, textureX, textureY, position,
                new ColorUtil.RGBA(255, 255, 255, 255), new ColorUtil.RGBA(65, 65, 65), new ColorUtil.RGBA(menu.getVariant().getActiveColor().getX(), menu.getVariant().getActiveColor().getY(), menu.getVariant().getActiveColor().getZ(), 255), onPress);

        this.canonSymbol = canonSymbol;

        setTooltip(Tooltip.create(symbolComponent()));
    }

    public CameleonDHDSymbolButton(int x, int y, CameleonMenu menu, int screenWidth, int screenHeight, int symbol, int canonSymbol, DefaultButton defaultButton,Button.OnPress onPress, DHDVariant variant)
    {
        this(x, y, defaultButton.width, defaultButton.height, menu, screenWidth, screenHeight, defaultButton.width / 2F + defaultButton.symbolOffsetX, defaultButton.height / 2F + defaultButton.symbolOffsetY, defaultButton.textureX, defaultButton.textureY, symbol, canonSymbol, defaultButton.position,onPress, variant);
    }

    @Override
    public int getSymbol()
    {
        return ClientDHDConfig.classic_dhd_canon_button_layout.get() ? canonSymbol : symbol;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        updateRemapping();
        this.isHovered = isMouseOver(mouseX, mouseY);

        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, widgets);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        guiGraphics.blit(widgets, this.getX(), this.getY(), textureX, textureY, this.width, this.height);

        if(isEngaged())
        {
            RenderSystem.setShaderTexture(0, overlay);
            RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
            guiGraphics.blit(overlay, this.getX(), this.getY(), textureX, textureY, this.width, this.height);
        }
        else if(this.isHoveredOrFocused())
        {
            RenderSystem.setShaderTexture(0, overlay);
            RenderSystem.setShaderColor(hoverColor.red(), hoverColor.green(), hoverColor.blue(), hoverColor.alpha());
            guiGraphics.blit(overlay, this.getX(), this.getY(), textureX, textureY, this.width, this.height);
        }

        if(ClientDHDConfig.dhd_symbols_numbers.get() == SGJourneyContainerScreen.isShiftDown())
            renderNumber(guiGraphics, minecraft);
        else
            renderSymbol(guiGraphics);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
    }

    @Override
    public ResourceKey<PointOfOrigin> getPointOfOrigin() {
        return this.menu.getDHD().symbolInfo().pointOfOrigin();
    }

    @Override
    public ResourceKey<Symbols> getSymbols() {

        return this.menu.getDHD().symbolInfo().symbols();
    }
}
