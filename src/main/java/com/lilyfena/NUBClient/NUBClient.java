package com.lilyfena.NUBClient;

import com.lilyfena.NUBClient.Module.Mod;
import com.lilyfena.NUBClient.Module.ModuleManager;
import com.lilyfena.NUBClient.ui.screens.clickgui.ClickGUI;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ModInitializer;
import org.lwjgl.glfw.GLFW;


public class NUBClient  implements ModInitializer {


    public static NUBClient INSTANCE;
    public static String NAME = "NUB Client";
    public static String VERSION = "v0.1";

    private MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {
        INSTANCE = this;
        System.out.println(NAME+" "+VERSION+" KAMUNIISTYYYYYYYY!");

    }


    public void postInit() {

    }

    public void onKeyPressed(int key, int action) {
        if (action == GLFW.GLFW_PRESS) {
            for (Mod module : ModuleManager.INSTANCE.getModules()) {
                if (key == module.getKey()) module.toggle();
            }

            if (key == GLFW.GLFW_KEY_N) mc.setScreen(ClickGUI.INSTANCE);
        }
    }

    public void onTick() {
        if (mc.player != null) {
            for (Mod module : ModuleManager.INSTANCE.getEnabledModules()) {
                module.onTick();
            }
        }
    }
}
