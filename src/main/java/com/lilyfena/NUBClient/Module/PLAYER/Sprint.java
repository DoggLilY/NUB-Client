package com.lilyfena.NUBClient.Module.PLAYER;

import com.lilyfena.NUBClient.Module.Mod;

public class Sprint extends Mod {
    public Sprint() {
        super("Sprint", "Allows you to sprint automaticly", Category.PLAYER);
    }

    @Override
    public void onTick() {
        mc.player.setSprinting(true);
        super.onTick();
    }

    @Override
    public void onDisable() {
        mc.player.setSprinting(false);
        super.onDisable();
    }
}
