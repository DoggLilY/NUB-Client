package com.lilyfena.NUBClient.ui.screens.clickgui;

import com.lilyfena.NUBClient.Mc;
import com.lilyfena.NUBClient.Module.Mod;
import com.lilyfena.NUBClient.Module.settings.BooleanSetting;
import com.lilyfena.NUBClient.Module.settings.ModeSetting;
import com.lilyfena.NUBClient.Module.settings.NumberSetting;
import com.lilyfena.NUBClient.Module.settings.Setting;
import com.lilyfena.NUBClient.ui.BThackRenderUtils2;
import com.lilyfena.NUBClient.ui.screens.clickgui.setting.CheckBox;
import com.lilyfena.NUBClient.ui.screens.clickgui.setting.Component;
import com.lilyfena.NUBClient.ui.screens.clickgui.setting.ModeBox;
import com.lilyfena.NUBClient.ui.screens.clickgui.setting.Slider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class ModuleButton {
    public Mod module;
    public Frame parent;
    public ArrayList<Component> components;
    public int bbuttons;
    public int offset;
    public boolean extended;

    public ModuleButton(Mod module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.bbuttons = 0;
        this.extended = false;

        int setOffset = offset;
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                components.add(new CheckBox(setting, this, setOffset));
            } else if (setting instanceof ModeSetting) {
                components.add(new ModeBox(setting, this, setOffset));
            } else if (setting instanceof NumberSetting) {
                components.add(new Slider(setting, this, setOffset));
            }
            setOffset += parent.h;
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        BThackRenderUtils2.drawRect(parent.x, parent.y + offset, parent.x + parent.w, parent.y + offset + parent.h, module.isEnabled() ? new Color(0xFFFF7200, true).hashCode() : new Color(0x83000000, true).hashCode());
        BThackRenderUtils2.drawString(module.getName(), parent.x + parent.w/2 - MinecraftClient.getInstance().textRenderer.getWidth(module.getName()) / 2, parent.y + offset + parent.h/4, new Color(0xFFFFFFFF, true).hashCode());
        if (extended) {
            int count = 0;
            for (Component component : components) {
                component.render(matrices, mouseX, mouseY, delta);
                count++;
            }
            if (module.isEnabled()) {
                BThackRenderUtils2.drawOutlineRect(parent.x, parent.y + offset, parent.x + parent.w, parent.y + offset + (parent.h * (1 + count)), 1, new Color(0xFF7200).hashCode());
            } else {
                BThackRenderUtils2.drawOutlineRect(parent.x, parent.y + offset, parent.x + parent.w, parent.y + offset + (parent.h * (1 + count)), 1, new Color(0xFF000000, true).hashCode());
            }
        }
        if (isHovered(mouseX, mouseY)) {
            BThackRenderUtils2.drawRect(0, 0, MinecraftClient.getInstance().textRenderer.getWidth(module.getDescription()) + 10, 20, new Color(0x83000000, true).hashCode());
            BThackRenderUtils2.drawOutlineRect(0,0, MinecraftClient.getInstance().textRenderer.getWidth(module.getDescription()) + 10, 20, 2, new Color(0xFF7200).hashCode());
            BThackRenderUtils2.drawString(module.getDescription(), 5, 5, -1);
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            module.toggle();
        } else if (isHovered(mouseX, mouseY) && button == 1) {
            extended = !extended;
            this.bbuttons = parent.updateButtons();
        }
        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.x && mouseX < parent.x+ parent.w && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.h;
    }
}
