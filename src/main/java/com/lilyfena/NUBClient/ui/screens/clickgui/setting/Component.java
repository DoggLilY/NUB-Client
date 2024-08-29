package com.lilyfena.NUBClient.ui.screens.clickgui.setting;
import com.lilyfena.NUBClient.ui.screens.clickgui.*;
import com.lilyfena.NUBClient.Module.settings.Setting;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class Component {
    public Setting setting;
    public ModuleButton parent;
    public int offset;

    public Component(Setting setting, ModuleButton parent, int offset) {
        this.setting = setting;
        this.parent = parent;
        this.offset = offset;
    }

    public void render(MatrixStack matrices, int mouseX , int mouseY, float delta) {

    }

    public void mouseClicked(double mouseX, double mouseY, int button) {

    }

    public void mouseReleased(double mouseX, double mouseY, int button) {

    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.parent.x && mouseX < parent.parent.x+ parent.parent.w && mouseY > parent.parent.y + parent.offset + offset - (parent.bbuttons-1)*parent.parent.h && mouseY < parent.parent.y + parent.offset + offset + parent.parent.h - (parent.bbuttons-1)*parent.parent.h;
    }
}
