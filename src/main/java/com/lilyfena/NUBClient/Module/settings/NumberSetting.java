package com.lilyfena.NUBClient.Module.settings;

public class NumberSetting extends Setting {

    private double min, max, increment;
    private double value;
    public String name;

    public NumberSetting(String name, double min, double max, double defaultValue, double increment) {
        super(name);
        this.name = name;
        this.min = min;
        this.max = max;
        this.value = defaultValue;
        this.increment = increment;
    }

    public static double clamp(double value, double min, double max) {
        value = Math.max(min, value);
        value = Math.min(max, value);
        return value;
    }

    public double getValue() {
        return value;
    }

    public float getValueFloat() {
        return (float)value;
    }

    public int getValueInt() {
        return (int)value;
    }

    public void setValue(double value) {
        value = clamp(value, this.min, this.max);
        value = Math.round(value * (1.0 / this.increment)) / (1.0 / this.increment);
        this.value = value;
    }

    public double getIncrement() {
        return increment;
    }

    public void Increment(boolean positive) {
        if (positive) setValue(getValue() + getIncrement());
        else setValue(getValue() - getIncrement());
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public String getName() {
        return name;
    }
}
