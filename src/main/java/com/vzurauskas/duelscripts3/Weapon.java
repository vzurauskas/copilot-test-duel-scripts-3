package com.vzurauskas.duelscripts3;

public final class Weapon {
    private final String name;
    private final int baseDamage;
    private final double criticalChance;
    private final double criticalMultiplier;
    private boolean lastWasCritical;

    public Weapon(
        String name,
        int baseDamage,
        double criticalChance,
        double criticalMultiplier
    ) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.criticalChance = criticalChance;
        this.criticalMultiplier = criticalMultiplier;
        this.lastWasCritical = false;
    }

    public int calculateDamage() {
        if (Math.random() < criticalChance) {
            this.lastWasCritical = true;
            return (int) (baseDamage * criticalMultiplier);
        }
        this.lastWasCritical = false;
        return baseDamage;
    }

    public boolean wasCritical() {
        return lastWasCritical;
    }

    public String name() {
        return name;
    }
}

