package com.lilyfena.NUBClient.Module.PLAYER;

import com.lilyfena.NUBClient.Module.Mod;
import com.lilyfena.NUBClient.Module.settings.ModeSetting;
import com.lilyfena.NUBClient.ui.ChatUtils;
import net.minecraft.entity.Entity;

import java.util.Comparator;

public class Mkill extends Mod {
    public ModeSetting mode = new ModeSetting("Mode", "enemy", "enemy", "instant");


    public Mkill() {
        super("Mkill", "Things with kill command", Category.PLAYER);
        addSetting(mode);
    }

    @Override
    public void onEnable() {
        if (mode.getMode() == "instant") {
            ChatUtils.sendCommand("/kill");
        }
    }

    @Override
    public void onTick() {
        double range = 4.0;

        Entity entity = mc.world.getPlayers().stream().filter(entityPlayer -> entityPlayer != mc.player).min(Comparator.comparing(entityPlayer ->
                entityPlayer.distanceTo(mc.player))).filter(entityPlayer -> entityPlayer.distanceTo(mc.player) <= range).orElse(null);
        if (mode.getMode() == "enemy" && entity != null) {
            ChatUtils.sendCommand("/kill");
        }
        super.onTick();
    }

}
