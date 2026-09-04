package org.example.muc.moregate.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.menu.ChiselMenu;
import org.example.muc.moregate.network.SetCartridgeAddressPayload;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;

public class ChiselScreen extends AbstractContainerScreen<ChiselMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Moregate.MODID, "textures/gui/chisel/chisel.png");

    private EditBox addressInput;

    public ChiselScreen(ChiselMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        addressInput = new EditBox(font, x + 20, y + 30, 136, 20, Component.literal("Adresse"));

        addressInput.setMaxLength(26);
        addressInput.setFilter(text -> text.matches("[0-9\\s]*"));

        addRenderableWidget(addressInput);

        addRenderableWidget(
                Button.builder(Component.translatable("screen.moregate.chisel_enter_button"), button -> submit()).bounds(
                        x + 50,
                        y + 60,
                        76,
                        20
                ).build()
        );

        setInitialFocus(addressInput);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        if (Screen.hasControlDown()) {
            if (keyCode == GLFW.GLFW_KEY_V || // paste
                    keyCode == GLFW.GLFW_KEY_C || // copy
                    keyCode == GLFW.GLFW_KEY_X)   // cut
            {
                return true;
            }
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }


    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {

    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {

        super.render(graphics, mouseX, mouseY, partialTick);

        renderTooltip(graphics, mouseX, mouseY);
    }

    private int[] parseAddress(String value){
        if (value == "" || value == null){
            return new int[0];
        }
        return Arrays.stream(value.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    private void submit() {

        int[] address = parseAddress(addressInput.getValue());

        PacketDistributor.sendToServer(
                new SetCartridgeAddressPayload(
                        menu.getCartridgePos(),
                        address
                )
        );

        onClose();
    }
    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // No labels hehe
    }
}
