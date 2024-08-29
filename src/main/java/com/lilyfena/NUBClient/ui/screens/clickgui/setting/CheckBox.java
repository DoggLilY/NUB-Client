package com.lilyfena.NUBClient.ui.screens.clickgui.setting;

import com.lilyfena.NUBClient.Module.settings.BooleanSetting;
import com.lilyfena.NUBClient.Module.settings.Setting;
import com.lilyfena.NUBClient.ui.BThackRenderUtils2;
import com.lilyfena.NUBClient.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class CheckBox extends Component {

    private BooleanSetting boolSet = (BooleanSetting) setting;

    public CheckBox(Setting setting, ModuleButton parent, int offset){
        super(setting, parent, offset);
        this.boolSet = (BooleanSetting) setting;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        BThackRenderUtils2.drawRect(parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.w, parent.parent.y + parent.offset +offset + parent.parent.h, (boolSet.isEnabled()) ?new Color(0xFFFF7200, true).hashCode() : new Color(0x83000000, true).hashCode());
        BThackRenderUtils2.drawOutlineRect(parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.w, parent.parent.y + parent.offset + offset + parent.parent.h, 2, new Color(0xFF000000, true).hashCode());
        BThackRenderUtils2.drawString(boolSet.getName(), parent.parent.x + parent.parent.w/2 - MinecraftClient.getInstance().textRenderer.getWidth(boolSet.getName())/2, parent.parent.y + parent.offset + offset + parent.parent.h/4, new Color(0xFFFFFFFF, true).hashCode());
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            boolSet.toggle();
        }
        super.mouseClicked(mouseX, mouseY, button);
    }
}
