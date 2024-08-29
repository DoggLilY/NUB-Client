package com.lilyfena.NUBClient.Module.PLAYER;

import com.lilyfena.NUBClient.Module.Mod;
import com.lilyfena.NUBClient.Module.settings.BooleanSetting;
import com.lilyfena.NUBClient.Module.settings.NumberSetting;

public class Uuuiiiii extends Mod {
    public NumberSetting speed = new NumberSetting("Rotation Speed", 1, 90, 2, 1);
    public BooleanSetting shift = new BooleanSetting("a", false);
    public Uuuiiiii() {
        super("Uuuiiiii", "Uuuiiiii", Category.PLAYER);
        addSettings(
                speed,
                shift
        );
    }

    @Override
    public void onTick() {
        mc.player.changeLookDirection((double) speed.getValue(), 0);
    }

}
