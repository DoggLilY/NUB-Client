package com.lilyfena.NUBClient.ui;

import com.lilyfena.NUBClient.Module.Mod;
import com.lilyfena.NUBClient.Module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.Comparator;

import static com.lilyfena.NUBClient.Mc.mc;

public class Hud {

    public static void render(MatrixStack matrices, float tickDelta) {
        renderArrayList(matrices);
    }

    public static void renderArrayList(MatrixStack matrices) {
        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();

        ModuleManager.INSTANCE.getEnabledModules().sort(Comparator.comparingInt(m -> (int)mc.textRenderer.getWidth(((Mod)m).getName())).reversed());
        for (Mod mod : ModuleManager.INSTANCE.getEnabledModules()) {
            BThackRenderUtils2.drawString(mod.getName(), (sWidth - 4) - mc.textRenderer.getWidth(mod.getName()), 10 + (index * mc.textRenderer.fontHeight), -1);
            index = index+1;
        }
    }

}