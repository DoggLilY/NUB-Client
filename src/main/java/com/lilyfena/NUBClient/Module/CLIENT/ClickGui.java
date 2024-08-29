package com.lilyfena.NUBClient.Module.CLIENT;

import com.lilyfena.NUBClient.Module.Mod;
import com.lilyfena.NUBClient.Module.settings.BooleanSetting;

public class ClickGui extends Mod {
    public BooleanSetting RGB = new BooleanSetting("Rainbow: ", false);

    public ClickGui() {
        super("ClickGUI", "Settings of this cheat client", Category.CLIENT);
        addSetting(RGB);
    }

    @Override
    public void onTick() {
        super.onTick();
    }

    public boolean getRGBs()  {
        return RGB.isEnabled();
    }
}
