package org.example.muc.moregate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.povstalec.sgjourney.StargateJourney;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Reader;

public class TransportRingVariant {
    private ResourceLocation texture;
    private ResourceLocation activeTexture;
    private ResourceLocation ringTexture;
    private ResourceLocation ringActiveTexture;
    private String[] parts;
    private Resource resource;
    public TransportRingVariant(@Nullable String Variant, ResourceManager resourceManager) throws IOException {
        if (Variant != null && Variant != ""){
            this.parts = Variant.split(":",2);
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(parts[0], "moregate/ring/" + parts[1] + ".json");
            this.resource  = resourceManager.getResource(location).orElseThrow();
            if (resource != null) {
                try (Reader reader = resource.openAsReader()) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                    this.texture = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("texture").getAsString());

                    this.activeTexture = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("active_texture").getAsString());

                    this.ringTexture = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("ring_texture").getAsString());

                    this.ringActiveTexture = ResourceLocation.fromNamespaceAndPath(parts[0], json.get("ring_active_texture").getAsString());

                }}else {
                this.texture = null;

                this.activeTexture = null;

                this.ringTexture = null;

                this.ringActiveTexture = null;
            }
        }
        else{
            this.texture = null;

            this.activeTexture = null;

            this.ringTexture = null;

            this.ringActiveTexture = null;
        }
    }
    public ResourceLocation getTexture(){
        if (this.texture != null){
            return this.texture;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/ring/default/default_texture.png");
        }
    }
    public ResourceLocation getActiveTexture(){
        if (this.activeTexture != null){
            return this.activeTexture;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/ring/default/default_active_texture.png");
        }
    }
    public ResourceLocation getRingTexture(){
        if (this.ringTexture != null){
            return this.ringTexture;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/ring/default/default_ring_texture.png");
        }
    }

    public ResourceLocation getRingActiveTexture(){
        if (this.ringActiveTexture != null){
            return this.ringActiveTexture;
        }
        else {
            return ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/ring/default/default_ring_active_texture.png");
        }
    }
}
