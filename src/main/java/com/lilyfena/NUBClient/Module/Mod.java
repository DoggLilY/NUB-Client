package com.lilyfena.NUBClient.Module;

import com.lilyfena.NUBClient.Module.settings.Setting;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class Mod {

    private String name;
    private String description;
    private int key;
    private boolean enabled;
    public Category category;

    private List<Setting> settings = new ArrayList<>();

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Mod(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;

    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void addSetting(Setting setting) {
        settings.add(setting);
    }

    public void addSettings(Setting... settings) {
        for (Setting setting : settings) addSetting(setting);
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onTick() {

    }
    public String getName() {
        return this.name;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String s) {
        this.description = s;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }


    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public Category getCategory() {
            return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public enum Category {
        COMBAT("Combat"),
        CLIENT("Client"),
        PLAYER("Player"),
        RENDER("Render");

        public String name;

        private Category(String name) {
            this.name = name;
        }
    }
}
