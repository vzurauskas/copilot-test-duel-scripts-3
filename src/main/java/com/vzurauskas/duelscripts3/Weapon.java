package com.vzurauskas.duelscripts3;

public final class Weapon {
    private final String name;
    private final int baseDamage;
    private final double criticalChance;
    private final double criticalMultiplier;

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
    }

    public int baseDamage() {
        return baseDamage;
    }
}

