package org.example.muc.moregate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.phys.Vec3;
import net.povstalec.sgjourney.StargateJourney;


import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Reader;

public class DHDVariant {
    private ResourceLocation texture;
    private ResourceLocation background;
    private ResourceLocation Button;
    private ResourceLocation ButtonOverlay;
    private ResourceLocation bigButton;
    private Vec3i ActiveColor;
    private Boolean isHorned;
    private String[] parts;
    private Resource resource;
    public DHDVariant(@Nullable String Variant, ResourceManager resourceManager) throws IOException {
        if (Variant != null && Variant != ""){
            this.parts = Variant.split(":",2);
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(parts[0], "moregate/dhd/" + parts[1] + ".json");
            this.resource  = resourceManager.getResource(location).orElseThrow();
            if (resource != null) {
        try (Reader reader = resource.openAsReader()) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            this.texture = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("texture").getAsString());

            this.isHorned = json.get("is_horned").getAsBoolean();

            this.background = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("background").getAsString());

            this.Button = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("button").getAsString());

            this.ButtonOverlay = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("button_overlay").getAsString());

            this.bigButton = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("big_button").getAsString());

            JsonArray color = json.getAsJsonArray("active_color");
            this.ActiveColor = new Vec3i(color.get(0).getAsInt(), color.get(1).getAsInt(), color.get(2).getAsInt());

        }}else {
                this.texture = null;

                this.isHorned = null;

                this.background = null;

                this.Button = null;

                this.ButtonOverlay = null;

                this.bigButton = null;
            }
        }
        else{
            this.texture = null;

            this.isHorned = null;

            this.background = null;

            this.Button = null;

            this.ButtonOverlay = null;

            this.bigButton = null;
        }

    }
    public ResourceLocation getTexture(){
        if (this.texture != null){
            return this.texture;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/dhd/default/default_texture.png");
        }
    }
    public Boolean IsHorned(){
        if (this.isHorned != null){
            return this.isHorned;
        }
        else {
            return false;
        }
    }
    public ResourceLocation getBackground(){
        if (this.background != null){
            return this.background;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate","textures/entity/dhd/default/default_bg.png");
        }
    }
    public ResourceLocation getButton(){
        if (this.Button != null){
            return this.Button;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/dhd/default/default_button.png");
        }
    }
    public ResourceLocation getButtonOverlay(){
        if (this.ButtonOverlay != null){
            return this.ButtonOverlay;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/dhd/default/default_button_overlay.png");
        }
    }
    public ResourceLocation getBigButton(){
        if (this.bigButton != null){
            return this.bigButton;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/dhd/default/default_big_button.png");
        }
    }

    public Vec3i getActiveColor(){
        if (this.ActiveColor != null){
            return this.ActiveColor;
        }
        else {
            return new Vec3i(255, 251, 0);
        }
    }
}
