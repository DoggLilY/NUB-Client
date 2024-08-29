package com.lilyfena.NUBClient.Module.settings;

public class BooleanSetting extends Setting {

    public String name;
    private boolean enabled;

    public BooleanSetting(String name, boolean defaultValue) {
        super(name);
        this.name = name;
        this.enabled = defaultValue;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return this.name;
    }
}
