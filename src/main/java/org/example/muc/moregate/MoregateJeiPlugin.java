package org.example.muc.moregate;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.component.ModDataComponent;
import org.example.muc.moregate.item.ModItems;

@JeiPlugin
public class MoregateJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(
                Moregate.MODID,
                "jei_plugin"
        );
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {

        registration.registerSubtypeInterpreter(
                ModItems.DHD_VARIANT_CRYSTAL.get(),
                (stack, context) -> {
                    return stack.getOrDefault(
                            ModDataComponent.DHD_VARIANT.get(),
                            ""
                    );
                }
        );

        registration.registerSubtypeInterpreter(
                ModItems.TRANSPORT_RING_VARIANT_CRYSTAL.get(),
                (stack, context) -> {
                    return stack.getOrDefault(
                            ModDataComponent.TRASNPORT_RING_VARIANT.get(),
                            ""
                    );
                }
        );
    }
}
