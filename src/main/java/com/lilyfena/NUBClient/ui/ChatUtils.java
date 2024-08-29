package com.lilyfena.NUBClient.ui;


import com.lilyfena.NUBClient.Mc;
import com.lilyfena.NUBClient.NUBClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class ChatUtils implements Mc {

    private static final String prefix = "[" + Formatting.BLUE + NUBClient.NAME + Formatting.RESET + "] ";

    public static void sendMessage(String message) {
        if (mc.player == null) return;
        mc.player.sendMessage(Text.of(prefix + message));

    }

    public static void sendMessage(String msg, SoundEvent soundEvent) {
        if (mc.player == null) return;
        mc.player.sendMessage(Text.of(prefix + msg));
        mc.player.playSound(soundEvent,1,1);
    }

    public static void sendChatMessage(String message) {
        if (mc.player == null) return;
        mc.player.networkHandler.sendChatMessage(message);
    }

    public static void sendCommand(String command) {
        if (mc.player == null) return;
        mc.player.networkHandler.sendChatCommand(command.substring(1));
    }
}
