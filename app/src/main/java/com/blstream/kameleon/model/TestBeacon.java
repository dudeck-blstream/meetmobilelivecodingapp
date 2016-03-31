package com.blstream.kameleon.model;

public class TestBeacon {
    private final String uuid;
    private final int minor;
    private final int major;
    private final int color;
    private final String name;
    private double accuracy;
    private boolean isDiscovered;

    public TestBeacon(String uuid, int minor, int major, int color, String name) {
        this.uuid = uuid;
        this.minor = minor;
        this.major = major;
        this.color = color;
        this.name = name;
    }

    public String getUUID() {
        return uuid;
    }

    public int getMinor() {
        return minor;
    }

    public int getMajor() {
        return major;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered(boolean discovered) {
        isDiscovered = discovered;
    }
}
