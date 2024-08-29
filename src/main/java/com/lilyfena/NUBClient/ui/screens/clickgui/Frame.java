package com.lilyfena.NUBClient.ui.screens.clickgui;

import com.lilyfena.NUBClient.Module.CLIENT.ClickGui;
import com.lilyfena.NUBClient.Module.Mod;
import com.lilyfena.NUBClient.Module.ModuleManager;
import com.lilyfena.NUBClient.ui.BThackRenderUtils2;
import com.lilyfena.NUBClient.ui.screens.clickgui.setting.Component;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.ArrayList;

public class Frame {
    public int x,y,w,h, dragX, dragY;
    public Mod.Category category;
    private ArrayList<ModuleButton> buttons;
    public boolean dragging, extended;

    public Frame(Mod.Category category, int x, int y, int w, int h) {
        this.category=category;
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.dragging = false;
        this.extended = true;

        buttons = new ArrayList<>();

        int offset = h;
        for (Mod mod : ModuleManager.INSTANCE.getModulesCategory(category)) {
            buttons.add(new ModuleButton(mod, this, offset));
            offset+= h;
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        BThackRenderUtils2.drawRect(x,y,x+w,y+h, new Color(0xFFFF7200, true).hashCode());
        BThackRenderUtils2.drawOutlineRect(x,y,x+w,y+h, 1, new Color(0x000000).hashCode());
        BThackRenderUtils2.drawString(category.name, x+4,y+4, new Color(0xFFFFFFFF, true).hashCode());
        BThackRenderUtils2.drawString(extended ? "-" : "+", x+w-13,y+4, new Color(0xFFFFFFFF, true).hashCode());
        if (extended) {
            for (ModuleButton button : buttons) {
                button.render(matrices, mouseX, mouseY, (int) delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (extended) {
            for (ModuleButton mb : buttons) {
                mb.mouseClicked(mouseX, mouseY, button);
            }
        }
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                dragging = true;
                dragX = (int) (mouseX - x);
                dragY = (int) mouseY - y;
            } else if (button == 1) {
                extended = !extended;
            }
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > x && mouseX < x+ w && mouseY > y && mouseY < y + h;
    }

    public void updatePosition(double mouseX, double mouseY) {
        if (dragging) {
            x = (int) (mouseX-dragX);
            y = (int) (mouseY-dragY);
        }
    }

    public void mouseRelease(double mouseX, double mouseY, int button) {
        if (button == 0 && dragging) dragging = false;

        for (ModuleButton mb : buttons) {
            mb.mouseReleased(mouseX, mouseY, button);
        }
    }

    public int updateButtons() {
        int bbuttons = 0;
        int offset = h;

        for (ModuleButton button : buttons) {
            button.offset = offset;
            offset += h;
            bbuttons++;

            if (button.extended) {
                for (Component component : button.components) {
                    if (component.setting.isVisible()) {
                        offset += h;
                    }
                }
            }
        }
        return bbuttons;

    }
}
