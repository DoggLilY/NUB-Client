package com.lilyfena.NUBClient.ui.screens.clickgui.setting;

import com.lilyfena.NUBClient.Module.settings.NumberSetting;
import com.lilyfena.NUBClient.Module.settings.Setting;
import com.lilyfena.NUBClient.ui.BThackRenderUtils2;
import com.lilyfena.NUBClient.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Component {

    public NumberSetting numSet = (NumberSetting) setting;
    private boolean sliding = false;

    public Slider(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.numSet = (NumberSetting) setting;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        BThackRenderUtils2.drawRect(parent.parent.x, parent.parent.y + parent.offset - (parent.bbuttons-4)*parent.parent.h, parent.parent.x + parent.parent.w, parent.parent.y + parent.offset + parent.parent.h - (parent.bbuttons-4)*parent.parent.h, new Color(0x83000000, true).hashCode());
        double diff = Math.min(parent.parent.w, Math.max(0, mouseX - parent.parent.x));
        int renderW = (int) (parent.parent.w * ((numSet.getValue() - numSet.getMin()) / (numSet.getMax() - numSet.getMin())));

        BThackRenderUtils2.drawRect(parent.parent.x, parent.parent.y + parent.offset - (parent.bbuttons-4)*parent.parent.h, parent.parent.x + renderW, parent.parent.y + parent.offset - (parent.bbuttons-4)*parent.parent.h, new Color(0xFFFF7200, true).hashCode());
        BThackRenderUtils2.drawOutlineRect(parent.parent.x, parent.parent.y + parent.offset - (parent.bbuttons-4)*parent.parent.h, parent.parent.x + parent.parent.w, parent.parent.y + parent.offset + parent.parent.h - (parent.bbuttons-4)*parent.parent.h, 2, new Color(0x83000000, true).hashCode());
        if (sliding) {
            if (diff == 0) {
                numSet.setValue(numSet.getMin());
            } else {
                numSet.setValue(roundToPlace((diff/parent.parent.w) * (numSet.getMax() - numSet.getMin()) + numSet.getMin(), 2));
            }
        }
        BThackRenderUtils2.drawString(numSet.getName() + ": " + roundToPlace(numSet.getValue(),2), parent.parent.x + parent.parent.w - MinecraftClient.getInstance().textRenderer.getWidth(numSet.getName() + ": " + roundToPlace(numSet.getValue(),2)), parent.parent.y + parent.offset + offset + parent.parent.h/4, new Color(0xFFFFFFFF, true).hashCode());
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) sliding = true;
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        sliding = false;
        super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return super.isHovered(mouseX, mouseY);
    }

    private double roundToPlace(double value, int place) {
        if (place < 0) {
            return value;
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

